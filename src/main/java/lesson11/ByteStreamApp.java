package lesson11;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteStreamApp {
    public static void main(String[] args) {
        byte[] arr = {65, 66, 67};
        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        int x = 0;
        while (in.available() > 0) {
            x = in.read();
            System.out.println(x);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(65);
        out.write(66);
        System.out.println(Arrays.toString(out.toByteArray()));
    }
}
