package at.kaindorf.inheritance.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@ToString(callSuper = true) //Daten der Basisklasse werden auch ausgegeben
@Entity
//@DiscriminatorValue("car") //für singleTable, wird für die discriminatorSpalte der Value "car" eingefügt
public class Car extends Vehicle{
    private String engine;
    private int seats;

    public Car(@NonNull String brand, String engine, int seats) {
        super(brand);
        this.engine = engine;
        this.seats = seats;
    }
}
