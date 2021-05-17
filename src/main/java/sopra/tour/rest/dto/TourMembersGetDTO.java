package sopra.tour.rest.dto;

public class TourMembersGetDTO {
    private Long id;
    private String useremail;
    private String tourName;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getUsername(){return username;}

    public void setUsername(String username) { this.username = username;}

}
