import lesson14.hw.ArrayChange;
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

public class ArrayChangeTest {

    ArrayChange arrayChange;

    @BeforeEach
    void init() {
        arrayChange = new ArrayChange();
    }

    @Test
    @DisplayName("массив пустой")
    void testArrayIsEmpty() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            List<Integer> list = new ArrayList<>();
            arrayChange.changing(list);
        });
    }

    @Test
    @DisplayName("нет четвёрок")
    void testNoFours() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            arrayChange.changing(list);
        });
    }

    @ParameterizedTest
    @MethodSource("initArrays")
    @DisplayName("преобразование массива")
    void testChanging(List<Integer> original, List<Integer> result) {
        Assertions.assertEquals(result, arrayChange.changing(original));
    }

    public static Stream<Arguments> initArrays() {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <= 50; i++) {
            List<Integer> original = new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            for (int j = 0; j <= random.nextInt(50); j++) {
                original.add(random.nextInt(10));
            }
            original.add(4);
            for (int n = 0; n <= random.nextInt(15); n++) {
                Integer num = random.nextInt(10);
                while (num == 4) {
                    num = random.nextInt(10);
                }
                original.add(num);
                result.add(num);
            }
            arguments.add(Arguments.arguments(original, result));
        }
        return arguments.stream();
    }
}
