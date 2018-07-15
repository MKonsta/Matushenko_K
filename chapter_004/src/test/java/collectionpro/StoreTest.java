package collectionpro;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreTest {
    @Test
    public void whenAddThreeUsers() {
        Store store = new Store();
        assertThat(store.add(1, "Petr"), is(true));
        assertThat(store.add(2, "Viktor"), is(true));
        assertThat(store.add(2, "Ivan"), is(false));
    }

    @Test
    public void tryUpdateTwoUsers() {
        Store store = new Store();
        store.add(1, "Petr");
        store.add(2, "Viktor");
        assertThat(store.update(1, "Ivan"), is(true));
        assertThat(store.update(5, "Fedoor"), is(false));
    }

    @Test
    public void oneDeleteTwoAddOneUpdated() {
        Store store = new Store();
        store.add(1, "Petr");
        store.add(2, "Viktor");
        store.add(3, "Semen");
        store.add(4, "Nikola");
        store.add(5, "Sergey");
        List<Store.User> changed = new ArrayList<>();
        changed.add(new Store.User(1, "Petr"));
        changed.add(new Store.User(3, "Semen"));
        changed.add(new Store.User(4, "Konstantin"));
        changed.add(new Store.User(5, "Sergey"));
        changed.add(new Store.User(6, "Dmitriy"));
        changed.add(new Store.User(8, "Efim"));

        assertThat(store.diff(store.getUsers(), changed), is(new Store.Info(2, 1, 1)));
    }

}
