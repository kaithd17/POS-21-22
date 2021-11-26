package at.kaindorf.springburger.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "burger_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Long orderId;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;

    @OneToMany(mappedBy = "order")
    private List<Burger> burgerList = new ArrayList<>();

    public void addBurger(Burger burger) {
        burger.setOrder(this);
        burgerList.add(burger);
    }
}
