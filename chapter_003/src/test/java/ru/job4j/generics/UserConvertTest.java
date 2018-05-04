package ru.job4j.generics;

import org.junit.Test;
import ru.job4j.generics.hashmap.User;
import ru.job4j.generics.hashmap.UserConvert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenConvertOneList() {
        UserConvert userConvert = new UserConvert();
        List<User> users = Arrays.asList(
                new User(342, "Petr", "Omsk"),
                new User(547, "Konstantin", "Bishkek"),
                new User(5647, "Artem", "Moskow")
        );
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(342, new User(342, "Petr", "Omsk"));
        expect.put(547, new User(547, "Konstantin", "Bishkek"));
        expect.put(5647, new User(5647, "Artem", "Moskow"));
        assertThat(userConvert.process(users), is(expect));
    }
}
