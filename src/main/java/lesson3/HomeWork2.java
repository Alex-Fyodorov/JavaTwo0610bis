package lesson3;

import java.util.Map;
import java.util.Set;

public class HomeWork2 {

    public static void main(String[] args) {
        Map<String, Set> phoneBook = new Contacts();

        ((Contacts)phoneBook).add("Петрович", "+7-900-000");
        ((Contacts)phoneBook).add("Василич", "+7-911-111");
        ((Contacts)phoneBook).add("Саныч", "+7-922-222");
        ((Contacts)phoneBook).add("Василич", "+7-933-333");

        System.out.println(phoneBook.toString());

        System.out.println(((Contacts) phoneBook).get("Василич ").toString());
    }
}