package sopra.tour.rest.dto;

import sopra.tour.TourType;
import java.util.*;

public class TourPostDTO {
    private TourType type;
    private String name;
    private String summit;
    private String emailMember;
    private int emptySlots;
    private int altitude;
    private String tourPictureKey;
    private Date date;

    private String creatorUsername;

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    public String getEmailMember() {
        return emailMember;
    }

    public void setEmailMember(String emailMember) {
        this.emailMember = emailMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummit() {
        return summit;
    }

    public void setSummit(String summit) {
        this.summit = summit;
    }

    public int getEmptySlots() {
        return emptySlots;
    }

    public void setEmptySlots(int emptySlots) {
        this.emptySlots = emptySlots;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public String getTourPictureKey() {
        return tourPictureKey;
    }

    public void setTourPictureKey(String tourPictureKey ) {
        this.tourPictureKey = tourPictureKey;
    }

    public Date getDate(){return date;}

    public void setDate(Date date){this.date = date;}


    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

}
