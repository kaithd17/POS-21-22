package at.kaindorf.beans;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Room.findByName", query = "SELECT r FROM Room r WHERE r.name = (:name)"),
        @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
        @NamedQuery(name = "Room.findByFloor", query = "SELECT r FROM Room r WHERE r.floor = (:floor) "),
        @NamedQuery(name = "Room.countAll", query = "SELECT COUNT(r) FROM Room r")
})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "room_id")
    private int roomId;
    @NonNull
    private String name;
    @NonNull
    private Floor floor;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Classname classname;
}
