package lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends HashMap {

    public void add (String name, String number) {
        Set<String> contacts = (Set) this.getOrDefault(name, new HashSet<>());
        contacts.add(number);
        this.put(name, contacts);
    }

    public Set<String> get (String name){
        return (Set<String>) this.getOrDefault(name, new HashSet<>());

    }
}

