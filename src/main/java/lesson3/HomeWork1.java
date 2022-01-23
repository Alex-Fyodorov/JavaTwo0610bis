package lesson3;

import java.util.Map;
import java.util.HashMap;

public class HomeWork1 {
    public static void main(String[] args) {
        String[] arr = new String[] {
                "Харе", "Кришна", "Харе", "Кришна",
                "Кришна", "Кришна", "Харе", "Харе",
                "Харе", "Рама", "Харе", "Рама",
                "Рама", "Рама", "Харе", "Харе"
        };
        Map<String, Integer> hm = new HashMap<>();

        for (String key: arr) {
            if (hm.containsKey(key)){
                hm.put(key, hm.get(key) + 1);
            } else {
                hm.put(key, 1);
            }
        }
        System.out.println(hm.toString());
    }
}
