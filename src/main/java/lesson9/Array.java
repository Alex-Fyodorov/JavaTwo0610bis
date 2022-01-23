package lesson9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * ДЗ 1 и 2.
 * Может я что-то не понял, но при чём тут вообще обобщения
 * и дженерики? Я постарался их приклеить, как смог.
 */
public class Array<T> {
    private T[] arr;

    public Array(T[] arr) {
        this.arr = arr;
    }

    public T[] getArr() {
        return arr;
    }

    /**
     * Метод, преобразующий массив в ArrayList.
     */
    public List<T> toArrList() {
        List<T> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, this.arr);
        return arrayList;
    }

    /**
     * Метод, меняющий местами в массиве два случайных элемента.
     */
    public void changePlace() {
        Random random = new Random();
        int rand1 = random.nextInt(this.arr.length);
        int rand2 = rand2(rand1, this.arr.length);
        T buffer = this.arr[rand1];
        this.arr[rand1] = this.arr[rand2];
        this.arr[rand2] = buffer;
    }

    public int rand2(int rand1, int length) {
        Random random = new Random(length);
        int rand2 = random.nextInt(length);
        while (rand2 == rand1) {
            rand2 = random.nextInt(length);
        }
        return rand2;
    }

    public static void main(String[] args) {
        //Почему-то эта строчка у меня компилируется только в таком виде,
        //хотя в методичке по-другому.
        Array<Integer> integer = new Array<>(new Integer[]{1, 2, 3, 4, 5});
        integer.changePlace();
        System.out.println(integer.toArrList().toString());
    }
}


