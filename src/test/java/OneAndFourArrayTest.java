import lesson14.hw.OneAndFourArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class OneAndFourArrayTest {

    OneAndFourArray oneAndFourArray;
    Random random = new Random();

    @BeforeEach
    void init() {
        oneAndFourArray = new OneAndFourArray();
    }

    @Test
    @DisplayName("только единички")
    void testOnlyOne() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<= random.nextInt(5) + 2; i++) {
            list.add(1);
        }
        Assertions.assertFalse(oneAndFourArray.arrayChecking(list));
    }

    @Test
    @DisplayName("только четвёрки")
    void testOnlyFour() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<= random.nextInt(5) + 2; i++) {
            list.add(4);
        }
        Assertions.assertFalse(oneAndFourArray.arrayChecking(list));
    }

    @Test
    @DisplayName("пустой массив")
    void testEmptyArray() {
        Assertions.assertFalse(oneAndFourArray.arrayChecking(new ArrayList<>()));
    }

    @ParameterizedTest
    @MethodSource("arrayWithExtraDigitInit")
    @DisplayName("лишняя цифра")
    void testExtraDigit(List<Integer> list) {
        Assertions.assertFalse(oneAndFourArray.arrayChecking(list));
    }

    @ParameterizedTest
    @MethodSource("rightArrayInit")
    @DisplayName("правильный массив")
    void testRightArray(List<Integer> list) {
        Assertions.assertTrue(oneAndFourArray.arrayChecking(list));
    }

    public static Stream<Arguments> arrayWithExtraDigitInit() {
        return makeArray(true);
    }

    public static Stream<Arguments> rightArrayInit() {
        return makeArray(false);
    }

    public static Stream<Arguments> makeArray(boolean extraDigit) {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        int num = 0;
        for (int j = 0; j <= 50; j++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(4);
            for (int i = 0; i <= random.nextInt(10) + 5; i++) {
                num = random.nextInt(10);
                if (num % 2 == 0) {
                    list.add(4);
                } else {
                    list.add(1);
                }
            }
            if (extraDigit) {
                while (num == 1 || num == 4) {
                    num = random.nextInt(10);
                }
                list.set(random.nextInt(list.size()), num);
            }
            arguments.add(Arguments.arguments(list));
        }
        return arguments.stream();
    }
}
