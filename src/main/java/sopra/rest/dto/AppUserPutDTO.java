package sopra.rest.dto;

public class AppUserPutDTO {

    private String password;
    private String AppUsername;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppUsername() {
        return AppUsername;
    }

    public void setAppUsername(String AppUsername) {
        this.AppUsername = AppUsername;
    }
}
