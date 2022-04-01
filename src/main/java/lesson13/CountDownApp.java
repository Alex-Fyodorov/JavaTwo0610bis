package lesson13;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownApp {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int w = i + 1;
            new Thread(() -> {
                try {
                    Thread.sleep((1 + random.nextInt(50)) * 100);
                    countDownLatch.countDown();
                    System.out.println("Поток " + w + " готов");
                } catch (Exception e) {

                }
            }).start();
        }
        //Ждём всех.
        countDownLatch.await();

        System.out.println("Работа завершена");
    }
}
