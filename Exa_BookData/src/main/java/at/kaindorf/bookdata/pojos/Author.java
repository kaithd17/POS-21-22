package at.kaindorf.bookdata.pojos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
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
public class Author {
    private int authorId;
    private String firstname;
    private String lastname;
    private String url;

    @XmlTransient
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();
}
