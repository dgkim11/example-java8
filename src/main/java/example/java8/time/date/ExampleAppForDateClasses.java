package example.java8.time.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;

public class ExampleAppForDateClasses {
	public static void main(String[] args)	{
		ExampleAppForDateClasses theApp = new ExampleAppForDateClasses();
		theApp.exampleOfCreation();
		theApp.exampleOfWith();
		theApp.exampleOfTemporal();
	}
	
	private void exampleOfCreation()	{
		System.out.println("====> example of creation");
		LocalDateTime dateTime = LocalDateTime.of(2016, 4, 25, 12, 12, 30);
		System.out.println("example of LocalDateTime : " + dateTime.toString());
		LocalDate date = LocalDate.of(2016, 4, 25);
		System.out.println("example of LocalDate : " + date.toString());
		LocalTime time = LocalTime.now();
		System.out.println("current time : " + time.toString());
		YearMonth yearMonth = YearMonth.of(2016, 4);
		YearMonth yearMonth2 = YearMonth.of(2010, 2);
		YearMonth yearMonth3 = YearMonth.of(2012, 2);
		System.out.println("example of YearMonth : " + yearMonth.toString());
		System.out.println("length of Month in " + yearMonth + " : " + yearMonth.lengthOfMonth());
		System.out.println("length of Month in " + yearMonth2 + " : " + yearMonth2.lengthOfMonth());
		System.out.println("length of Month in " + yearMonth3 + " : " + yearMonth3.lengthOfMonth());
		MonthDay monthDay = MonthDay.of(4, 25);
		System.out.println("example of MonthDay : " + monthDay.toString());
		Year year = Year.of(2016);
		System.out.println("example of Year : " + year.toString());
	}
	
	private void exampleOfWith()	{
		System.out.println("====> example of with()");
		LocalDateTime dateTime = LocalDateTime.of(2016, 4, 25, 12, 12, 30);
		System.out.println("example of LocalDateTime : " + dateTime.toString());
		LocalDateTime hourChanged = dateTime.withHour(1);
		System.out.println("hour changed : " + hourChanged.toString());
		LocalDateTime dayChanged = dateTime.withDayOfMonth(1);
		System.out.println("day changed : " + dayChanged.toString());
		LocalDateTime monthChanged = dateTime.withMonth(1);
		System.out.println("month changed : " + monthChanged.toString());
	}
	
	private void exampleOfTemporal()	{
		System.out.println("====> example of Temporal");
		LocalDateTime dateTime = LocalDateTime.of(2016, 4, 25, 12, 12, 30);
		LocalDateTime nextWed = dateTime.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
		System.out.println("nex Wednesday : " + nextWed.toString());
	}
}
