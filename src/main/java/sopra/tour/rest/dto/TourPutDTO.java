package sopra.tour.rest.dto;

public class TourPutDTO {

    private Long id;
    private String emailMember;
    //private String tourPictureKey;

    public String getEmailMember() {
        return emailMember;
    }

    public void setEmailMember(String emailMember) {
        this.emailMember = emailMember;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
/*
    public String getTourPictureKey() {
        return tourPictureKey;
    }

    public void setTourPictureKey(String tourPictureKey ) {
        this.tourPictureKey = tourPictureKey;
    }
*/
}
