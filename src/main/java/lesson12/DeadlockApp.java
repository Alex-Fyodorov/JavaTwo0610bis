package lesson12;

public class DeadlockApp {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new ThreadOne().start();
        new ThreadTwo().start();
    }

    private static class ThreadOne extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " locked lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting for lock2");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "locked lock2");
                }
            }
        }
    }

    private static class ThreadTwo extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " locked lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting for lock1");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "locked lock1");
                }
            }
        }
    }
}
