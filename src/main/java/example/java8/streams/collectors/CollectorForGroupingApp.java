package example.java8.streams.collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import example.java8.streams.CaloricLevel;
import example.java8.streams.Dish;


public class CollectorForGroupingApp {
	public static void main(String[] args)	{
		CollectorForGroupingApp theApp = new CollectorForGroupingApp();
		theApp.exampleOfGroupBy();
		theApp.exampleOfMultiLevelGrouping();
		theApp.exampleOfGroupByCalculation();
	}

	private void exampleOfGroupBy()	{
		System.out.println("######### exampleOfGroupBy ##############");

		Map<Dish.Type, List<Dish>> dishesByType = 
				generateDishes().stream().collect(groupingBy(Dish::getType));
		System.out.println(getJson(dishesByType));
	}
	
	private void exampleOfMultiLevelGrouping()	{
		System.out.println("\n\n######### exampleOfMultiLevelGrouping ##############");
		List<Dish> dishes = generateDishes();
		
		System.out.println("\n==> Map<Dish.Type, Map<CaloricLevel, List<Dish>>>");
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = 
				dishes.stream().collect(
					groupingBy(Dish::getType, groupingBy(dish -> getCaloricLevel(dish)))
				);
		System.out.println(getJson(dishesByTypeCaloricLevel));

		System.out.println("\n==> Map<Dish.Type, Set<CaloricLevel>>");
		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByDisType = 
				dishes.stream().collect(
						groupingBy(Dish::getType, mapping(dish -> getCaloricLevel(dish), toSet()))
					);
		System.out.println(getJson(caloricLevelsByDisType));
	}
	
	private CaloricLevel getCaloricLevel(Dish dish)	{
		if (dish.getCalories() <= 400) return CaloricLevel.DIET;
		else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
		else return CaloricLevel.FAT;
	}

	private void exampleOfGroupByCalculation()	{
		System.out.println("\n\n######### exampleOfGroupByCalculation ##############");
		List<Dish> dishes = generateDishes();
		System.out.println("\n ===> count by Dish type");
		Map<Dish.Type, Long> countByType = 
				dishes.stream().collect(groupingBy(Dish::getType, counting()));
		System.out.println(getJson(countByType));
		
		System.out.println("\n ===> max caloric dish by Dish type");
		Map<Dish.Type, Optional<Dish>> maxCaloricDishByType = 
				dishes.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
		maxCaloricDishByType.entrySet().stream().forEach(
				entry -> System.out.println(
						String.format("type:%s, dish:%s", 
								entry.getKey(), entry.getValue().isPresent() ? getJson(entry.getValue().get()) : "")));

		// The below code is equivalent to the above code. It uses 'collectingAndThen()' collector.
//		Map<Dish.Type, Dish> maxCaloricDishByType2 = 
//				dishes.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
//		maxCaloricDishByType2.entrySet().stream().forEach(
//				entry -> System.out.println(
//						String.format("type:%s, dish:%s", entry.getKey(), getJson(entry.getValue()))));
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
