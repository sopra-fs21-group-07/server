package sopra.tour.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sopra.mapAPI.service.MapApiService;
import sopra.pastTour.entity.PastTour;
import sopra.pastTour.service.PastTourService;
import sopra.tour.entity.Tour;
import sopra.tour.repository.TourRepository;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Tour Service
 * This class is the "worker" and responsible for all functionality related to the Tour
 * (e.g., it creates, modifies, deletes, finds). The result will be passed back to the caller.
 */
@Service
@Transactional
public class TourService {

    private final Logger log = LoggerFactory.getLogger(TourService.class);
    private MapApiService mapApiService = null;

    private final TourRepository tourRepository;
    private String currentURL = null;
    private final PastTourService pastTourService;

    public String getCurrentURL() {
        return currentURL;
    }

    public void setCurrentURL(String currentURL) {
        this.currentURL = currentURL;
    }

    // Every 30min check and clean the tour repo
    // (cron = sec, min, hour, day, month, weekday)
    @Scheduled(cron = "* */2 * * * ?")
    public void clearToursOlderThenToday() throws Exception {
        Iterable<Tour> tours = this.tourRepository.findAllValid();
        PastTour pastTour = new PastTour();
        for (Tour t : tours){
            this.tourRepository.deleteById(t.getId());
            pastTour.setSummit(t.getSummit());
            pastTour.setDate(t.getDate());
            pastTour.setType(t.getType());
            pastTourService.createPastTour(pastTour);
        }
    }

    @Autowired
    public TourService(@Qualifier("tourRepository") TourRepository tourRepository, PastTourService pastTourService) {
        this.tourRepository = tourRepository;
        this.pastTourService = pastTourService;
        this.mapApiService = new MapApiService();
    }

    public List<Tour> getTours() {
        return this.tourRepository.findAll();
    }

    private void removeOldTours(){

    }

    public Tour createTour(Tour newTour) throws Exception {
        newTour.setToken(UUID.randomUUID().toString());
        int[] coordinates = mapApiService.getSummitCoordinates(newTour.getSummit(), newTour.getAltitude());
        newTour.setCoordinate_LV03(coordinates);
        newTour.setCoordinate_WGS(convertCoordinatesLV03TOWGS(coordinates));

        checkIfTourExists(newTour);

        // saves the given entity but data is only persisted in the database once flush() is called
        newTour = tourRepository.save(newTour);
        tourRepository.flush();

        mapApiService.updateGistGithub(createKMLFile(getTours()));

        log.debug("Created Information for Tour: {}", newTour);

        return newTour;
    }

    /**convert Date into string of correct format
     *@param date
     *@return string
     */
    private String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    private double[] convertCoordinatesLV03TOWGS(int[] coordinates) {
        double east = 0.0; //longitude
        double north = 0.0; //latitude
        double y = ((double)coordinates[0] - 600000.0)/1000000.0;
        double x = ((double)coordinates[1] - 200000.0)/1000000.0;
        east = (2.6779094 + 4.728982 * y + 0.791484 * y * x + 0.1306 * y * x * x - 0.0436 * Math.pow(y, 3)) *100/36;
        north = (16.9023892 + 3.238272 * x - 0.270978 * y * y - 0.002528 * x * x - 0.0447 * y * y * x - 0.014 * Math.pow(x, 3))*100/36;
        return new double[]{east, north};
    }

    public Tour getTourById(long id){
        return tourRepository.findById(id);
    }

    public String add(Tour addMemberToTour, Tour inputUser){
        int emptySlots = addMemberToTour.getEmptySlots();
        if (emptySlots > 0){
            addMemberToTour.setEmptySlots(emptySlots - 1);
            addMemberToTour.setEmailMember(inputUser.getEmailMember());
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format("This tour is full. Please choose another one!"));
        }
        return Integer.toString(addMemberToTour.getEmptySlots());
    }

    /**
     * This is a helper method that will check the uniqueness criteria of the Tourname and the name
     * defined in the Tour entity. The method will do nothing if the input is unique and throw an error otherwise.
     *
     * @param TourToBeCreated
     * @throws org.springframework.web.server.ResponseStatusException
     * @see Tour
     */
    private void checkIfTourExists(Tour TourToBeCreated) {
        Tour TourByTourname = tourRepository.findByName(TourToBeCreated.getName());
        Tour TourByName = tourRepository.findByName(TourToBeCreated.getName());

        String baseErrorMessage = "The %s provided %s not unique. Therefore, the Tour could not be created!";
        if (TourByTourname != null && TourByName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(baseErrorMessage, "tourname and the name", "are"));
        }
        else if (TourByTourname != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(baseErrorMessage, "tourname", "is"));
        }
        else if (TourByName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(baseErrorMessage, "name", "is"));
        }
    }

    private String getCurrentUrl(long tourId){
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        if (baseUrl.contains("localhost")){
            return "http://localhost:3000/confirmTour/"+tourId;
        }
        if (baseUrl.contains("server")) {
            return "https://sopra-fs21-group-07-client.herokuapp.com/confirmTour/" + tourId;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Main URL is unknown"));
        }
    }

    public String createKMLFile(List<Tour> tours) {
        String datapath = "C:\\Users\\elbeato\\Downloads";
        String kmlstart = "<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xsi:schemaLocation=\"http://www.opengis.net/kml/2.2 https://developers.google.com/kml/schema/kml22gx.xsd\">" +
                "<Document><name>Zeichnung</name>";
        String kmlend = "</Document></kml>";

        String content = new String();

        content += kmlstart;
        for (Tour tour : tours) {
            content += "<Placemark id=\"marker_"+tour.getId()+"\">" +
                    "<ExtendedData>" +
                    "<Data name=\"type\"><value>marker</value></Data>" +
                    "</ExtendedData>" +
                    "<name>"+tour.getName()+"</name>" +
                    "<description>Link: &lt;a target=\"_blank\" " +
                    "href="+getCurrentUrl(tour.getId())+"&gt; Tour details: "+ tour.getName()+"&lt;/a&gt;&lt;" +
                    "style=\"max-height:200px;\"/&gt;</description>" +
                    "<Style>" +
                    "<IconStyle>" +
                    "<scale>0.75</scale>" +
                    "<Icon>" +
                    "<href>https://api3.geo.admin.ch/color/255,0,0/triangle-24@2x.png</href>" +
                    "<gx:w>48</gx:w>" +
                    "<gx:h>48</gx:h>" +
                    "</Icon>" +
                    "</IconStyle>" +
                    "<LabelStyle>" +
                    "<color>ff00ffff</color>" +
                    "</LabelStyle>" +
                    "</Style>" +
                    "<Point>" +
                    "<tessellate>1</tessellate>" +
                    "<altitudeMode>clampToGround</altitudeMode>" +
                    "<coordinates>"+tour.getEast_WGS()+","+tour.getNorth_WGS()+","+tour.getAltitude()+"</coordinates>" +
                    "</Point>" +
                    "</Placemark>";
        }
        content += kmlend;
        return content.replace("\"", "\\\"");
    }
}

