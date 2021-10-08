package example.java8.streams.basic;

import java.util.stream.IntStream;

public class NumericRangeExample {
	public static void main(String[] args)	{
		NumericRangeExample theApp = new NumericRangeExample();
        theApp.exampleOfRange();
        System.out.println("");
		theApp.exampleOfRangeClosed();
	}
	
	private void exampleOfRangeClosed()	{
		IntStream.rangeClosed(1, 100).forEach(number -> System.out.print(number + ","));
    }

    private void exampleOfRange()	{
        IntStream.range(1, 100).forEach(number -> System.out.print(number + ","));
    }
}
