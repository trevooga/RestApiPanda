package code.trevooga.RestApiPanda.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public class RegistrationDto {

    @Setter
    @Getter
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "[0-9]{11,12}$", message = "Неверный формат телефона")
    private String phone;

    @Setter
    @Getter
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Minimum 5 characters required")
    private String password;

    @Setter
    @Getter
    private String username;
}