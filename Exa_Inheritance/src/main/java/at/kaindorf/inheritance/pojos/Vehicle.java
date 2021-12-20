package at.kaindorf.inheritance.pojos;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //Für jede Class einen Table
//@DiscriminatorColumn(name="type",discriminatorType = DiscriminatorType.STRING) //das weitere Spalte in die Tabelle eingefügt wird (bei singleTable)
//singleTable ein Table wird erzeugt
//JOINED joined alle Tables
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    @NonNull
    private String brand;
}
