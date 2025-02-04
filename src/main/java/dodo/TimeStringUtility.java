package dodo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeStringUtility {
    public final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public final static DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final static DateTimeFormatter PRESENTATION_DTF = DateTimeFormatter.ofPattern("HH:mm dd-MMM-yyyy");
    public final static DateTimeFormatter PRESENTATION_DF = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    public static LocalDateTime stringToLdt(String line) throws DodoException {
        LocalDateTime ldt;
        try {
            ldt = LocalDateTime.parse(line, DTF);
        } catch (DateTimeParseException ex) {
            throw new DodoException("Incorrect formatting of time. Use: yyyy-mm-dd hh:ss");
        }
        return LocalDateTime.parse(line, DTF);
    }

    public static LocalDate stringToLd(String line) throws DodoException {
        LocalDate ld;
        try {
            ld = LocalDate.parse(line, DF);
        } catch (DateTimeParseException ex) {
            throw new DodoException("Incorrect formatting of time. Use: yyyy-mm-dd");
        }
        return LocalDate.parse(line, DF);
    }
}
