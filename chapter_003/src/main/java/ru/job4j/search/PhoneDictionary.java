package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {
    public List<Person> persons = new ArrayList<Person>();

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.getName().contains(key)) {
                result.add(person);
            } else if (person.getSurname().contains(key)) {
                result.add(person);
            } else if (person.getPhone().contains(key)) {
                result.add(person);
            } else if (person.getAdress().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PhoneDictionary phoneDictionary = new PhoneDictionary();
        phoneDictionary.addPerson(new Person("ivan", "Stepanov", "663548", "fedorova 76"));
        phoneDictionary.addPerson(new Person("alexandr", "smirnov", "45682", "drevesnaya 7"));
        phoneDictionary.addPerson(new Person("Igor", "Drobov", "3421678", "chuyskaya 23"));


    }
}
