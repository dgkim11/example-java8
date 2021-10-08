package example.java8.concurrency.producerconsumer;

/**
 * User : Dongle (Dongkyun)
 * Date : 2016. 12. 27.
 */
public class Consumer implements Runnable   {
    private MessageBroker broker;

    public Consumer(MessageBroker broker)  {
        this.broker = broker;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String message = broker.get();
                System.out.println("[" + Thread.currentThread().getName() + "]Consumer : " + message);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
