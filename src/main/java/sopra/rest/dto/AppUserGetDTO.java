package sopra.rest.dto;

import sopra.appuser.AppUserStatus;

public class AppUserGetDTO {

    private Long id;
    private String AppUsername;
    private String email;
    private AppUserStatus status;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AppUserStatus getStatus() {
        return status;
    }

    public void setStatus(AppUserStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
