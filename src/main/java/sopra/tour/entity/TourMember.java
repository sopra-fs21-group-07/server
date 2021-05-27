package sopra.tour.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "TOURMEMBER")
public class TourMember {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    //@Column(nullable = false)
    private String useremail;

    @Column(nullable = false)
    private String tourName;

    //@Email --> is not working!!!
    //@NotEmpty(message = "Email is required")
    @Column(nullable = false)
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

    public void setUseremail(String name) {
        this.useremail = name;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
