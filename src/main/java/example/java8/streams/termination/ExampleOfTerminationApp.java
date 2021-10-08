package example.java8.streams.termination;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExampleOfTerminationApp {
	public static void main(String[] args)	{
		ExampleOfTerminationApp theApp = new ExampleOfTerminationApp();
		theApp.exampleOfForEach();
		theApp.exampleOfCount();
		theApp.exampleOfCollect();
	}
	
	private void exampleOfForEach()	{
		System.out.println("######### exampleOfForEach ##############");
		List<String> strings = Arrays.asList("hello", "I", "am", "conguraturation", "", "phone");
		strings.stream().forEach(string -> System.out.print(string.toUpperCase() + ","));
	}

	private void exampleOfCount()	{
		System.out.println("\n\n######### exampleOfCount ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		long count = numbers.stream().count();
		System.out.println("count of numbers : " + count);
	}

	private void exampleOfCollect()	{
		System.out.println("\n\n######### exampleOfCollect ##############");
		List<String> strings = Arrays.asList("hello", "I", "am", "conguraturation", "", "phone");
		List<String> filteredStrings = strings
										.stream()
										.filter(string -> string.length() > 3)
										.collect(Collectors.toList());
		filteredStrings.stream().forEach(string -> System.out.print(string + ","));
	}
}
