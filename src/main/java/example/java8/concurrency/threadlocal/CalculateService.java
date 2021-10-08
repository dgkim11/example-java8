package example.java8.concurrency.threadlocal;

/**
 * ThreadLocal로 선언된 변수가 static이다. 일반적으로 static인 경우 JVM 내에서 하나의 global한 값을 가지게되나 ThreadLocal 변수는
 * static이라 할지라도 Thread별로 별도의 값을 가진다. ThreadLocal은 static으로 선언하던 그렇지 않던 같은 값을 가진다.
 */
public class CalculateService {
    private static ThreadLocal<Integer> sum = new ThreadLocal<>();

    public void add(int value)  {
        if(sum.get() == null) sum.set(0);
        sum.set(sum.get() + value);
    }

    public int value()  {
        return sum.get();
    }
}
