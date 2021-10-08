package example.java8.streams.termination;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExampleOfReductionApp {
	public static void main(String[] args)	{
		ExampleOfReductionApp theApp = new ExampleOfReductionApp();
		theApp.exampleOfReduce();
		theApp.exampleOfReduceWithIdentity();
		theApp.exampleOfMax();
		theApp.exampleOfMin();
		theApp.exampleOfAllMatch();
		theApp.exampleOfAnyMatch();
		theApp.exampleOfNoneMatch();
	}
	
	/**
	 * reduce(BinaryOperator<T> accumulator) is equivalent to:
     * <pre>
     * {@code
     *     boolean foundAny = false;
     *     T result = null;
     *     for (T element : this stream) {
     *         if (!foundAny) {
     *             foundAny = true;
     *             result = element;
     *         }
     *         else
     *             result = accumulator.apply(result, element);
     *     }
     *     return foundAny ? Optional.of(result) : Optional.empty();
     * }
     * </pre>	 
     */
	private void exampleOfReduce()	{
		System.out.println("\n\n######### exampleOfReduce ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Optional<Integer> sum = numbers.stream().reduce((x,y) -> x + y);
		sum.ifPresent(value -> System.out.println("sum of numbers : " + value));
	}

	/**
	 * reduce(T identity, BinaryOperator<T> accumulator) is equivalent
     * to:
	 *  <pre>{@code
     *     T result = identity;
     *     for (T element : this stream)
     *         result = accumulator.apply(result, element)
     *     return result;
     * }</pre>
	 */
	private void exampleOfReduceWithIdentity()	{
		System.out.println("\n\n######### exampleOfReduceWithIdentity ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Integer sum = numbers.stream().reduce(100, (x,y) -> x + y);
		System.out.println("sum of numbers : " + sum);
	}

	private void exampleOfMax()	{
		System.out.println("\n\n######### exampleOfMax ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Optional<Integer> max = numbers.stream().max(Integer::compare);
		// The following code is equivalent to the above code.
		// numbers.stream().reduce(Integer::max);
		max.ifPresent(value -> System.out.println("max value of numbers : " + value));
	}

	private void exampleOfMin()	{
		System.out.println("\n\n######### exampleOfMin ##############");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Optional<Integer> min = numbers.stream().min(Integer::compare);
		// The following code is equivalent to the above code.
		// numbers.stream().reduce(Integer::min);
		min.ifPresent(value -> System.out.println("max value of numbers : " + value));
	}
	
	private void exampleOfAllMatch()	{
		System.out.println("\n\n######### exampleOfAllMatch ##############");
		List<Integer> numbers = Arrays.asList(2,4,6,8,10);
		boolean isAllEven = numbers.stream().allMatch(number -> number%2 == 0);
		System.out.println("Are all numbers even? - " + isAllEven);
	}

	private void exampleOfAnyMatch()	{
		System.out.println("\n\n######### exampleOfAnyMatch ##############");
		List<Integer> numbers = Arrays.asList(2,4,6,7,8,10);
		boolean existOddNumber = numbers.stream().anyMatch(number -> number%2 == 1);
		System.out.println("Is there odd number? - " + existOddNumber);
	}

	private void exampleOfNoneMatch()	{
		System.out.println("\n\n######### exampleOfNoneMatch ##############");
		List<Integer> numbers = Arrays.asList(2,4,6,7,8,10);
		boolean lessThan = numbers.stream().noneMatch(number -> number > 10);
		System.out.println("Are all numbers less than 10? - " + lessThan);
	}
}
