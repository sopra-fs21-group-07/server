package sopra.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sopra.tour.TourType;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "TOUR")
public class Tour {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TourType type;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String summit;

    @Column(nullable = false, unique = true)
    private String token;

    //Tells number of total members from the beginning
    @Column(nullable = true)
    private String emailMember;

    //Count the number of empty slots member - participants = emtpy slots
    @Column(nullable = false)
    private int emptySlots;

    @Column(nullable = false)
    private int altitude;

    @Column(nullable = true)
    private String TourPictureKey;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String creatorUsername;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
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

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public String getEmailMember() {
        return emailMember;
    }

    public void setEmailMember(String emailMember) {
        this.emailMember = emailMember;
    }

    public int getEmptySlots() {
        return emptySlots;
    }

    public void setEmptySlots(int emptySlots) {
        this.emptySlots = emptySlots;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTourPictureKey() { return TourPictureKey; }

    public void setTourPictureKey(String tourPictureKey) { TourPictureKey = tourPictureKey; }

    public LocalDate getDate(){return date;}

    public void setDate(LocalDate date){this.date = date;}

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

}
