package lesson15.hw;

import lesson15.hw.Anotations.*;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void init() {
        System.out.println("---");
        calculator = new Calculator();
    }

    @AfterSuite
    void tearDown() {
        System.out.println("All test finished");
    }

    @BeforeSuite
    void startUp() {
        System.out.println("Start testing");
    }

    @Test(priority = 8)
    void testAddition() {
        if (5 == calculator.sum(2, 3)) {
            System.out.println("testAddition - true");
        } else {
            System.out.println("testAddition - false");
        }
    }

    @Test(priority = 7)
    void testSubtraction() {
        if (3 == calculator.subtraction(7, 4)) {
            System.out.println("testSubtraction - true");
        } else {
            System.out.println("testSubtraction - false");
        }
    }

    @Test(priority = 6)
    void testMultiplication() {
        if (15 == calculator.multiply(3, 5)) {
            System.out.println("testMultiplication - true");
        } else {
            System.out.println("testMultiplication - false");
        }
    }

    @Test
    void testDivision() {
        if (3 == calculator.division(15, 5)) {
            System.out.println("testDivision - true");
        } else {
            System.out.println("testDivision - false");
        }
    }
}
