
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Contains a time.
 */
public class Time implements Comparable<Time>, Clonable<Time>{
    private int hours;
    private int minutes;
    private int seconds;
    
    /**
     * Initializes a Time object
     * @param h the hours
     * @param m the minute
     * @param s
     */
    public Time(int h, int m, int s) {
        hours = h;
        minutes = m;
        seconds = s;
    }
    
    /**
     * Compares this object and another Time object.
     * If this object is earlier, then return -1.
     * If this object is later, then return 1.
     * If the objects are at the same time, then return 0.
     * @param o The other Time object
     */
    @Override
    public int compareTo(Time o) {
        if(hours < o.hours) {
            return -1;
        } else if(hours > o.hours) {
            return 1;
        } else {
            if(minutes < o.minutes) {
                return -1;
            } else if(minutes > o.minutes) {
                return 1;
            } else {
                if(seconds < o.seconds){
                    return -1;
                } else if(seconds > o.seconds){
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
    
    public Time wrap(Time o){
        int result = o.compareTo(this);
        if(result < 0){
            return new Time(24 - (hours - o.hours), 59 - (minutes - o.minutes), 59 - (seconds - o.seconds));
        } else if(result == 0){
            return new Time(0, 0, 0);
        } else {
            return o.sub(this);
        }
    }
    
    public long getMili(){
        long totalSeconds = 0;
        totalSeconds += seconds;
        totalSeconds += 60*minutes;
        totalSeconds += 3600*hours;
        return totalSeconds * 1000;
    }
    
    public Time sub(Time o){
        int h = hours - o.hours;
        int m = minutes - o.minutes;
        int s = seconds - o.seconds;
        
        if(s < 0){
            s = 60 - Math.abs(s);
            m--;
        }
        
        if(m < 0){
            m = 60 - Math.abs(m);
            h--;
        }
        
        if(h < 0){
            h = 0;
        }
        
        return new Time(h, m, s);
    }
    
    /**
     * Get the current date.
     * @return date
     */
    public Date getDate() {
        Instant instant = CalendarHelper.getInstant();
        ZonedDateTime zone = instant.atZone(ZoneOffset.systemDefault());
        int year = zone.getYear();
        int dayOfYear = zone.getDayOfYear();
        LocalDate local = LocalDate.ofYearDay(year, dayOfYear);
        LocalDateTime dateTime = local.atTime(hours, minutes);
        Instant goal = dateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(dateTime));
        Date date = Date.from(goal);
        //Print.print("Date: " + date);
        return date;
    }
    
    /**
     * Get the string representation.
     * @return string representation
     */
    @Override
    public String toString() {
        String h = hours + "";
        String m = minutes + "";
        String s = seconds + "";
        if(hours < 10){
            h = "0" + hours;
        }
        
        if(minutes < 10){
            m = "0" + minutes;
        }
        
        if(seconds < 10){
            s = "0" + seconds;
        }
        
        return h + ":" + m + ":" + s;
    }
    
    public int getHour() {
        return hours;
    }
    
    public int getMinutes() {
        return minutes;
    }
    
    public int getSeconds(){
        return seconds;
    }
    
    public static Time convert(String code){
        String[] params;
        int h;
        int m;
        int s;
        try{
            params = code.split(":");
            h = Integer.parseInt(params[0]);
            m = Integer.parseInt(params[1]);
            s = Integer.parseInt(params[2]);
            if(h < 0 || h > 23){
                System.out.println("Time Code Error! Hour " + h + " is not valid!");
                return null;
            }
            if(m < 0 || m > 59){
                System.out.println("Time Code Error! Minute " + m + " is not valid!");
                return null;
            }
            
            if(s < 0 || s > 59){
                System.out.println("Time Code Error! Second " + s + " is not valid!");
                return null;
            }
        } catch(Exception e){
            System.out.println("Time Code Error! Time format: /hours:minute");
            return null;
        }
        return new Time(h, m, s);
    }

    @Override
    public Time copy() {
        return new Time(hours, minutes, seconds);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Time)){
            return false;
        }
        Time t = (Time) obj;
        return (hours == t.hours && minutes == t.minutes && seconds == t.seconds);
    }
}
