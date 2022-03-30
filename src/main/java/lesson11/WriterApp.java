package lesson11;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

//Reader и Writer работают не с цифрами, а с символами.
public class WriterApp {
    public static void main(String[] args) {
        try (Writer writer = new BufferedWriter(
                new FileWriter("dir/demo.txt"))
        ) {
            //writer.append();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
