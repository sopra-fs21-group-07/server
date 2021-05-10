package sopra.tour.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SUMMIT")
public class Summit {

    @Column(nullable = false)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
