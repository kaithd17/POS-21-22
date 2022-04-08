package at.kaindorf.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Meisterschaft")
public class Tournament {
    @XmlAttribute(name = "Name")
    private String name;
    @XmlElementWrapper(name = "Spiele")
    @XmlElement(name = "Spiel")
    private List<Game> games = new ArrayList<>();
}
