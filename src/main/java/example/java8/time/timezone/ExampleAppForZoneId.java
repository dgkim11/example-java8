package example.java8.time.timezone;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ExampleAppForZoneId {
	public static void main(String[] args)	{
		ExampleAppForZoneId theApp = new ExampleAppForZoneId();
		theApp.displayZoneList();
	}
	
	private void displayZoneList()	{
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		List<String> zoneList = new ArrayList<String>(zoneIds);
		Collections.sort(zoneList);
		
		LocalDateTime dt = LocalDateTime.now();
		for(String zoneId : zoneList)	{
			ZoneId zone = ZoneId.of(zoneId);
			ZonedDateTime zdt = dt.atZone(zone);
			ZoneOffset zoneOffset = zdt.getOffset();
			int secondsOfHour = zoneOffset.getTotalSeconds() % (60*60);
			String out = String.format("%20s, %-50s, %-10s", zone, zdt, zoneOffset);
			String comment = "";
			if(secondsOfHour != 0)	{
				comment = "- Zone offset is not at hour level";
			}
			System.out.println(out + comment);
		}
	}
}
