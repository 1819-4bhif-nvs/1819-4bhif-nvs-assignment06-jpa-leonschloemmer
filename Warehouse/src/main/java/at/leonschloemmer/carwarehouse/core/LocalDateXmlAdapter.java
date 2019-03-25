package at.leonschloemmer.carwarehouse.core;

import javax.json.bind.adapter.JsonbAdapter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateXmlAdapter implements JsonbAdapter<LocalDate, String> {

    @Override
    public String adaptToJson(LocalDate localDate) throws Exception {
        return localDate.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public LocalDate adaptFromJson(String s) throws Exception {
        return LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
    }
}
