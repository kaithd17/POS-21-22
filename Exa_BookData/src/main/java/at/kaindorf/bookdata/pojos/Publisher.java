package at.kaindorf.bookdata.pojos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Publisher {
    private int publisherId;
    private String name;
    private String url;

    @XmlElementWrapper(name = "booklist")
    @XmlElement(name = "book")
    private List<Book> books = new ArrayList<>();
}
