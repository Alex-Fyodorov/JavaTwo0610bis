import lesson14.Battery;
import lesson14.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CalculatorTest {

    Calculator calculator;
    Battery battery = Mockito.mock(Battery.class);

    void testWithBattery() {
        Mockito.doReturn(50).when(battery.getPercent());
    }

    //Библиотека мокито ставит заглушки, при этом методы работают так:
    //void - не делает ничего, все остальные возвращают null.

    @BeforeEach
    void init() {
        System.out.println("initialisation");
        calculator = new Calculator(battery);
    }

    @AfterEach
    void tearDown() {
        System.out.println("test finished");
    }

    @Test
    @DisplayName("сложение")
    void testAddition() {
        Assertions.assertEquals(10, calculator.sum(3, 7),
                "sum should be equal 10");
    }

    @Test
    @DisplayName("умножение")
    //@Disabled Выключение теста
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS) // проверка на скорость выполнения
    void testMultiplication() {
        Assertions.assertEquals(15, calculator.multiply(3, 5),
                "result must be 15");
    }

    @Test
    @DisplayName("деление с исключением")
    void testDivisionWithException() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.division(10, 0),
                "на ноль делить нельзя");
    }

    @CsvSource({
            "1, 2, 3",
            "3, 2, 5",
            "13, 2, 15"
    })
    @ParameterizedTest
    @DisplayName("сложение мульти")
    void testAdditionMultiple(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.sum(a, b));
    }

    @MethodSource("dataForAddition")
    @ParameterizedTest
    @DisplayName("сложение методом")
    void testAdditionWithMethod(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.sum(a, b));
    }

    public static Stream<Arguments> dataForAddition() {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int a = random.nextInt(1000);
            int b = random.nextInt(1000);
            arguments.add(Arguments.arguments(a, b, a + b));
        }
        return arguments.stream();
    }
}
