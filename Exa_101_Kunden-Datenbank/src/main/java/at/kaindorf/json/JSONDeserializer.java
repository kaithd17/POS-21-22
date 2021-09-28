package at.kaindorf.json;

import at.kaindorf.pojos.Customer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class JSONDeserializer extends StdDeserializer<Customer> {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public JSONDeserializer() {
        super(Customer.class);
    }

    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        return null;
    }
}
