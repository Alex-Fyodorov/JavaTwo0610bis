package lesson12;

public class ABCPrint {
    private final Object monitor = new Object();
    private char letter = 'A';

    public static void main(String[] args) {
        ABCPrint abc = new ABCPrint();
        Thread threadOne = new Thread(() -> {
            abc.print('A', 'B');
        });
        Thread threadTwo = new Thread(() -> {
            abc.print('B', 'C');
        });
        Thread threadThree = new Thread(() -> {
            abc.print('C', 'A');
        });
        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }

    private void print(char letterOne, char letterTwo) {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                while (letter != letterOne) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(letter);
                if (letter == 'C') {
                    System.out.println();
                }
                letter = letterTwo;
                monitor.notifyAll();
            }
        }
    }
}
