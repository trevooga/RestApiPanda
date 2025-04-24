package code.trevooga.RestApiPanda.services;

import code.trevooga.RestApiPanda.Entities.Orders;
import code.trevooga.RestApiPanda.Interfaces.OrdersRepo;
import code.trevooga.RestApiPanda.config.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrdersRepo ordersRepo;


    public List<OrderDTO> getOrdersWithCustomer(int customerId) {
        return ordersRepo.findOrdersWithCustomerByCustomerId(customerId);
    }
}
