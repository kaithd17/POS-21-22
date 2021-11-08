package at.kaindorf.beans;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "ClassTeacher.findByName", query = "SELECT ct FROM ClassTeacher ct WHERE ClassTeacher.lastname = (:lastname) "),
        @NamedQuery(name = "ClassTeacher.findByClassname", query = "SELECT ct FROM ClassTeacher ct WHERE ClassTeacher.classname.name = (:classname) "),
        @NamedQuery(name = "ClassTeacher.findByGrade", query = "SELECT ct FROM ClassTeacher ct WHERE ClassTeacher.classname.grade = (:grade) "),
        @NamedQuery(name = "ClassTeacher.countAll", query = "SELECT COUNT(ct) FROM ClassTeacher ct")
})
public class ClassTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "teacher_id")
    private int teacherId;
    @NonNull
    private String initials;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private String title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Classname classname;
}
