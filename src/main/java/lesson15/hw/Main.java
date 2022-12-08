package lesson15.hw;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        StartTestApp startTest = new StartTestApp();
        Class calculatorClass = CalculatorTest.class;
        startTest.start(calculatorClass);
    }
}
