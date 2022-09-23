
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;

public class CalendarHelper {
    
    private static Instant instant = Instant.now();
    
    /**
     * Get the day of the week.
     * @return day of week as an integer.
     */
    public static int getDayOfTheWeek() {
        resetInstant();
        int weekday = instant.atZone(ZoneOffset.systemDefault()).getDayOfWeek().getValue();
        return weekday;
    }
    
    public static String timeSpecFormat() {
        resetInstant();
        String specFormat = "";
        specFormat += getDayOfTheWeek();
        specFormat += "-" + instant.atZone(ZoneOffset.systemDefault()).getHour();
        specFormat += "-" + instant.atZone(ZoneOffset.systemDefault()).getMinute();
        specFormat += "-" + instant.atZone(ZoneOffset.systemDefault()).getSecond();
        return specFormat;
    }
    
    /**
     * Returns the current Time.
     * @return current time
     */
    public static Time getCurrentTime() {
        resetInstant();
        instant = Instant.now();
        int hour = instant.atZone(ZoneOffset.systemDefault()).getHour();
        int minutes = instant.atZone(ZoneOffset.systemDefault()).getMinute();
        int seconds = instant.atZone(ZoneOffset.systemDefault()).getSecond();
        return new Time((int)hour, (int)minutes, (int)seconds);
    }
    
    /**
     * Resets the instant object.
     */
    private static void resetInstant() {
        instant = Instant.now();
    }
    
    /**
     * Get the instant object.
     * @return instant object
     */
    public static Instant getInstant() {
        resetInstant();
        return instant;
    }
    
    public static int timeToNextSec() {
        long now = Date.from(getInstant()).getTime();
        long timeTill = now % 1000;
        return (int) timeTill;
        
    }
    
}
