package example.java8.util;

import java.util.regex.Pattern;

/**
 * java.util.regex.Pattern example
 *
 * @author Dennis Kim
 */
public class PatternExample {
	private String quotes = "^.+\\)|\\(.+\\)|\\[.+\\]|<.+>|\\{.+\\}|\\*.+\\*";

	public static void main(String[] args) 	{
		PatternExample theApp = new PatternExample();
		theApp.execute();
	}

	private void execute()	{
		Pattern pattern = Pattern.compile(quotes);
		String result = pattern.matcher("1234(abcd)5678").replaceAll("");
		System.out.println("result : " + result);
	}
}
