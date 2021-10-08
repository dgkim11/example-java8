package example.java8.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class SortTaskWithForkJoin extends RecursiveAction {
    private long[] array;
    private int lo, hi;
    private int THRESHOLD = 5000;

    SortTaskWithForkJoin(long[] array, int lo, int hi) {
        this.array = array; this.lo = lo; this.hi = hi;
    }

    SortTaskWithForkJoin(long[] array) {
        this(array, 0, array.length);
    }

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD)
            sortSequentially(lo, hi);
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new SortTaskWithForkJoin(array, lo, mid),
                    new SortTaskWithForkJoin(array, mid, hi));
            merge(lo, mid, hi);
        }
    }
    void sortSequentially(int lo, int hi) {
        System.out.println("[" + Thread.currentThread().getName() + "]Sort Sequentially. lo:" + lo + ", hi:" + hi);
        Arrays.sort(array, lo, hi);
    }

    void merge(int lo, int mid, int hi) {
        long[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++)
            array[j] = (k == hi || buf[i] < array[k]) ?
                    buf[i++] : array[k++];
    }
}
