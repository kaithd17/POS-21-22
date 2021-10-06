package at.kaindorf.pojos;

import at.kaindorf.xml.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLCustomer {
    private String country;
    private String country_code;
    private String city;
    private String postal_code;
    private String streetname;
    private int streetnumber;
    private String firstname;
    private String lastname;
    private String gender;
    private boolean active;
    private String email;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate since;
}
