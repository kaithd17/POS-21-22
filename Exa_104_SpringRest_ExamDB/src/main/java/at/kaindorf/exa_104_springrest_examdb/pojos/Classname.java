package at.kaindorf.exa_104_springrest_examdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classname {
    @Id
    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "classname", length = 10)
    private String classname;

    @OneToMany(mappedBy = "className", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Student> students = new ArrayList<>();
}
