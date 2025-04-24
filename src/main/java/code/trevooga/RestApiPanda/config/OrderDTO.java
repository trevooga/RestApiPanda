package code.trevooga.RestApiPanda.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OrderDTO {
    private Integer orderId;
    private String username;
    private Integer price;
}
