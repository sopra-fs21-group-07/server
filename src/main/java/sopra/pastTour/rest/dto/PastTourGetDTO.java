package sopra.pastTour.rest.dto;

import sopra.tour.TourType;

import java.util.Date;

public class PastTourGetDTO {

    private TourType type;
    private Long id;
    private String summit;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

