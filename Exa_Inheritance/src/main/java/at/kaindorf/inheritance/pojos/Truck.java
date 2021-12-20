package at.kaindorf.inheritance.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
//@DiscriminatorValue("truck") //setzt value
public class Truck extends Vehicle{
    private double payload;
    private int height;

    public Truck(@NonNull String brand, double payload, int height) {
        super(brand);
        this.payload = payload;
        this.height = height;
    }
}
