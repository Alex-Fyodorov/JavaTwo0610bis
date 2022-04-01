package lesson13.hw;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static String winner = "";
    public static CountDownLatch start = new CountDownLatch(CARS_COUNT);
    public static CountDownLatch finish = new CountDownLatch(CARS_COUNT);
    public static CountDownLatch win = new CountDownLatch(1);
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
    public static Semaphore semaphore = new Semaphore((int) Math.ceil(CARS_COUNT/2));


    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Сar[] cars = new Сar[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Сar(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            start.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            win.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Победил: " + winner);
            finish.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}