package lesson11;

import java.io.*;

public class BufferedIOApp {
    public static void main(String[] args) {
        try (FileOutputStream fous = new FileOutputStream("dir/demo.txt");
                BufferedOutputStream out = new BufferedOutputStream(fous);
                ) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100_000; i++) {
                out.write(65);
            }
            System.out.println(System.currentTimeMillis() - start);
        } catch (IOException io) {
            io.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(new File("dir/demo.txt")))) {

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
