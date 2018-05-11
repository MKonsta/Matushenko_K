package ru.job4j.sortirovka;

import org.junit.Test;
import ru.job4j.sortirovka.sortirovka_comparator.SortUser;
import ru.job4j.sortirovka.sortirovka_comparator.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserComparatorTest {
    @Test
    public void testMetodaSortNameLength() {
        List<User> expect = new ArrayList<>();
        expect.addAll(
                Arrays.asList(
                        new User("Oleg", 36),
                        new User("Sergey", 45),
                        new User("Konstantin", 33)
                )
        );

        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("Sergey", 45),
                        new User("Konstantin", 33),
                        new User("Oleg", 36)
                )
        );

        SortUser sortUser = new SortUser();
        assertThat(sortUser.sortNameLength(users), is(expect));
    }

    @Test
    public void testMetodaSortAllFields() {
        List<User> expect = new ArrayList<>();
        expect.addAll(
                Arrays.asList(
                        new User("Ivan", 25),
                        new User("Ivan", 30),
                        new User("Sergey", 20),
                        new User("Sergey", 25)
                )
        );

        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("Sergey", 25),
                        new User("Ivan", 30),
                        new User("Sergey", 20),
                        new User("Ivan", 25)
                )
        );

        SortUser sortUser = new SortUser();
        assertThat(sortUser.sortNameLength(users), is(expect));
    }
}
