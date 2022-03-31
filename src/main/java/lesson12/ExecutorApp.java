package lesson12;

import java.util.concurrent.*;

public class ExecutorApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));

        Future<?> future = executorService.submit(() -> System.out.println("smth"));
        System.out.println(future.isDone());

        Future<String> stringFuture = executorService.submit(new MyCallable());
        System.out.println(stringFuture.get());

        executorService.shutdown(); //перестаёт принимать новые задачи
        //executorService.shutdownNow(); //этот вдобавок всем рабочим рассылает интеррапты
        //если не вызвать shutdown, приложение никогда не завершится
    }

    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "string from callable";
        }
    }
}
