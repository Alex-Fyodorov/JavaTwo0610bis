package lesson12;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterApp {

    private static class Counter {
        private int value;

        public Counter(int value) {
            this.value = value;
        }

        public Counter() {
            this.value = 0;
        }

        public int getValue() {
            return value;
        }

        public synchronized void increment() {
            this.value ++;
        }
    }

    private static class DoudleCounter {
        private Object lock1 = new Object();
        private Object lock2 = new Object();

        private int first;
        private int second;

        public DoudleCounter() {
            this.first = 0;
            this.second = 0;
        }

        @Override
        public String toString() {
            return "DoudleCounter{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }

        public void incrementFirst() {
            synchronized (lock1) {
                this.first ++;
            }
        }

        public void incrementSecond() {
            synchronized (lock2) {
                this.second++;
            }
        }
    }

    /**
     * Способы синхронизировать процессы:
     * 1. synchronized
     * 2. класс AtomicInteger
     */
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        DoudleCounter doudleCounter = new DoudleCounter();

        AtomicInteger atomicInteger = new AtomicInteger(0);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    counter.increment();
                    atomicInteger.incrementAndGet();
                    doudleCounter.incrementFirst();
                    if (i % 2 == 0) {
                        doudleCounter.incrementSecond();
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getValue());
        System.out.println(atomicInteger.get());
        System.out.println(doudleCounter.toString());
    }
}
