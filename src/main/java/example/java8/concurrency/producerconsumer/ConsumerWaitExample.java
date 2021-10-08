package example.java8.concurrency.producerconsumer;

/**
 * User : Dongle (Dongkyun)
 * Date : 2016. 12. 27.
 */
public class ConsumerWaitExample {
    public static void main(String[] args)  {
        ConsumerWaitExample theApp = new ConsumerWaitExample();
        theApp.example();
    }

    private void example()  {
        MessageBroker broker = new MessageBroker();
        Thread p1 = new Thread(new Producer(broker));
        p1.start();
        Thread c1 = new Thread(new Consumer(broker));
        Thread c2 = new Thread(new Consumer(broker));
        c1.start();
        c2.start();
    }
}
