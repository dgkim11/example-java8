package example.java8.concurrency.interruption;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Java의 interruption에 대한 이해를 위한 예제이다. 기본적으로 interrupt를 받은 쓰레드는 작업을 멈추어야 한다.
 * 일반적인 use case는 쓰레드가 과도하게 오래동안 수행되어 수행을 멈추게 한다던가, 시스템이 종료를 위해서 현재
 * 작업중인 쓰레드에게 interrupt를 요청하게 된다. 여기 예제에서는 3초 이상 작업이 걸리는 쓰레드에게 interrupt를 요청하여
 * 시스템이 종료될 수 있게 한다. interrupt를 받는 방법은 크게 두가지이다. 이 예제에서처럼 메쏘드 자체가 InterruptedException
 * 을 throw하는 경우에는 try-catch로 받을 수 있고, 또는 오랜 작업을 하는 동안 중간 중간에 Thread.interrupted()를
 * 호출하여 자신이 interrupt 되었는지를 확인할 수 있다.
 *
 */
public class InterruptionExMain {
    public static void main(String[] args)  {
        InterruptionExMain theApp = new InterruptionExMain();
        theApp.execute();
    }

    private void execute()  {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(new MyThread(1));
        executor.execute(new MyThread(2));
        executor.execute(new MyThread(5));
        executor.execute(new WorkingLongThread());

        try { Thread.sleep(3000); }catch(InterruptedException e) {}

        executor.shutdownNow();
    }

    private static class MyThread extends Thread    {
        private int iterationCount;

        public MyThread(int iterationCount)    {
            this.iterationCount = iterationCount;
        }

        public void run()   {
            for(int i =0;i < iterationCount;i++)    {
                System.out.println("working..." + Thread.currentThread().getName());
                try { Thread.sleep(1000); }catch(InterruptedException e)   {
                    System.out.println("INTERRUPTED !!!-" + Thread.currentThread().getName());
                    break;
                }
            }
        }
    }

    private static class WorkingLongThread extends Thread   {
        public void run()   {
            while(true) {
                File dir = new File(".");
                dir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return true;
                    }
                });
                if(Thread.interrupted())    {
                    System.out.println("INTERRUPTED !!!-" + Thread.currentThread().getName());
                    break;
                }
            }
        }
    }
}
