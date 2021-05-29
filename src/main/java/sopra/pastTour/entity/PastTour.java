package sopra.pastTour.entity;

import org.apache.tomcat.jni.Local;
import sopra.tour.TourType;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "PASTTOUR")
public class PastTour {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String summit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TourType type;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private String tourPictureKey;

    @Column(nullable = false)
    private String name;


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

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTourPictureKey(){return tourPictureKey;}

    public void setTourPictureKey(String tourPictureKey){this.tourPictureKey = tourPictureKey;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

}
