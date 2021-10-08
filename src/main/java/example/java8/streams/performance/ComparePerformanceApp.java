package example.java8.streams.performance;

import example.java8.streams.Dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ComparePerformanceApp {
	private List<Dish> dishes = generateDishes();
	
	public static void main(String[] args)	{
		ComparePerformanceApp theApp = new ComparePerformanceApp();
		
		theApp.compareBetweenStreamAndTraditionalWay();
		theApp.compareParallelAndSequential();
	}

	private void compareBetweenStreamAndTraditionalWay()	{
		System.out.println("\n\n######### compareBetweenStreamAndTraditionalWay ##############");

		long startTime = System.nanoTime();
		findDishNamesByStream();
		long endTime = System.nanoTime();
		System.out.println("elapsed time for streaming way : " + (endTime - startTime) / 1000);
		
		startTime = System.nanoTime();
		findDishNamesByTraditionalWay();
		endTime = System.nanoTime();
		System.out.println("elapsed time for traditional way : " + (endTime - startTime) / 1000);
		
	}

	private void compareParallelAndSequential()	{
		System.out.println("\n\n######### compareParallelAndSequential ##############");

		long startTime = System.nanoTime();
		sumOfNumbersByParallel();
		long endTime = System.nanoTime();
		System.out.println("elapsed time for parallel way : " + (endTime - startTime) / 1000);
		
		startTime = System.nanoTime();
		sumOfNumbersByTraditionalWay();
		endTime = System.nanoTime();
		System.out.println("elapsed time for traditional way : " + (endTime - startTime) / 1000);
	}

	private void sumOfNumbersByParallel()	{
		int sumOfCalories = dishes.parallelStream().map(Dish::getCalories).reduce(0, Integer::sum);
		System.out.println("sum of calories : " + sumOfCalories);	
	}
	
	private void sumOfNumbersByTraditionalWay()	{
		int sumOfCalories = 0;
		for(Dish dish : dishes)	{
			sumOfCalories += dish.getCalories();
		}
		System.out.println("sum of calories : " + sumOfCalories);	
	}
	
	private List<String> findDishNamesByStream()	{
		return dishes
				.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.sorted()
				.collect(Collectors.toList());
	}
	
	private List<String> findDishNamesByTraditionalWay()	{
		List<String> dishNames = new ArrayList<>();
		for(Dish dish : dishes)	{
			if(dish.getCalories() > 300)	{
				dishNames.add(dish.getName());
			}
		}
		Collections.sort(dishNames);
		
		return dishNames;
	}
	
	private List<Dish> generateDishes()	{
		Dish.Type[] types = { Dish.Type.FISH, Dish.Type.MEAT, Dish.Type.OTHER };
		
		Random random = new Random();
		List<Dish> dishes = new ArrayList<>();
		for(int i = 0;i < 1000000;i++)	{
			dishes.add(
					new Dish(
							"dish_" + random.nextInt(1000) + "_" + i, 
							i%2 == 0, 
							random.nextInt(1000000), 
							types[random.nextInt(3)]
					)
			);
		}
		
		return dishes;
	}
}
