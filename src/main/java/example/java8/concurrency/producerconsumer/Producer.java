package example.java8.concurrency.producerconsumer;

/**
 * User : Dongle (Dongkyun)
 * Date : 2016. 12. 27.
 */
public class Producer implements Runnable   {
    private MessageBroker broker;

    public Producer(MessageBroker broker)   {
        this.broker = broker;
    }

    @Override
    public void run() {
        int count = 0;

        while(true) {
            try {
                String message = "message - " + count + "from " + Thread.currentThread().getName();
                broker.put(message);
                System.out.println("[" + Thread.currentThread().getName() + "] producer sent : " + message);
                Thread.sleep(1000);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
