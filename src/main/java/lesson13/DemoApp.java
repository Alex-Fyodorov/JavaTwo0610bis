package lesson13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DemoApp {
    public static void main(String[] args) {
        //Массив, синхронизированный на чтение. Читают все, кто хотят,
        //а при записи создаётся новая копия.
        List<Integer> list1 = new CopyOnWriteArrayList<>();

        //Обёртка для синхронизации обычного ArrayList.
        List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());

        //ConcurrentHashMap при синхронизации блокирует не весь объект,
        //а только один бакет.
        Map<String, String> map = new ConcurrentHashMap<>();
    }
}
