package sopra.tour.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TOUR")
public class Tour {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

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
}
