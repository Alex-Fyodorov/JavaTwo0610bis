package lesson5;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionApp {
    public static void main(String[] args) throws InterruptedException {
        Counter2 counter = new Counter2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Counter = " + counter.getValue());
    }

    public static class Counter {
        private int value;

        public int getValue() {
            return value;
        }

//        public void increment() {
//            value++;
//        }

        public synchronized void increment() {
            value++;
        }
    }

    public static class Counter2 {
        private AtomicInteger value;

        public Counter2() {
            this.value = new AtomicInteger(0);
        }

        public AtomicInteger getValue() {
            return value;
        }

        public void increment() {
            value.incrementAndGet();
        }
    }
}
