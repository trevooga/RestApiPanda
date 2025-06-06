package code.trevooga.RestApiPanda.Controller;

import code.trevooga.RestApiPanda.Entities.TrackForm;
import code.trevooga.RestApiPanda.Interfaces.OrdersRepo;
import code.trevooga.RestApiPanda.config.OrderDTO;
import code.trevooga.RestApiPanda.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/panda/lk")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Показать форму и список заказов
    @GetMapping("/find")
    public String showOrders(
            Model model,
            Authentication authentication
    ) {
        // Получаем номер телефона текущего пользователя
        String phone = authentication.getName(); // если getUsername() возвращает phone

        // Получаем заказы по номеру телефона
        List<OrderDTO> orders = orderService.getOrdersWithCustomer(phone);

        // Добавляем атрибуты для Thymeleaf
        model.addAttribute("phone", phone);
        model.addAttribute("orders", orders);
        model.addAttribute("newTrack", new TrackForm()); // Пустая форма

        return "orderPage";
    }

    // Обработка добавления трек-номера
    @PostMapping("/add-track")
    public String addTrack(
            @ModelAttribute("newTrack") TrackForm form,
            Authentication authentication
    ) {
        // Получаем номер телефона текущего пользователя
        String phone = authentication.getName();

        // Привязываем трек к пользователю по номеру телефона

        // Редиректим просто на /find (без параметров)
        return "redirect:/panda/lk/find";
    }
}


