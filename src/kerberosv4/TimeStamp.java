
package kerberosv4;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class TimeStamp {

    public TimeStamp() {
    }
    
    public Timestamp tiempo(){
        return Timestamp.from(Instant.now());
    }

    public String tSaS(Timestamp timestamp) {
        return DateTimeFormatter.ISO_DATE_TIME.format(timestamp.toLocalDateTime());
    }

    public Timestamp SaTS(String timeStampValue) {
        return Timestamp.valueOf(timeStampValue);
    }

    public Long tl(int d, int h, int m, int s) {
        return tl((d * 24) + h, m, s);
    }

    public Long tl(int h, int m, int s) {
        return tl((h * 60) + m, s);
    }

    public Long tl(int m, int s) {
        return tl((m * 60) + s);
    }

    public Long tl(int s) {
        return s * 1000L;
    }
    
}
