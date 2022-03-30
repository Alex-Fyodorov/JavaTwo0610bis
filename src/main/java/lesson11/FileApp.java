package lesson11;

import java.io.File;
import java.io.IOException;

public class FileApp {

    public static void main(String[] args) throws IOException {
        File parentDir = new File("dir");
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }
        File file = new File(parentDir, "demo.txt");

        System.out.println("File exists " + file.exists());

        if (!file.exists()) {
            file.createNewFile();
        }

        System.out.println("Is file - " + file.isFile() +
                ", is directory - " + file.isDirectory());
        System.out.println(file.getParent());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.length());
    }
}
