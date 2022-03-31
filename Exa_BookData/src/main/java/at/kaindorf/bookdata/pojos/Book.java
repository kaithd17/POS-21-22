package at.kaindorf.bookdata.pojos;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    private int bookId;
    private String title;
    private String url;
    private float price;
    private String isbn;

    @XmlElementWrapper(name = "authorlist")
    @XmlElement(name = "author")
    private List<Author> authors = new ArrayList<>();

    @XmlTransient
    @ToString.Exclude
    private Publisher publisher;
}
