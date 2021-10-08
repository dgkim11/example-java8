package example.java8.time.enumeration;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * java.time provides a couple of enum such as DayOfWeek, Month.
 * 
 * @author Dennis
 *
 */
public class ExampleAppForEnum {
	public static void main(String[] args)	{
		ExampleAppForEnum theApp = new ExampleAppForEnum();
		theApp.exampleOfPlus();
		theApp.exampleOfDisplayForDayOfWeek();
		theApp.exampleOfDisplayForMonth();
	}
	
	private void exampleOfPlus()	{
		DayOfWeek dow = DayOfWeek.FRIDAY;
		System.out.println("current day of week is " + dow.toString());
		DayOfWeek oneDayAfter = dow.plus(1);
		System.out.println("Tomorrow is " + oneDayAfter.toString());
		DayOfWeek oneDayBefore = dow.plus(-1);
		System.out.println("Yesterday is " + oneDayBefore.toString());
		
		Month month = Month.APRIL;
		System.out.println("current month is " + month.toString());
		Month nextMonth = month.plus(1);
		System.out.println("next month is " + nextMonth);
		Month previousMonth = month.plus(-1);
		System.out.println("previous month is " + previousMonth);
	}
	
	private void exampleOfDisplayForDayOfWeek()	{
		DayOfWeek dow = DayOfWeek.FRIDAY;
		System.out.println("====> display types of DayOfWeek with default locale.");
		Locale defaultLocale = Locale.getDefault();
		displayDayOfWeek(defaultLocale, dow);
		System.out.println("====> display types of DayOfWeek with US locale.");
		Locale usLocale = Locale.US;
		displayDayOfWeek(usLocale, dow);
		System.out.println("====> display types of DayOfWeek with Japan locale.");
		Locale japanLocale = Locale.JAPAN;
		displayDayOfWeek(japanLocale, dow);
	}
	
	private void exampleOfDisplayForMonth()	{
		Month month = Month.APRIL;
		System.out.println("====> display types of Month with default locale.");
		Locale defaultLocale = Locale.getDefault();
		displayMonth(defaultLocale, month);
		System.out.println("====> display types of Month with US locale.");
		Locale usLocale = Locale.US;
		displayMonth(usLocale, month);
		System.out.println("====> display types of Month with Japan locale.");
		Locale japanLocale = Locale.JAPAN;
		displayMonth(japanLocale, month);
	}

	private void displayDayOfWeek(Locale locale, DayOfWeek dow)	{
		System.out.println("FULL - " + dow.getDisplayName(TextStyle.FULL, locale));
		System.out.println("FULL_STANDALONE - " + dow.getDisplayName(TextStyle.FULL_STANDALONE, locale));
		System.out.println("SHORT - " + dow.getDisplayName(TextStyle.SHORT, locale));
		System.out.println("SHORT_STANDALONE - " + dow.getDisplayName(TextStyle.SHORT_STANDALONE, locale));
		System.out.println("NARROW - " + dow.getDisplayName(TextStyle.NARROW, locale));
		System.out.println("NARROW_STANDALONE - " + dow.getDisplayName(TextStyle.NARROW_STANDALONE, locale));
	}

	private void displayMonth(Locale locale, Month month)	{
		System.out.println("FULL - " + month.getDisplayName(TextStyle.FULL, locale));
		System.out.println("FULL_STANDALONE - " + month.getDisplayName(TextStyle.FULL_STANDALONE, locale));
		System.out.println("SHORT - " + month.getDisplayName(TextStyle.SHORT, locale));
		System.out.println("SHORT_STANDALONE - " + month.getDisplayName(TextStyle.SHORT_STANDALONE, locale));
		System.out.println("NARROW - " + month.getDisplayName(TextStyle.NARROW, locale));
		System.out.println("NARROW_STANDALONE - " + month.getDisplayName(TextStyle.NARROW_STANDALONE, locale));
	}
}
