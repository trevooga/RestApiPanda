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
    public Orders addNewOrder(@RequestParam int price, @RequestParam String track,
                              @RequestParam double deliveryprice, @RequestParam double weight,
                              @RequestParam String nameofgood) {
        return pandaService.addNewOrder(price, track, deliveryprice, weight, nameofgood);
    }

    @PostMapping("/newUser")
    public User addNewUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return userService.addNewUser(username, password);
    }

}
