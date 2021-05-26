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
import sopra.map_api.service.MapApiService;
import sopra.pastTour.entity.PastTour;
import sopra.pastTour.service.PastTourService;
import sopra.tour.entity.Summit;
import sopra.tour.entity.Tour;
import sopra.tour.entity.TourMember;
import sopra.tour.repository.SummitRepository;
import sopra.tour.repository.TourMembersRepository;
import sopra.tour.repository.TourRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    private TourRepository tourRepository;
    private final SummitRepository summitRepository;
    private final TourMembersRepository tourMembersRepository;
    private String currentURL = null;
    private final PastTourService pastTourService;
    private static final String error = "This username does not exist";

    // Every 30min check and clean the tour repo
    // (cron = sec, min, hour, day, month, weekday)
    @Scheduled(cron = "* */2 * * * ?")
    public void clearToursOlderThenToday() throws Exception {
        Iterable<Tour> tours = this.tourRepository.findAllValid();
        PastTour pastTour = new PastTour();
        for (Tour t : tours) {
            this.tourRepository.deleteById(t.getId());
            pastTour.setSummit(t.getSummit());
            pastTour.setDate(new Date());
            pastTour.setType(t.getType());
            pastTourService.createPastTour(pastTour);
        }
    }

    @Autowired
    public TourService(@Qualifier("tourRepository") TourRepository tourRepository, PastTourService pastTourService,
                       @Qualifier("summitRepository") SummitRepository summitRepository,
                       @Qualifier("tourMembersRepository") TourMembersRepository tourMembersRepository) {
        this.tourRepository = tourRepository;
        this.pastTourService = pastTourService;
        this.mapApiService = new MapApiService();
        this.summitRepository = summitRepository;
        this.tourMembersRepository = tourMembersRepository;
    }

    public List<Tour> getTours() {
        return this.tourRepository.findAll();
    }

    public Tour createTour(Tour newTour) throws Exception {
        newTour.setToken(UUID.randomUUID().toString());

        createSummit(newTour.getSummit(), newTour.getAltitude());
        checkIfTourExists(newTour);

        // saves the given entity but data is only persisted in the database once flush() is called
        newTour = tourRepository.save(newTour);
        tourRepository.flush();

        mapApiService.updateGistGithub(createKMLFile(getTours(), getSummits()));

        log.debug("Created Information for Tour: {}", newTour);

        return newTour;
    }

    private List<Summit> getSummits() {
        return summitRepository.findAll();
    }

    public List<TourMember> getTourMembers() {
        return tourMembersRepository.findAll();
    }

    private void createNewMember(String email, String name, long id) {
        TourMember tourMember = new TourMember();
        tourMember.setId(id);
        tourMember.setUsername(email);
        tourMember.setTourName(name);

        tourMembersRepository.save(tourMember);
        tourMembersRepository.flush();
    }


    private Long createSummit(String name, int altitude) {
        Summit repoSummit = summitRepository.findByName(name);

        if (repoSummit == null) {
            Summit newSummit = new Summit();
            //add summit to repo
            int[] coordinates = mapApiService.getSummitCoordinates(name, altitude);
            newSummit.setName(name);
            newSummit.setAltitude(altitude);
            newSummit.setCoordinate_LV03(coordinates);
            newSummit.setCoordinate_WGS(convertCoordinatesLV03TOWGS(coordinates));
            newSummit = summitRepository.save(newSummit);
            summitRepository.flush();
            return newSummit.getId();
        }
        else {
            log.debug("Summit already exists, no new tuple in the repository.");
        }
        return summitRepository.findByName(name).getId();
    }

    private double[] convertCoordinatesLV03TOWGS(int[] coordinates) {
        double east = 0.0; //longitude
        double north = 0.0; //latitude
        double y = ((double) coordinates[0] - 600000.0) / 1000000.0;
        double x = ((double) coordinates[1] - 200000.0) / 1000000.0;
        east = (2.6779094 + 4.728982 * y + 0.791484 * y * x + 0.1306 * y * x * x - 0.0436 * Math.pow(y, 3)) * 100 / 36;
        north = (16.9023892 + 3.238272 * x - 0.270978 * y * y - 0.002528 * x * x - 0.0447 * y * y * x - 0.014 * Math.pow(x, 3)) * 100 / 36;
        return new double[]{east, north};
    }

    public Tour getTourById(long id) {
        Optional<Tour> tour = tourRepository.findById(id);
        if (tour.isPresent())
            return tour.get();
        else
            return new Tour();
    }

    public String add(Tour addMemberToTour, Tour inputUser) {
        int emptySlots = addMemberToTour.getEmptySlots();
        if (emptySlots > 0) {
            addMemberToTour.setEmptySlots(emptySlots - 1);
            addMemberToTour.setEmailMember(inputUser.getEmailMember());
            createNewMember(inputUser.getEmailMember(), addMemberToTour.getName(), addMemberToTour.getId());
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
        Tour TourByName = tourRepository.findByName(TourToBeCreated.getName());

        String baseErrorMessage = "The %s provided %s not unique. Therefore, the Tour could not be created!";
        if (TourByName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(baseErrorMessage, "tourname and the name", "are"));
        }
    }

    private String getCurrentUrl(long tourId) {
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        if (baseUrl.contains("localhost")) {
            return "http://localhost:3000/tourProfilePage/" + tourId;
        }
        if (baseUrl.contains("server")) {
            return "https://sopra-fs21-group-07-client.herokuapp.com/tourProfilePage/" + tourId;
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Main URL is unknown"));
        }
    }

    public String createKMLFile(List<Tour> tours, List<Summit> summits) {
        String datapath = "C:\\Users\\elbeato\\Downloads";
        String kmlstart = "<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xsi:schemaLocation=\"http://www.opengis.net/kml/2.2 https://developers.google.com/kml/schema/kml22gx.xsd\">" +
                "<Document><name>Zeichnung</name>";
        String kmlend = "</Document></kml>";
        String content = new String();
        Summit summit = new Summit();

        content += kmlstart;

        for (Tour tour : tours) {
            for (Summit s : summits) {
                if (s.getName().equals(tour.getSummit()))
                    summit = s;
            }
            content += "<Placemark id=\"marker_" + tour.getId() + "\">" +
                    "<ExtendedData>" +
                    "<Data name=\"type\"><value>marker</value></Data>" +
                    "</ExtendedData>" +
                    "<name>" + tour.getName() + "</name>" +
                    "<description>Link: &lt;a target=\"_blank\" " +
                    "href=" + getCurrentUrl(tour.getId()) + "&gt; Tour details: " + tour.getName() + "&lt;/a&gt;&lt;" +
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
                    "<coordinates>" + summit.getEast_WGS() + "," + summit.getNorth_WGS() + "," + tour.getAltitude() + "</coordinates>" +
                    "</Point>" +
                    "</Placemark>";
        }
        content += kmlend;
        return content.replace("\"", "\\\"");
    }

    //EDIT functions
    public void editName(Long id, String name) {
        if (this.tourRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else {
            Tour tour = this.tourRepository.findById(id).get();
            tour.setName(name);
            tourRepository.flush();
        }
    }

    public void editEmptySlots(Long id, int emptySlots) {
        if (tourRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else {
            if (this.tourRepository.findById(id).isPresent()) {
                Tour tour = this.tourRepository.findById(id).get();
                tour.setEmptySlots(emptySlots);
                tourRepository.flush();
            }
        }
    }

    public void deleteTour(Long id) {
        if (tourRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, error);
        }
        else {
            this.tourRepository.deleteById(id);
            tourRepository.flush();
        }
    }

    public void cancleTour(Long tourID, String username) {
        TourMember tourmember = this.tourMembersRepository.findByUsername(username);
        if (tourmember.getUseremail().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user is not signed up for the tour yet.");
        }
        else {
            if (this.tourRepository.findById(tourID).isPresent()) {
                Tour tour = this.tourRepository.findById(tourID).get();
                tourMembersRepository.delete(tourmember);
                tour.setEmptySlots(tour.getEmptySlots() + 1);
                tourRepository.flush();
                tourMembersRepository.flush();
            }
        }

    }

}