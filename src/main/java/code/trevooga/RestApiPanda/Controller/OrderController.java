package code.trevooga.RestApiPanda.Controller;

import code.trevooga.RestApiPanda.Entities.TrackForm;
import code.trevooga.RestApiPanda.Interfaces.OrdersRepo;
import code.trevooga.RestApiPanda.config.OrderDTO;
import code.trevooga.RestApiPanda.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam("user") int userId,
            Model model,
            HttpSession session
    ) {
        // Сохраняем userId в сессию
        session.setAttribute("userId", userId);

        // Получаем заказы
        List<OrderDTO> orders = orderService.getOrdersWithCustomer(userId);

        // Добавляем атрибуты для Thymeleaf
        model.addAttribute("userId", userId);
        model.addAttribute("orders", orders);
        model.addAttribute("newTrack", new TrackForm()); // Пустая форма

        return "orderPage";
    }

    // Обработка добавления трек-номера
    @PostMapping("/add-track")
    public String addTrack(
            @ModelAttribute("newTrack") TrackForm form,
            HttpSession session
    ) {
        int userId = (int) session.getAttribute("userId");

        // Привязываем трек к пользователю
        orderService.assignOrderToCustomer(userId, form.getTrackNumber());

        return "redirect:/panda/lk/find?user=" + userId;
    }
}

