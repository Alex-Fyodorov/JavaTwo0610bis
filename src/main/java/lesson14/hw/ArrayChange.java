package lesson14.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayChange {
    public static void main(String[] args) {
        ArrayChange arrayChange = new ArrayChange();
        List<Integer> list = arrayChange.init();
        System.out.println(list);
        System.out.println();
        List<Integer> result = arrayChange.changing(list);
        System.out.println(result);
    }

    public List<Integer> init() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        int capasity = random.nextInt(50);
        for (int i = 0; i <= capasity; i++) {
            list.add(random.nextInt(10));
        }
        return list;
    }

    public List<Integer> changing(List<Integer> originalArray) {
        if (originalArray.isEmpty()) {
            throw new RuntimeException("Исходный массив пуст.");
        }
        if (!originalArray.contains(4)) {
            throw new RuntimeException("Нет четвёрок в исходном массиве.");
        }
        List<Integer> bufferArray = new ArrayList<>();
        for (int i = originalArray.size() - 1; i >= 0; i--) {
            if (originalArray.get(i) == 4) {
                break;
            }
            bufferArray.add(originalArray.get(i));
        }
        List<Integer> resultArray = new ArrayList<>();
        for (int j = bufferArray.size() - 1; j >= 0; j--) {
            resultArray.add(bufferArray.get(j));
        }
        return resultArray;
    }
}
