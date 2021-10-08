package example.java8.concurrency.threadlocal;

/**
 * ThreadLocal 객체는 thread별로 로컬 변수를 가질 수 있도록 해준다. 아래의 CalculateService는 내부에 ThreadLocal 변수를 가지게 되며
 * 각 Thread별로 자신의 고유 값을 가지게 된다.
 */
public class ThreadLocalExMain {
    public static void main(String[] args)  {
        CalculateService service = new CalculateService();

        CalculateThread t1 = new CalculateThread(1, 10, service);
        CalculateThread t2 = new CalculateThread(5, 10, service);

        t1.start();
        t2.start();
    }
}
