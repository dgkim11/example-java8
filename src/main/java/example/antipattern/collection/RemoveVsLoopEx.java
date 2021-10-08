package example.antipattern.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RemoveVsLoopEx {
    public static void main(String[] args)  {
        RemoveVsLoopEx theApp = new RemoveVsLoopEx();
        theApp.executeByLoop();
        theApp.executeByRemoveLinkedList();
        theApp.executeByRemoveArrayList();
    }

    private void executeByLoop()    {
        List<String> items = new ArrayList<>();
        for(int i = 0;i < 1000000;i++)
            items.add("Item " + i);

        long start = System.nanoTime();
        for(String item : items)    {
        }
        long end = System.nanoTime();

        System.out.println("normal loop elapsed time : " + TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS) + " ms");
    }

    private void executeByRemoveArrayList()    {
        List<String> items = new ArrayList<>();
        for(int i = 0;i < 1000000;i++)
            items.add("Item " + i);

        long start = System.nanoTime();
        while(items.isEmpty() == false)    {
            items.remove(0);
        }
        long end = System.nanoTime();

        System.out.println("arraylist.remove loop elapsed time : " + TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS) + " ms");
    }
    private void executeByRemoveLinkedList()    {
        List<String> items = new LinkedList<>();
        for(int i = 0;i < 1000000;i++)
            items.add("Item " + i);

        long start = System.nanoTime();
        items.forEach(s -> s = s + "1");
        long end = System.nanoTime();

        System.out.println("linkedlist.remove loop elapsed time : " + TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS) + " ms");
    }

}
