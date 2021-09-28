package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Address.GetAll", query = "SELECT a FROM Address a WHERE a.street LIKE :street"),
        @NamedQuery(name = "Address.GetByClassname", query = "SELECT a FROM Address a WHERE a.student.schoolClass.schoolClassname = :classname")
})
public class Address implements Serializable {
    @Id
    @Column(name = "addressId", nullable = false)
    @GeneratedValue
    private Long addressId;
    @Column(length = 100, nullable = false)
    @NonNull
    private String city;
    @Column(length = 100, nullable = false)
    @NonNull
    private String street;
    @Column(length = 100, nullable = false)
    @NonNull
    private String number;

    //Bei 1 zu 1 bei der Seite wo nicht der Eigentümer ist und one to many da wo nicht der FK ist
    //Hier kommt der Name hin wie es in der Klasse heißt
    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private Student student;
}
