package code.trevooga.RestApiPanda.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OrderDTO {
    private Integer id;
    private Integer price;
    private String track;
    private double deliveryprice;
    private double weight;
    private String nameofgood;
    private String whereare;
}


