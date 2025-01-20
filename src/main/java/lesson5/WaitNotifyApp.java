package lesson5;

public class WaitNotifyApp {

    private Object lock = new Object();
    private char currentLetter = 'A';

    public static void main(String[] args) {
        WaitNotifyApp app = new WaitNotifyApp();

        Thread t1 = new Thread(() -> {
            app.printChar('A');
        });

        Thread t2 = new Thread(() -> {
            app.printChar('B');
        });

        t1.start();
        t2.start();
    }

    private void printChar(char a) {
        synchronized (lock) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentLetter != a) {
                        lock.wait();
                    }
                    System.out.print(a);
                    changeCurrentLetter(a);
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void changeCurrentLetter(char letter) {
        if (letter == 'A') currentLetter = 'B';
        else currentLetter = 'A';
    }
}
