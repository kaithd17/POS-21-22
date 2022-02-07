package at.kaindorf.exa_104_springrest_examdb.pojos;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "firstname", length = 80)
    private String firstname;

    @Column(name = "lastname", length = 80)
    private String lastname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classname", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Classname className;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Exam> exams = new ArrayList<>();
}
