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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    private int price;

    private String track;

    private double deliveryprice;

    private double weight;

    private String nameofgood;

    private String whereare;

    private String phonecons;

    public Orders( int price, String track, double deliveryprice, double weight, String nameofgood, String whereare, String phonecons) {
        this.price = price;
        this.track = track;
        this.deliveryprice = deliveryprice;
        this.weight = weight;
        this.nameofgood = nameofgood;
        this.whereare = whereare;
        this.phonecons = phonecons;
    }
}
