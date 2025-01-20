package lesson5;

public class DeadLock {

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new MyTread(object1, object2);
        Thread thread2 = new MyTread(object2, object1);
        thread1.start();
        thread2.start();
    }

    public static class MyTread extends Thread {

        private Object lock1;
        private Object lock2;

        public MyTread(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }
    }
}
