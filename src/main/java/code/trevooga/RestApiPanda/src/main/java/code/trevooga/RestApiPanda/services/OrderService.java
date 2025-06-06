package code.trevooga.RestApiPanda.services;

import code.trevooga.RestApiPanda.Entities.Orders;
import code.trevooga.RestApiPanda.Interfaces.OrdersRepo;
import code.trevooga.RestApiPanda.config.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrdersRepo ordersRepo;


    public List<OrderDTO> getOrdersWithCustomer(String phone) {
        return ordersRepo.findOrdersWithCustomerByCustomerId(phone);
    }
    @Transactional
    public void assignOrderToCustomer(int customerId, String track) {
        ordersRepo.assignOrderToCustomer(customerId, track);
    }

    
}
