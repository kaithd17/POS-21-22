package at.kaindorf.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name = "country")
public class Country implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "country_id")
    private Long countryId;

    @Column(length = 50, name = "name")
    private String country_name;

    @Column(length = 10, name = "code")
    private String country_code;
}
