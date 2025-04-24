package code.trevooga.RestApiPanda.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public class RegistrationDto {

    @Setter
    @Getter
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only Latin letters allowed")
    private String username;

    @Setter
    @Getter
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Minimum 5 characters required")
    private String password;

}