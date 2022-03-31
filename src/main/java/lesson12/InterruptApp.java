package lesson12;

public class InterruptApp {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //boolean isInterrupted = false;
            for (int i = 0; i < 5; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                System.out.println(i + 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //isInterrupted = true;
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        //thread.stop();
        thread.interrupt();
    }
}
