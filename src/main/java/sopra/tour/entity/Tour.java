package sopra.tour.entity;

import sopra.tour.TourType;

import javax.persistence.*;

@Entity
@Table(name = "TOUR")
public class Tour {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TourType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
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

    @Column(nullable = false)
    //200'000er lowvalue
    private int x_LV03;

    @Column(nullable = false)
    //600'000er highvalue
    private int y_LV03;

    @Column(nullable = false)
    //latitude
    private double North_WGS;

    @Column(nullable = false)
    //Longitude
    private double East_WGS;

    @Column(nullable = true)
    //Longitude
    private String TourPictureKey;

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

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public void setCoordinate_LV03(int[] coordinates) {
        this.x_LV03 = coordinates[1];
        this.y_LV03 = coordinates[0];
    }

    public void setCoordinate_WGS(double[] coordinates) {
        this.North_WGS = coordinates[1];
        this.East_WGS = coordinates[0];
    }

    public int getX_LV03() {
        return x_LV03;
    }

    public int getY_LV03() {
        return y_LV03;
    }

    public double getNorth_WGS() {
        return North_WGS;
    }

    public double getEast_WGS() {
        return East_WGS;
    }

    public String getTourPictureKey() { return TourPictureKey; }

    public void setTourPictureKey(String tourPictureKey) { TourPictureKey = tourPictureKey; }

}
