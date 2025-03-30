package code.trevooga.RestApiPanda.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor

@Getter
@Setter

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String customer;

    private int price;

    public Orders(String customer, int price) {
        this.customer = customer;
        this.price = price;
    }
}
