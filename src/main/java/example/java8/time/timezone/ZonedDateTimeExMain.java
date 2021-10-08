package example.java8.time.timezone;

import java.time.*;

public class ZonedDateTimeExMain {
    public static void main(String[] args)  {
        ZonedDateTimeExMain theApp = new ZonedDateTimeExMain();
        theApp.changeTimeZone();
        theApp.zoneOffset();
    }

    private void changeTimeZone()    {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("US/Hawaii"));
        System.out.println(localDateTime.toString());
        System.out.println(zonedDateTime.toString());

        System.out.println("Local. Day:Hour=" + localDateTime.getDayOfMonth() + ":" + localDateTime.getHour());
        System.out.println("Hawaii. Day:Hour=" + zonedDateTime.getDayOfMonth() + ":" + zonedDateTime.getHour());
    }

    private void zoneOffset()   {
        ZoneOffset zoneOffset = ZoneOffset.of("+02:00");
        LocalDateTime localDateTime = LocalDateTime.now();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, zoneOffset);
        System.out.println(offsetDateTime.toString());
    }
}
