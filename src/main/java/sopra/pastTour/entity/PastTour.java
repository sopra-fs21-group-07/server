package sopra.pastTour.entity;

import sopra.tour.TourType;
import javax.persistence.*;
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
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
