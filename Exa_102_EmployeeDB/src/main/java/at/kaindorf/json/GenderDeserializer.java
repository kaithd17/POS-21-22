package at.kaindorf.json;

import at.kaindorf.pojos.Gender;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class GenderDeserializer extends StdDeserializer<Gender> {

    public GenderDeserializer() {
        super(Gender.class);
    }

    @Override
    public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        if (jsonParser.readValueAs(String.class) == "F") {
            return Gender.F;
        }
        return Gender.M;
    }
}
