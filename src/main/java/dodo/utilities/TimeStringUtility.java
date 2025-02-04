package dodo.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeStringUtility {
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter PRESENTATION_DTF = DateTimeFormatter.ofPattern("HH:mm dd-MMM-yyyy");
    public static final DateTimeFormatter PRESENTATION_DF = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
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
