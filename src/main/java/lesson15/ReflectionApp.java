package lesson15;

import lesson7.client.Client;

import java.lang.reflect.Modifier;

public class ReflectionApp {

    public static void main(String[] args) {
        // Класс по сути является объектом, в данном случае, строка.
        Class clazz = "Java".getClass();

        // Можно запросить напрямую:
        Class strClass = String.class;
        Class intClass = Integer.class;

        Class smallIntClass = int.class;
        Class smallIntArrayClass = int[].class;

        System.out.println(clazz.getName() + " " + clazz.getSimpleName());

        int modifiers = clazz.getModifiers();
        if (Modifier.isFinal(modifiers)) {
            System.out.println(clazz.getName() + " is final");
        }

        if (Modifier.isAbstract(modifiers)) {
            System.out.println(clazz.getName() + " is abstract");
        }

        if (Modifier.isPublic(modifiers)) {
            System.out.println(clazz.getName() + " is public");
        }

        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.getSimpleName());
        }

        Class superClass = clazz.getSuperclass();
        System.out.println(superClass.getSimpleName());
    }
}
