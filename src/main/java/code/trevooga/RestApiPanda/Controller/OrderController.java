package code.trevooga.RestApiPanda.Controller;

import code.trevooga.RestApiPanda.config.OrderDTO;
import code.trevooga.RestApiPanda.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/panda/lk")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/find")
    public String findByid(@RequestParam("customer_id") int customerId,
                           Model model) {
        List<OrderDTO> orders = orderService.getOrdersWithCustomer(customerId);
        model.addAttribute("orders", orders);
        model.addAttribute("customer_id", customerId);
        return "orderResult";
    }
}
