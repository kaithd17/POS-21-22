package at.kaindorf.json;

import at.kaindorf.pojos.Customer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class JSONDeserializer extends StdDeserializer<Customer> {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    public JSONDeserializer() {
        super(Customer.class);
    }

    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = deserializationContext.readValue(jsonParser, JsonNode.class);
        //TODO: Create Objects
        Customer customer = new Customer();
        return customer;
    }
}
