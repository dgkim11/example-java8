package example.java8.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExMain {
    public static void main(String[] args)  {
        ThreadPoolExMain theApp = new ThreadPoolExMain();
        theApp.executeFixedThreadPool();
        //theApp.executeForkJoinPool();

    }

    /**
     * 10개의 fixed 쓰레드가 100개의 아이템을 처리한다. 즉, 천개의 item을 처리한다.
     */
    private void executeFixedThreadPool()  {
        long startTime = System.currentTimeMillis();
        System.out.println("Execute Runnable Tasks");
        Callable<Integer> callableTask = () -> {
            int itemCount = (int)(Math.random() * 100) + 1;
            System.out.println(Thread.currentThread().getName() + " is executing... itemCount:" + itemCount);
            for(int i = 0;i < itemCount;i++)   {
                try { Thread.sleep(100); }catch(Exception e) {}
                //System.out.println(Thread.currentThread().getName() + ". i:" + i);
            }
            System.out.println(Thread.currentThread().getName() + " finished");
            return itemCount;
        };
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        List<Callable<Integer>> tasks = new ArrayList<>();
        for(int i = 0;i < 10;i++)   {
            tasks.add(callableTask);
        }
        try {
            fixedThreadPool.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("elaspsed time : " + (endTime - startTime));

        fixedThreadPool.shutdown();
    }

    /**
     * 동일하게 천개의 item에 대해서 forkJoin thread pool로 처리한다. fork를 하는 기준을 100개로 함으로써
     * 위에서 fixed thread pool과 동일하게 10개의 thread를 사용하게 된다.
     */
    private void executeForkJoinPool()  {
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int[] items = new int[1000];
        for(int i = 0;i < 1000;i++) {
            items[i] = i;
        }
        ForkJoinTask task = new ForkJoinTask(items);
        forkJoinPool.invoke(task);

        long endTime = System.currentTimeMillis();

        System.out.println("elaspsed time : " + (endTime - startTime));
    }

    private static class ForkJoinTask extends RecursiveAction   {
        private int[] items;
        private static final int THRESHOLD = 20;
        private int lo;
        private int hi;
        public ForkJoinTask(int[] items, int lo, int hi)    {
            this.items = items;
            this.lo = lo;
            this.hi = hi;
        }
        public ForkJoinTask(int[] items)    {
            this.items = items;
            lo = 0;
            hi = items.length;
        }

        @Override
        protected void compute() {
            if(hi - lo > THRESHOLD)   {
                int mid = (lo + hi) >>> 1;
                invokeAll(new ForkJoinTask(items, lo, mid), new ForkJoinTask(items, mid, hi));
                int[] merged = new int[hi-lo];
                for(int i = 0;i < hi - lo;i++)  {
                    merged[i] = items[i];
                }
            }
            else    {
                for(int i = lo;i < hi;i++)  {
                    try { Thread.sleep(100); }catch(Exception e) {}
                }
                System.out.println("exectued. lo = " + lo + ", hi = " + hi);
            }
        }
    }
}
