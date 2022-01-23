package lesson9.Fruits;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> box;
    private String type;
    private float weight;

    public Box() {
        box = new ArrayList<>();
        weight = 0;
    }

    public float getWeight() {
        return weight;
    }

    /**
     * Метод создан для выполнения задания 3d.
     */
    public float getWeightTwo() {
        return this.box.get(0).getWeight() * this.box.size();
    }

    public void fruitInBox(T fruit) {
        if (this.box.isEmpty()) {
            this.type = fruit.getClass().getName();
        }
        if (this.type.equals(fruit.getClass().getName())) {
            this.box.add(fruit);
            this.weight += fruit.getWeight();
        } else {
            System.out.println("Не в ту коробку суёшь.");
        }
    }

    /**
     * Поскольку в данном методе сравниваются одинаковые типы данных,
     * теоретически ошибка округления возникнуть не должна. Поэтому
     * я использовал обычный тип сравнения (==).
     */
    public void compare(Box<?> carton){
        if (this.weight == carton.weight) {
            System.out.println("Вес обоих коробок равен.");
        } else {
            System.out.println("Вес коробок отличается.");
        }
    }

    /**
     * Заметка от Жени:
     * public void replaceFruits(Box<?> carton){
     */
    public void replaceFruits(Box carton){
        if (!carton.box.isEmpty() && !this.type.equals(carton.type)) {
            System.out.println("Не мешай яблоки с апельсинами!");
            return;
        }
        for (int i = this.box.size()-1; i >= 0; i--) {
            carton.fruitInBox(this.box.get(i));
            this.weight -= this.box.get(i).getWeight();
            this.box.remove(i);
        }
        this.type = "";
        System.out.println("Ok!");
    }
}
