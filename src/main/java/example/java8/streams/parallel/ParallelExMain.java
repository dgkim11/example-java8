package example.java8.streams.parallel;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelExMain {
    public static void main(String[] args)  {
        ParallelExMain theApp = new ParallelExMain();
        long limit = 10000000;
        theApp.executeInTraditional(limit);
        theApp.executeInStream(limit);
        theApp.executeInParallelStream(limit);
        theApp.executeRangedSum(limit);
        theApp.executeRangedSumWithParallel(limit);
    }

    private void executeInTraditional(Long limit)  {
        long start = System.nanoTime();
        long result = 0;
        for(Long i = 0L;i <= limit;i++)   {
            result += i;
        }
        System.out.println("executeInTraditional : " + result);
        long end = System.nanoTime();
        System.out.println("elapsed: " + (end - start) / 1000000 + " ms");
    }

    private void executeInStream(long limit)  {
        long start = System.nanoTime();
        // 아래와 같은 코드는 Long이란 object와 long이라는 primitive type간에 boxing/unboxing이 계속 일어나게 된다.
        // 따라서, 성능이 매우 떨어지게 된다.
        Optional<Long> result = Stream.iterate(1L, i -> i + 1)
                .limit(limit)
                .reduce(Long::sum);
        result.ifPresent(value -> System.out.println("executeInStream : " + value));
        long end = System.nanoTime();
        System.out.println("elapsed: " + (end - start) / 1000000 + " ms");
    }

    private void executeInParallelStream(long limit)  {
        long start = System.nanoTime();
        Optional<Long> result = Stream.iterate(1L, i -> i + 1)
                .limit(limit)
                .parallel()
                .reduce(Long::sum);
        result.ifPresent(value -> System.out.println("executeInParallelStream : " + value));
        long end = System.nanoTime();
        System.out.println("elapsed: " + (end - start) / 1000000 + " ms");
    }

    private void executeRangedSum(long limit)  {
        long start = System.nanoTime();
        // 아래와 같은 방식은 boxing/unboxing이 일어나지 않아 속도가 빨라진다.
        OptionalLong result = LongStream.rangeClosed(1, limit).reduce(Long::sum);
        long end = System.nanoTime();
        result.ifPresent(v -> System.out.println("executeRangedSum:" + result));
        System.out.println("elapsed: " + (end - start) / 1000000 + " ms");
    }

    private void executeRangedSumWithParallel(long limit)  {
        long start = System.nanoTime();
        OptionalLong result = LongStream.rangeClosed(1, limit).parallel().reduce(Long::sum);
        long end = System.nanoTime();
        result.ifPresent(v -> System.out.println("executeRangedSumWithParallel:" + result));
        System.out.println("elapsed: " + (end - start) / 1000000 + " ms");
    }


}
