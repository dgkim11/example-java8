package example.java8.concurrency.future;

import java.util.concurrent.*;

/**
 * 참조 : https://m.blog.naver.com/2feelus/220714398973
 * @author Dennis Kim
 */
public class SimpleCompletableFutureExMain {
	Runnable task = () -> {
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("task is done");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

	public static void main(String[] args) throws Exception {
		SimpleCompletableFutureExMain theApp = new SimpleCompletableFutureExMain();
		theApp.asyncCall();
		//theApp.thenApplyCall();
//		theApp.thenRun();
	}

	private void asyncCall() throws InterruptedException, ExecutionException {
		CompletableFuture future = CompletableFuture.runAsync(task);
		System.out.println("future is done? " + future.isDone());
		future.get();
		System.out.println("future is done? " + future.isDone());
	}

	private void thenApplyCall() throws ExecutionException, InterruptedException {
		CompletableFuture future = CompletableFuture.runAsync(task).thenApply(s -> {
			System.out.println("thenApply called");
			return s;
		});
		System.out.println("future is done? " + future.isDone());
		future.get();
		System.out.println("future is done? " + future.isDone());

	}

	private void thenRun() throws Exception {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CompletableFuture.runAsync(task, executorService).thenRun(() -> System.out.println("future done"));
		System.out.println("executed future");
		executorService.shutdown();
	}
}
