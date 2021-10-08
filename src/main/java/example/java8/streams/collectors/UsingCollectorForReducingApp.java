package example.java8.streams.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsingCollectorForReducingApp {
	public static void main(String[] args)	{
		UsingCollectorForReducingApp theApp = new UsingCollectorForReducingApp();
		theApp.exampleOfCount();
		theApp.exampleOfMinMax();
		theApp.exampleOfSumAndAverage();
		theApp.exampleOfJoining();
		theApp.exampleOfReducing();
	}
	
	private void exampleOfCount()	{
		System.out.println("######### exampleOfCount ##############");

		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		long count = numbers.stream().collect(Collectors.counting());
		System.out.println("count : " + count);
	}
	
	private void exampleOfMinMax()	{
		System.out.println("\n\n######### exampleOfMinMax ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		System.out.println("===> max number");
		Optional<Integer> maxNumber = numbers.stream().collect(Collectors.maxBy(Integer::compare));
		maxNumber.ifPresent(max -> System.out.println("maxNumber : " + max));

		System.out.println("===> min number");
		Optional<Integer> minNumber = numbers.stream().collect(Collectors.minBy(Integer::compare));
		minNumber.ifPresent(min -> System.out.println("minNumber : " + min));
	}
	
	private void exampleOfSumAndAverage()	{
		System.out.println("\n\n######### exampleOfSumAndAverage ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		System.out.println("===> sum of numbers");
		int sumValue  = numbers.stream().collect(Collectors.summingInt(number -> number * 10));
		System.out.println("sum of value : " + sumValue);

		System.out.println("===> average number");
		double avgValue = numbers.stream().collect(Collectors.averagingInt(number -> number * 10));
		System.out.println("average value : " + avgValue);
	}
	
	private void exampleOfJoining()	{
		System.out.println("\n\n######### exampleOfJoining ##############");
		String[] words = { "hello", "car", "telephone", "life", "company" };
		String joinedStringWithoutSpace = Stream.of(words).collect(Collectors.joining());
		System.out.println("joined string without space : " + joinedStringWithoutSpace);
		String joinedStringWithComma = Stream.of(words).collect(Collectors.joining(", "));
		System.out.println("joined string with comma : " + joinedStringWithComma);
	}
	
	private void exampleOfReducing()	{
		System.out.println("\n\n######### exampleOfReducing ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

		System.out.println("===> sum of numbers");
		int sumValue  = numbers.stream().collect(Collectors.reducing(0, number->number*10,Integer::sum));
		System.out.println("sum of value : " + sumValue);
	}
}
