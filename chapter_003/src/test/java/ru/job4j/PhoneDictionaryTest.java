package ru.job4j;

import org.junit.Test;
import ru.job4j.search.Person;
import ru.job4j.search.PhoneDictionary;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phoneDictionary = new PhoneDictionary();
        phoneDictionary.addPerson(new Person("Konstantin", "Matushenko", "3462577", "Kirpichnaya 44"));
        List<Person> persons = phoneDictionary.find("Konsta");
        assertThat(persons.iterator().next().getName(), is("Konstantin"));
    }
}
