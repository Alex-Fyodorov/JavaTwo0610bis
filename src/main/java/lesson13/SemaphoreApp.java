package lesson13;

import java.util.concurrent.Semaphore;

public class SemaphoreApp {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            final int w = i + 1;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + w + " перед семафором");
                    semaphore.acquire();
                    System.out.println("Поток " + w + " получил доступ к ресурсу");
                    Thread.sleep(500);
                } catch (Exception e) {

                } finally {
                    System.out.println("Поток " + w + " освободил ресурс");
                    semaphore.release();
                }
            }).start();
        }
    }
}
