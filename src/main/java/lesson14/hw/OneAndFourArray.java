package lesson14.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OneAndFourArray {
    public static void main(String[] args) {
        OneAndFourArray array = new OneAndFourArray();
        List<Integer> list = array.init();
        System.out.println(list);
        System.out.println(array.arrayChecking(list));
    }

    public List<Integer> init() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        int num = 0;
        for (int i = 0; i <= random.nextInt(10); i++) {
            num = random.nextInt(20);
            if (num == 10) {
                list.add(random.nextInt(10));
            } else {
                if (num % 2 == 0) {
                    list.add(4);
                } else {
                    list.add(1);
                }
            }
        }
        return list;
    }

    public boolean arrayChecking(List<Integer> list) {
        if (list.isEmpty()) {
            return false;
        }
        if (!list.contains(4) || !list.contains(1)) {
            return false;
        }
        for (int i = 0; i<= list.size() - 1; i++) {
            if (list.get(i) != 1 && list.get(i) != 4) {
                return false;
            }
        }
        return true;
    }
}
