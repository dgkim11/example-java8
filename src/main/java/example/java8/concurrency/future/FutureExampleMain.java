package example.java8.concurrency.future;

import java.util.concurrent.*;

/**
 * @author Dennis Kim
 */
public class FutureExampleMain {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureExampleMain theApp = new FutureExampleMain();
		theApp.execute();
	}

	private void execute() throws ExecutionException, InterruptedException {
		Callable<Integer> task = () -> {
			TimeUnit.SECONDS.sleep(3);
			return 123;
		};

		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(task);
		System.out.println("future is done? " + future.isDone());

		// 여기서 blocking이 일어난다. 쓰레드가 끝날때까지 기다린다.
		Integer result = future.get();
		System.out.println("future is done? " + future.isDone());
		System.out.println("result = " + result);

		executor.shutdownNow();
	}
}
