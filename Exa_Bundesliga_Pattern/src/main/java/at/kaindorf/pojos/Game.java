package at.kaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Spiel")
public class Game {
    @XmlAttribute(name = "Runde")
    private Integer round;
    @XmlElement(name = "Mannschaft1")
    private String team1;
    @XmlElement(name = "Mannschaft2")
    private String team2;
    @XmlElement(name = "Tore1")
    private Integer goal1;
    @XmlElement(name = "Tore2")
    private Integer goal2;
}
