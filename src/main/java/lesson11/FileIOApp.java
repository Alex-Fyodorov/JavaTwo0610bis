package lesson11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileIOApp {
    public static void main(String[] args) {
        File file = new File("dir/demo.txt");
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write("Java".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream in = new FileInputStream("dir/demo.txt")) {
            while (in.available() > 0) {
                System.out.print((char)in.read());
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream out = new FileOutputStream(file)) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100_000; i++) {
                out.write(65);
            }
            System.out.println(System.currentTimeMillis() - start);

            byte[] arr = new byte[100_000];
            Arrays.fill(arr, (byte) 65);
            start = System.currentTimeMillis();
            out.write(arr);
            System.out.println(System.currentTimeMillis() - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
