package example.java8.concurrency.forkjoin;

import java.util.Random;
import java.util.concurrent.*;

/**
 *
 * @author Dennis Kim
 */
public class SortTaskExMain {
	public static void main(String[] args)  {
        SortTaskExMain theApp = new SortTaskExMain();
        theApp.sortTaskWithForkJoin();
    }

    private void sortTaskWithForkJoin() {
        long[] array = generateArray();
        SortTaskWithForkJoin myAction = new SortTaskWithForkJoin(array);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(myAction);

        validateSorted(array);
    }

    private long[] generateArray()    {
        int size = 100000;
        Random random = new Random();
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.abs(random.nextInt());
        }

        return array;
    }

    private void validateSorted(long[] array)   {
        for(int i = 1;i < array.length;i++)   {
            if(array[i] < array[i-1])   {
                System.out.println("Wrong sorted !!!");
                break;
            }
            else    {
                System.out.print(".");
                if(i % 100 == 0) System.out.println("");
            }
        }
    }
}