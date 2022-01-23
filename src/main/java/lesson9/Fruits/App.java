package lesson9.Fruits;

public class App {
    public static void main(String[] args) {
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Apple a3 = new Apple();
        Orange o1 = new Orange();
        Orange o2 = new Orange();
        Box b1 = new Box();
        Box b2 = new Box();
        Box b3 = new Box();


        b1.fruitInBox(a1);
        b1.fruitInBox(a2);
        b1.fruitInBox(a3);
        b2.fruitInBox(o1);
        b2.fruitInBox(o2);

        System.out.println(b1.getWeight());
        System.out.println(b1.getWeightTwo());
        System.out.println(b2.getWeight());
        System.out.println(b2.getWeightTwo());

        b1.compare(b2);

        b1.replaceFruits(b3);
        b2.replaceFruits(b3);

        System.out.println(b1.getWeight());
        System.out.println(b2.getWeight());
        System.out.println(b3.getWeight());
    }
}
