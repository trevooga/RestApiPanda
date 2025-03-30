package code.trevooga.RestApiPanda.services;

import code.trevooga.RestApiPanda.Entities.Orders;
import code.trevooga.RestApiPanda.Interfaces.OrdersRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PandaService {

    @Autowired
    OrdersRepo ordersRepo;

    public List<Orders> getAllOrders() {
        return ordersRepo.findAll();
    }

    @Transactional
    public Orders addNewOrder(String customer, int price) {
        return ordersRepo.save(new Orders(customer, price));
    }
}
