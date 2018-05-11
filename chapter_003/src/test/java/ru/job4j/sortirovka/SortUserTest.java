package ru.job4j.sortirovka;

import org.junit.Test;
import ru.job4j.sortirovka.sortirovkapovozrastu.SortUser;
import ru.job4j.sortirovka.sortirovkapovozrastu.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void testMetodaSort() {
        List<User> expect = new ArrayList<>();
        expect.addAll(
                Arrays.asList(
                        new User("ivan", 18),
                        new User("igor", 24),
                        new User("misha", 43)
                )
        );
        List<User> list = new ArrayList<>();
        list.addAll(
                Arrays.asList(
                        new User("igor", 24),
                        new User("misha", 43),
                        new User("ivan", 18)
                )
        );
        SortUser sortUser = new SortUser();
        //assertThat(expect, is(sortUser.sort(list)));
        //assertThat(sortUser.sort(list).containsAll(expect));
        for (User user : sortUser.sort(list)) {
            user.equals(expect.get(0));
            expect.remove(0);
        }
    }
}
