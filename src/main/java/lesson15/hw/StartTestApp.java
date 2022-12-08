package lesson15.hw;

import lesson15.hw.Anotations.AfterSuite;
import lesson15.hw.Anotations.BeforeEach;
import lesson15.hw.Anotations.BeforeSuite;
import lesson15.hw.Anotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StartTestApp {

    public void start(Class testClass) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        Object testObject = objectInit(testClass);

        Method[] methods = testClass.getDeclaredMethods();

        List<Method> beforeSuitMethods = new ArrayList<>();
        List<Method> afterSuitMethods = new ArrayList<>();
        List<Method> beforeEachMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

        for (Method method: methods) {
            if(method.getAnnotation(BeforeSuite.class) != null) {
                beforeSuitMethods.add(method);
            }
            if(method.getAnnotation(AfterSuite.class) != null) {
                afterSuitMethods.add(method);
            }
            if(method.getAnnotation(BeforeEach.class) != null) {
                beforeEachMethods.add(method);
            }
            if(method.getAnnotation(Test.class) != null) {
                testMethods.add(method);
            }
        }

        if(beforeSuitMethods.size() > 1 || afterSuitMethods.size() > 1 ||
                beforeEachMethods.size() > 1) {
            throw new RuntimeException("Ошибка в расставлении аннотаций.");
        }

        beforeSuitMethods.get(0).invoke(testObject);

        for(int i = 10; i >= 1; i--) {
            for(Method method : testMethods) {
                if(method.getAnnotation(Test.class).priority() == i) {
                    beforeEachMethods.get(0).invoke(testObject);
                    method.invoke(testObject);
                }
            }
        }

        afterSuitMethods.get(0).invoke(testObject);
    }

    public Object objectInit(Class testClass) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = testClass.getDeclaredConstructor();
        Object testClassObject = constructor.newInstance();
        return testClassObject;
    }
}
