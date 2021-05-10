package sopra.pastTour.entity;

import sopra.tour.TourType;
import javax.persistence.*;

@Entity
@Table(name = "PASTTOUR")
public class PastTour {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TourType type;

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

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }
}
