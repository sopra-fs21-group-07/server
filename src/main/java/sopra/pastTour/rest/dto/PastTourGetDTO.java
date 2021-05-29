package sopra.pastTour.rest.dto;

import sopra.tour.TourType;

import java.time.LocalDate;
import java.util.Date;

public class PastTourGetDTO {

    private TourType type;
    private Long id;
    private String summit;
    private LocalDate date;
    private String tourPictureKey;
    private String name;

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummit() {
        return summit;
    }

    public void setSummit(String summit) {
        this.summit = summit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public String getTourPictureKey(){return tourPictureKey;}

    public void setTourPictureKey(String tourPictureKey){this.tourPictureKey= tourPictureKey;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}
}

