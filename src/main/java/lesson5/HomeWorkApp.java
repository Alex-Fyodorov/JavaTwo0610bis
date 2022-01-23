package lesson5;

import java.util.Arrays;

public class HomeWorkApp {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE/2;

    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
    }

    public static void firstMethod(){
        float[] array = new float[SIZE];
        Arrays.fill(array, 1f);
        long startTime = System.currentTimeMillis();
        calculation(array, 0);
        System.out.println("Время работы однопотокового метода: " +
                (System.currentTimeMillis() - startTime) + "мс.");
    }

    public static void secondMethod() throws InterruptedException {
        float[] primaryArray = new float[SIZE];
        Arrays.fill(primaryArray, 1f);
        float[] leftHalf = new float[HALF];
        float[] rightHalf = new float[HALF];
        long startTime = System.currentTimeMillis();
        System.arraycopy(primaryArray, 0, leftHalf, 0, HALF);
        System.arraycopy(primaryArray, HALF, rightHalf, 0, HALF);

        Thread thread1 = new Thread(() -> {
            calculation(leftHalf, 0);
        });

        Thread thread2 = new Thread(() -> {
            calculation(rightHalf, HALF);
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        float[] mergedArray = new float[SIZE];
        System.arraycopy(leftHalf, 0, mergedArray, 0, HALF);
        System.arraycopy(rightHalf, 0, mergedArray, HALF, HALF);
        System.out.println("Время работы двухпотокового метода: " +
                (System.currentTimeMillis() - startTime) + "мс.");
    }

    public static void calculation (float[] arr, int rider){
        for (int i = 0; i < arr.length; i++){
            float j = (float) (i + rider);
            arr[i] = (float) (arr[i] * Math.sin(0.2f +
                    j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
        }
    }
}
