package at.kaindorf.json;

import at.kaindorf.pojos.Address;
import at.kaindorf.pojos.Country;
import at.kaindorf.pojos.Customer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class JSONDeserializer extends StdDeserializer<Customer> {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
    private Set<Country> countrySet = new HashSet<>();
    private Set<Address> addressSet = new HashSet<>();

    public JSONDeserializer() {
        super(Customer.class);
    }

    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = deserializationContext.readValue(jsonParser, JsonNode.class);

        Country country = new Country(
            node.get("country").asText(),
            node.get("country_code").asText()
        );
        //Add country to countrySet
        countrySet.add(country);
        Country realCountry = countrySet.stream().filter(country1 -> country1.equals(country)).findFirst().get();

        Address address = new Address(
                node.get("streetname").asText(),
                Integer.parseInt(node.get("streetnumber").asText()),
                node.get("postal_code").asText(),
                node.get("city").asText(),
                //Find country object
                realCountry
        );
        //Add address to addressSet
        addressSet.add(address);
        //Add address to countryList
        realCountry.getAddresses().add(addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get());

        Customer customer = new Customer(
                node.get("firstname").asText(),
                node.get("lastname").asText(),
                node.get("gender").asText().charAt(0),
                node.get("active").asBoolean(),
                node.get("email").asText(),
                LocalDate.parse(node.get("since").asText(),DTF),
                //Add address object
                addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get()
        );

        //Add customer to address object
        addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get().getCustomers().add(customer);

        return customer;
    }
}
