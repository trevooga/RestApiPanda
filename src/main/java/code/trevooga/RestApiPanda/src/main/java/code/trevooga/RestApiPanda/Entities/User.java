package code.trevooga.RestApiPanda.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(nullable = false)
    private String password;

    private String roles;

    @Column(nullable = false, unique = true)
    private String phone;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
        this.roles = "USER";
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders = new ArrayList<>();
}
