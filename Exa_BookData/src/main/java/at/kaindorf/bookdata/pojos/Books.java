package at.kaindorf.bookdata.pojos;


import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.FIELD)
public class Books {
    @XmlElementWrapper(name = "publisherList")
    @XmlElement(name = "publisher")
    private List<Publisher> publishers = new ArrayList<>();
}
