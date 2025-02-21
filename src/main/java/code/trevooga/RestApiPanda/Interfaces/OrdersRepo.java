package code.trevooga.RestApiPanda.Interfaces;

import code.trevooga.RestApiPanda.Entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepo extends JpaRepository<Orders, Integer> {

}
