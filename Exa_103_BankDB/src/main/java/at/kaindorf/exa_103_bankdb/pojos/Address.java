package at.kaindorf.exa_103_bankdb.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @Column(name = "address_Id")
    private Long addressId;

    @Column(name = "streetname", length = 100)
    private String streetname;

    @Column(name = "street_number", length = 10)
    private String streetNumber;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(name = "city", length = 100)
    private String city;
}
