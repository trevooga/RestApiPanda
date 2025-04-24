package code.trevooga.RestApiPanda.Interfaces;

import code.trevooga.RestApiPanda.Entities.Orders;
import code.trevooga.RestApiPanda.config.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface OrdersRepo extends JpaRepository<Orders, Integer> {

    @Query("SELECT new code.trevooga.RestApiPanda.config.OrderDTO(o.id, o.customer.username, o.price) " +
            "FROM Orders o WHERE o.customer.id = :customer_id")
    List<OrderDTO> findOrdersWithCustomerByCustomerId(@Param("customer_id") int customerId);

}
