package lesson12;

public class ThreadApp {

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Do smth");
        }
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Do smth");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();
        Thread thread2 = new MyThread();
        thread2.start();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Do smth");
            }
        });
        thread3.start();
    }
}
