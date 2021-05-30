package sopra.userauthentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    private String firstName;
    
    private String lastName;

    @Column(unique = true)
    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @Min(value = 0, message = "Age must be larger than 0")
    @Max(value = 150, message = "Age must be less than 150")
    private int age;


    private String region;


    private UserStatus userStatus;

    private Instant created;
    private boolean enabled;
}
