package sopra.tour.rest.dto;

public class TourGetDTO {

    private Long id;
    private String name;
    private String summit;

    public String getSummit() {
        return summit;
    }

    public void setSummit(String summit) {
        this.summit = summit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

