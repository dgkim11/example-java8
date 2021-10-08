package example.java8.streams.basic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleOfBasic {
	public static void main(String[] args)	{
		ExampleOfBasic theApp = new ExampleOfBasic();
//		theApp.exampleOfStreamCreation();
//		theApp.exampleOfForEach();
//		theApp.exampleOfCount();
		theApp.exampleOfNotStreaming();
	}
	
	private void exampleOfStreamCreation()	{
		System.out.println("######### exampleOfStreamCreation ##############");
		
		System.out.println("--> create a stream from a Collection object");
		Collection<String> collection = Arrays.asList("A","B","C");
		collection.stream().forEach(System.out::print);
		
		System.out.println("\n--> create a stream through Stream.of()");
		Stream.of("A","B","C").forEach(System.out::print);
		
		System.out.println("\n--> create a stream through IntStream");
		IntStream.range(1, 10).forEach(System.out::print);

		System.out.println("\n--> create a stream through IntStream");
		Arrays.stream(new int[] { 1,2,3,4,5,6,7,8,9 }).forEach(System.out::print);
	}
	private void exampleOfForEach()	{
		System.out.println("######### exampleOfForEach ##############");
		List<String> strings = Arrays.asList("hello", "I", "am", "Congratulation", "", "phone");
		strings.stream().forEach(string -> System.out.print(string.toUpperCase() + ","));
	}

	private void exampleOfCount()	{
		System.out.println("\n\n######### exampleOfCount ##############");
		List<String> strings = Arrays.asList("hello", "I", "am", "Congratulation", "", "phone");
		long count = strings.stream().count();
		System.out.println("count : " + count);
	}

	private void exampleOfNotStreaming()	{
		Predicate<String> containsNumber = s -> s.matches("[0-9]*");

		System.out.println("\n\n######### exampleOfNotExec ##############");
		List<String> strings = Arrays.asList("hello", "I", "am", "Congratulation", "", "phone");
		System.out.println("\n\n######### peek ##############");
		strings.stream().peek(s -> printString(s)).peek(s -> writeString(s));
		System.out.println("\n\n######### findAny ##############");
		strings.stream().peek(s -> printString(s)).peek(s -> writeString(s)).findAny();
		System.out.println("\n\n######### allMatch ##############");
		strings.stream().peek(s -> printString(s)).peek(s -> writeString(s)).allMatch(containsNumber);
		System.out.println("\n\n######### anyMatch ##############");
		strings.stream().peek(s -> printString(s)).peek(s -> writeString(s)).anyMatch(containsNumber);
	}

	private void writeString(String s) {
		System.out.println("write: "+ s);
	}

	private void printString(String s) {
		System.out.println("print:" + s);
	}
}
