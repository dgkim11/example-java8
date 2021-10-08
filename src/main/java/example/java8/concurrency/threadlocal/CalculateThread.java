package example.java8.concurrency.threadlocal;

public class CalculateThread extends Thread {
    private int baseNumber;
    private int size;
    private CalculateService calculateService;

    public CalculateThread(int baseNumber, int size, CalculateService calculateService)  {
        this.baseNumber = baseNumber;
        this.size = size;
        this.calculateService = calculateService;
    }

    @Override
    public void run()   {
        int maxNumber = baseNumber + size;
        for(int i = baseNumber;i < maxNumber;i++) {
            calculateService.add(i);
        }

        System.out.println("sum is " + calculateService.value());
    }
}
