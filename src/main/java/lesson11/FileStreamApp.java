package lesson11;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileStreamApp {
    public static void main(String[] args) {
        byte[] buf = new byte[20];
        try (FileInputStream in = new FileInputStream("dir/demo.txt")) {
            int count;
            while ((count = in.read(buf)) > 0) {
                for (int i = 0; i < count; i++) {
                    System.out.print((char) buf[i]);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

        System.out.println("\n");

        try (InputStream in = new BufferedInputStream(new FileInputStream("dir/demo.txt"))) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char) x);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
