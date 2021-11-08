package at.kaindorf.beans;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Classname.findByName", query = "SELECT c FROM Classname c WHERE c.name = (:name)"),
    @NamedQuery(name = "Classname.findAll", query = "SELECT c FROM Classname c"),
    @NamedQuery(name = "Classname.findByFloor", query = "SELECT c FROM Classname c WHERE Classname.room.floor = (:floor)"),
    @NamedQuery(name = "Classname.countAll", query = "SELECT COUNT(c) FROM Classname c")
})
public class Classname {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int classId;
    @NonNull
    private String name;
    @NonNull
    private int grade;
    @NonNull
    private int size;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Room room;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private ClassTeacher classTeacher;
}
