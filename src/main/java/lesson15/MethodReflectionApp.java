package lesson15;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodReflectionApp {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class bikeClass = Bike.class;

        Method[] declaredMethods = bikeClass.getDeclaredMethods();
        for (Method method : declaredMethods) {

            if (method.getAnnotation(SimpleAnnotation.class) != null) {
                System.out.print("method with annotation ");
            }

            System.out.println("name = " + method.getName() +
                    ", type = " + method.getReturnType().getSimpleName() +
                    ", parameters = " + Arrays.toString(method.getParameterTypes()));
        }

        Method method = bikeClass.getDeclaredMethod("setYearAndModel", int.class, String.class);

        //Annotation annotation = method.getDeclaredAnnotation(NotSimpleAnnotation.class);
        NotSimpleAnnotation annotation = method.getDeclaredAnnotation(NotSimpleAnnotation.class);
        System.out.println("name = " + annotation.name() +
                ", value = " + annotation.value());

        Bike bike = new Bike();
        System.out.println(bike);
        method.setAccessible(true);
        method.invoke(bike, 2021, "Pinarello");
        System.out.println(bike);
    }
}
