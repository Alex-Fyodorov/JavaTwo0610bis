package lesson15;

import java.lang.reflect.Field;

public class FieldReflectionApp {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class bikeClass = Bike.class;
        //Field[] fields = bikeClass.getFields(); не видит приватные поля
        Field[] fields = bikeClass.getDeclaredFields(); // Видит приватные поля.

        for (Field f : fields) {
            System.out.println("name: " + f.getName() +
                    ", type: " + f.getType().getSimpleName());
        }

        Bike bike = new Bike();
        System.out.println(bike);

        Field field = bikeClass.getField("model");
        field.set(bike, "Canyon"); // Поле не д.б. приватным.
        System.out.println(bike);

        Field yearField = bikeClass.getDeclaredField("year");
        yearField.setAccessible(true); // Позволяет менять приватные поля.
        yearField.set(bike, 2021);
        System.out.println(bike);
    }
}
