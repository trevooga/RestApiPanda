package code.trevooga.RestApiPanda.Controller;

import code.trevooga.RestApiPanda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        // Добавьте атрибуты модели, если необходимо
        return "register"; // Возвращает имя шаблона без расширения
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String username, @RequestParam String password) {
        return userService.changeThePassword(username, password);
    }

    @PostMapping("/register/save")
    public String register(
            @RequestParam String username,
            @RequestParam String password) {
        // Кодируем пароль перед сохранением
        userService.addNewUser(username, password);
        return "redirect:/login"; // После успешной регистрации перенаправляем на страницу логина
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
