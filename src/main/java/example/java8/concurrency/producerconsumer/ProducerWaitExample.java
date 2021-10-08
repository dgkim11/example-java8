package example.java8.concurrency.producerconsumer;

/**
 * User : Dongle (Dongkyun)
 * Date : 2016. 12. 27.
 */
public class ProducerWaitExample {
    public static void main(String[] args)  {
        ProducerWaitExample theApp = new ProducerWaitExample();
        theApp.example();
    }

    private void example()  {
        MessageBroker broker = new MessageBroker();
        Thread p1 = new Thread(new Producer(broker));
        Thread p2 = new Thread(new Producer(broker));
        Thread p3 = new Thread(new Producer(broker));
        p1.start();
        p2.start();
        p3.start();
        Thread c1 = new Thread(new Consumer(broker));
        c1.start();
    }
}
