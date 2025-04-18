package code.trevooga.RestApiPanda.Controller;

import code.trevooga.RestApiPanda.Entities.Orders;
import code.trevooga.RestApiPanda.Entities.User;
import code.trevooga.RestApiPanda.services.PandaService;
import code.trevooga.RestApiPanda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/panda")
@RestController
public class PandaController {

    @Autowired
    UserService userService;

    @Autowired
    PandaService pandaService;

    @GetMapping
    public List<Orders> getAllOrders(){
        return pandaService.getAllOrders();
    }

    @PostMapping("/newOrder")
    public Orders addNewOrder(@RequestParam User customer, @RequestParam int price) {
        return pandaService.addNewOrder(customer, price);
    }

    @PostMapping("/newUser")
    public User addNewUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return userService.addNewUser(username, password);
    }

}
