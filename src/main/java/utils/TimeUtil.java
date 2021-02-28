package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeUtil {

    public static int getDayOfWeek() {
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String day = dayOfWeek.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.ENGLISH);
        return Integer.parseInt(day);
    }
}
