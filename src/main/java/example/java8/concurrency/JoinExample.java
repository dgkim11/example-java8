package example.java8.concurrency;

/**
 * join() 메쏘드는 해당 쓰레드가 끝날때까지 기다린다. 따라서, 쓰레드를 수행시킨 후에 그 쓰레드가 종료되기를
 * 기다려야 한다면 join() 메쏘드를 호출하면 된다.
 *
 * User : Dongle (Dongkyun)
 * Date : 2016. 12. 27.
 */
public class JoinExample {
    public static void main(String[] args)  {
        JoinExample theApp = new JoinExample();
        theApp.joinExample();
    }

    private void joinExample()  {
        System.out.println("#### joinExample ####");
        System.out.println("executing a thread");
        MyThread t = new MyThread();
        t.start();
        System.out.println("waiting for the thread completion....");
        try {
            t.join();
            System.out.println("The thread finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class MyThread extends Thread    {
        @Override
        public void run()   {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
