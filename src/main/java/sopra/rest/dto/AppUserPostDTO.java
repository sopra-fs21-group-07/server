package sopra.rest.dto;

public class AppUserPostDTO {

    private String password;
    private String AppUsername;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
