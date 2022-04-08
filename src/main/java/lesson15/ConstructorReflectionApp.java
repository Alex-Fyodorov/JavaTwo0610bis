package lesson15;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ConstructorReflectionApp {
    public static void main(String[] args) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Class bikeClass = Bike.class;

        Constructor[] declaredConstructors = bikeClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(Arrays.toString(declaredConstructor.getParameterTypes()));
        }

        Constructor constructor = bikeClass.getDeclaredConstructor(String.class,
                String.class, int.class);

        Object bike = constructor.newInstance("Canyon", "12345", 2018);
        System.out.println(bike);
        Bike oldBike = (Bike) constructor.newInstance(
                "Школьник", "0001", 1976);
        // При создании объектов через рефлексию необходимо приведение типов,
        // если нужкн конкретный тип.
        System.out.println(oldBike);
    }
}
