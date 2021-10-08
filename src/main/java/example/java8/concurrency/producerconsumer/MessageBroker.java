package example.java8.concurrency.producerconsumer;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * wait and notify를 설명하는 가장 대표적인 예제라고 보면 된다. wait()은 쓰레드가 동작을 멈추고 대기 상태로
 * 들어가게 된다. notify() 메쏘드는 이렇게 대기 상태에 있는 쓰레드 중에서 하나를 깨워준다. 이 예제는 이러한 방식으로
 * producer/consumer의 중심에 있는 MessageBroker를 구현하였다.
 * Queue에 데이타를 넣으려고 할 때 buffer가 꽉 찼으면 버퍼에 여유가 생길때까지 기다린다.
 * 반대로, Queue에서 데이타를 빼려고 할때 queue가 비어 있다면 queue에 데이타가 들어올때까지 기다린다.
 *
 * User : Dongle (Dongkyun)
 * Date : 2016. 12. 27.
 */
public class MessageBroker {
    private static boolean isEmpty = true;
    private static boolean isFull = false;

    private static final int MAX_BUFFER_SIZE = 10;
    Queue<String> messages = new PriorityQueue<>();

    public synchronized void put(String message) throws InterruptedException {
        if(messages.size() >= MAX_BUFFER_SIZE) {
            isFull = true;
            System.out.println("[" + Thread.currentThread().getName() + "]Buffer has been full. waiting...");
            wait();
            isFull = false;
        }
        messages.add(message);

        if(isEmpty) notify();
    }

    public synchronized String get() throws InterruptedException {
        if(messages.size() == 0)    {
            isEmpty = true;
            System.out.println("[" + Thread.currentThread().getName() + "]Buffer is empty. waiting...");
            wait();
            isEmpty = false;
        }
        String message = messages.poll();
        if(isFull) notify();

        return message;
    }
}
