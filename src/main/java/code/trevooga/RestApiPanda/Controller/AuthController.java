package code.trevooga.RestApiPanda.Controller;

import code.trevooga.RestApiPanda.config.RegistrationDto;
import code.trevooga.RestApiPanda.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String defaultPage(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registrationDto", new RegistrationDto());
        return "register";
    }

    @GetMapping("/changePassword")
    public String changePasswordForm(Model model) {
        return "password";
    }

    @PostMapping("/changePassword/save")
    public String changePassword(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("successMessage", "Пароль успешно изменен!");
        return "redirect:/changePassword";
    }

    @PostMapping("/register/save")
    public String register(
            @Valid RegistrationDto registrationDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // Передаем ошибки в представление
            redirectAttributes.addFlashAttribute(
                    "errors",
                    bindingResult.getFieldErrors()
            );
            return "redirect:/register";
        }

        try {
            userService.addNewUser(
                    registrationDto.getUsername(),
                    registrationDto.getPassword(),
                    registrationDto.getEmail()
            );
            redirectAttributes.addFlashAttribute(
                    "registrationSuccess",
                    "Аккаунт успешно зарегистрирован!"
            );
            return "redirect:/register";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Ошибка регистрации: " + e.getMessage()
            );
            return "redirect:/register";
        }
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/adminpage")
    public String adminPage() {
        return "mainAdminPage";
    }
}
