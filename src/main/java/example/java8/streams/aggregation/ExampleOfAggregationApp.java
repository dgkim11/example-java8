package example.java8.streams.aggregation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExampleOfAggregationApp {
	public static void main(String[] args)	{
		ExampleOfAggregationApp theApp = new ExampleOfAggregationApp();
		theApp.exampleOfFilter();
		theApp.exampleOfMap();
		theApp.exampleOfFlatMap();
		theApp.exampleOfLimitAndSkip();
		theApp.exampleOfDistinct();
		theApp.exampleOfSorted();
//		theApp.exampleOfFind()
//		theApp.exampleOfMatch();
	}
	
	private void exampleOfFilter()	{
		System.out.println("######### examplefOfFileter ##############");
		List<String> strings = Arrays.asList("hello", "I", "am", "conguraturation", "", "phone");
		
		System.out.println("==> Filter empty strings");
		List<String> notEmptyList = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		notEmptyList.stream().forEach(string -> System.out.print(string + ","));

		System.out.println("\n\n==> Filter the size of string less than 3");
		List<String> filteredBySize = strings.stream().filter(string -> string.length() >= 3).collect(Collectors.toList());
		filteredBySize.stream().forEach(string -> System.out.print(string + ","));
	}
	
	private void exampleOfMap()	{
		System.out.println("\n\n######### exampleOfMap ##############");
		System.out.println("==> Multiply numbers by 100");
		List<Integer> numbers = Arrays.asList(5,8,3,4,9,2,2,1,1);
		numbers
			.stream()
			.map(number -> number * 100)
			.forEach(number -> System.out.print(number + ","));
	}
	
	private void exampleOfFlatMap()	{
		System.out.println("\n\n######### exampleOfFlatMap ##############");
		String message = "Hello. Nice to see you. This is an example of map";
		Stream
			.of(message.split(" "))
			.map(word -> word.split(""))
			.flatMap(Arrays::stream)
			.distinct()
			.forEach(c -> System.out.print(c + ","));
	}

	private void exampleOfLimitAndSkip()	{
		System.out.println("\n\n######### exampleOfLimit ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		numbers
			.stream()
			.limit(5)
			.collect(Collectors.toList())
			.forEach(number -> System.out.print(number + ","));

		System.out.println("\n\n######### exampleOfSkip ##############");
		numbers
			.stream()
			.skip(5)
			.collect(Collectors.toList())
			.forEach(number -> System.out.print(number + ","));
	}

	private void exampleOfDistinct()	{
		System.out.println("\n\n######### exampleOfDistinct ##############");
		List<Integer> numbers = Arrays.asList(1,6,5,4,7,6,5,3,8,8,9,3,3);
		numbers
			.stream()
			.distinct()
			.collect(Collectors.toList())
			.forEach(number -> System.out.print(number + ","));
	}
	
	private void exampleOfSorted()	{
		System.out.println("\n\n######### exampleOfSorted ##############");
		List<Integer> numbers = Arrays.asList(7,4,5,8,9,3,4,1,2,7,4,5);
		numbers
			.stream()
			.sorted()
			.forEach(number -> System.out.print(number + ","));
	}
	
}
