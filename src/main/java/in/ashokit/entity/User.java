package in.ashokit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "email is required")
    @Email(message = "please enter valid email address")
    private String userEmail;

    @NotBlank(message = "password is required")
    @Size(min = 8,message = "password must contain 8 characters")
    private String userPassword;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain exactly 10 digits")
    private String userPhone;

    @NotBlank(message = "Address is required")
    private String userAddress;
}
