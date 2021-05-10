package sopra.pastTour.rest.dto;

import sopra.tour.TourType;

public class PastTourGetDTO {

    private TourType type;
    private Long id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

