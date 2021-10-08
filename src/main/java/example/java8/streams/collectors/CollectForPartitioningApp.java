package example.java8.streams.collectors;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.groupingBy;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import example.java8.streams.Dish;

public class CollectForPartitioningApp {
	public static void main(String[] args)	{
		CollectForPartitioningApp theApp = new CollectForPartitioningApp();
		theApp.exampleOfPartitioningBy();
		theApp.exampleOfPartitioningByWithMap();
	}

	private void exampleOfPartitioningBy()	{
		System.out.println("\n\n######### exampleOfPartitioningBy ##############");

		Map<Boolean, List<Dish>> partitionedDishes = generateDishes().stream().collect(partitioningBy(Dish::isVegetarian));
		System.out.println(getJson(partitionedDishes));
	}
	
	private void exampleOfPartitioningByWithMap()	{
		System.out.println("\n\n######### exampleOfPartitioningBy ##############");

		Map<Boolean, Map<Dish.Type, List<Dish>>> partitionedDishesMap = 
				generateDishes().stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
		System.out.println(getJson(partitionedDishesMap));
	}

	private List<Dish> generateDishes()	{
		Dish.Type[] types = { Dish.Type.FISH, Dish.Type.MEAT, Dish.Type.OTHER };
		
		Random random = new Random();
		List<Dish> dishes = new ArrayList<>();
		for(int i = 0;i < 10;i++)	{
			dishes.add(
					new Dish(
							"dish_" + i, 
							i%2 == 0, 
							random.nextInt(1000), 
							types[random.nextInt(3)]
					)
			);
		}
		
		return dishes;
	}

	private String getJson(Object obj)	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
