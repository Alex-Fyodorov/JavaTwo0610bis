package lesson13;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierApp {

    public static Random random = new Random();

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            final int w = i + 1;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + w + " готовится");
                    Thread.sleep((1 + random.nextInt(50)) * 100);
                    System.out.println("Поток " + w + " прошёл 1 барьер");
                    cyclicBarrier.await();

                    System.out.println("Поток " + w + " готовится");
                    Thread.sleep((1 + random.nextInt(50)) * 100);
                    System.out.println("Поток " + w + " прошёл 2 барьер");
                    cyclicBarrier.await();

                    System.out.println("Гонка закончена");
                } catch (Exception e) {

                }
            }).start();
        }
    }
}
