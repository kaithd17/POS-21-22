package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "student")
@IdClass(StudentPK.class)
//Entities sind nach dem Proxy Pattern aufgebaut
//@Basic(fetch = FetchType.LAZY) Eager ladet alles, Lazy ladet nur das wenn es aufgerufen wird
public class Student implements Serializable {
    //@Id --> Primary Key
 /* @Id
    @Column(name = "student_id")
    @GeneratedValue
    private Long studentId;*/

    @Id
    @NonNull
    private String className;
    @Id
    @NonNull
    private Long catNo;
    @NonNull
    @Column(nullable = false, length = 100)
    private String firstname;
    @NonNull
    @Column(nullable = false, length = 150)
    private String lastname;
    @NonNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Transient //wird nicht in die Datenbank gespeichert
    private String fullname;

    //orphanRemoval --> Adresse wird gelöscht wenn kein Objekt die Adresse referenziert.
    //cascade = CascadeType.PERSIST --> Addresse Objekt wird mit dem Student Objekt mitgenommen
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "schoolClass")
    private SchoolClass schoolClass;

    public String getFullname() {
        return String.format("%s %s", lastname, firstname);
    }
}
