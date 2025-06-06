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

    @Query("SELECT new code.trevooga.RestApiPanda.config.OrderDTO(o.id, o.price, o.track, o.deliveryprice, o.weight, o.nameofgood, o.whereare) " +
            "FROM Orders o WHERE o.phonecons = :phone")
    List<OrderDTO> findOrdersWithCustomerByCustomerId(@Param("phone") String phone);


    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET customer_id = :user WHERE track = :track", nativeQuery = true)
    void assignOrderToCustomer(@Param("user") int customerId, @Param("track") String track);
}
