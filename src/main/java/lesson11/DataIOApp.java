package lesson11;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOApp {
    public static void main(String[] args) {
        try (DataOutputStream out = new DataOutputStream(
                new FileOutputStream("dir/demo.txt")
        )) {
            out.writeUTF("We love Java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
