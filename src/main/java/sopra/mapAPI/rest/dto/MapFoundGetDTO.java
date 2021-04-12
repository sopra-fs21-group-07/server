package sopra.mapAPI.rest.dto;

public class MapFoundGetDTO {
    private String name;
    private int altitude;

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
}
