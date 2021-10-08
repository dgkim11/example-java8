package example.java8.streams.collectors;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Returns a Collector that accumulates elements into a Map whose keys and values are the result of applying the provided mapping functions to the input elements.
 * If the mapped keys contains duplicates (according to Object.equals(Object)), the value mapping function is applied to each equal element, and the results are merged using the provided merging function.
 *
 * @author Dennis Kim
 */
public class MapCollectorApp {
	public static void main(String[] args)	{
		PairValue[] items = { new PairValue("a","b"), new PairValue("c","d"), new PairValue("a", "g")};

		Map<String,String> mappedValue = Arrays.stream(items).collect(Collectors.toMap(v -> v.key, v -> v.value, (k, v) -> k + " , " + v ));
		mappedValue.entrySet().stream().forEach(v -> { System.out.println(v.getKey() + " = " + v.getValue()); });
	}

	private static class PairValue	{
		private String key;
		private	String value;

		public PairValue(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}
