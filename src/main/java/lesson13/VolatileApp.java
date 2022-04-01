package lesson13;

public class VolatileApp {

    public static volatile boolean isRunning = true;
    //Ключевое слово volatile, что данная переменная не попадёт в кэш
    //процессора. Это необходимо для потокобезопасности, но такие
    //переменные работают дольше. Тем не менее, благодаря volatile,
    //изменения переменной в одном потоке будут видны другому.

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (isRunning) {
                System.out.println("Still alive");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread.sleep(2000);
        isRunning = false;
        System.out.println("Stop");
    }
}
