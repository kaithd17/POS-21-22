package at.kaindorf.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, DTF);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.format(DTF);
    }
}
