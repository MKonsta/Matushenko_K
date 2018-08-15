package threads;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import threads.userstorage.User;
import threads.userstorage.UserStorage;

public class UserStorageTest {

    @Test
    public void whenAddOneUser() throws InterruptedException {
        UserStorage userStorage = new UserStorage();
        Thread threadA = new Thread(() -> userStorage.add(new User(5, 100)));

        threadA.start();
        threadA.join();
        assertThat(userStorage.getUsers().get(0).getId(), is(5));
        assertThat(userStorage.getUsers().get(0).getAmount(), is(100));
    }

    @Test
    public void whenAddAndDeleteOneUser() throws InterruptedException {
        UserStorage userStorage = new UserStorage();
        Thread threadA = new Thread() {
            @Override
            public void run() {
                userStorage.add(new User(8, 35));
                userStorage.add(new User(45, 500));
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                userStorage.delete(new User(45, 0));
            }
        };

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(userStorage.getUsers());
        assertThat(userStorage.getUsers().size(), is(1)); // тут тест ИНОГДА падает, показывает что имеется 2 юзера
    }

    @Test
    public void testMetodaTrasfer() throws InterruptedException {
        UserStorage userStorage = new UserStorage();
        Thread threadA = new Thread() {
            @Override
            public void run() {
                userStorage.add(new User(5, 50));
                userStorage.add(new User(8, 85));
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                userStorage.transfer(8, 5, 5);
            }
        };

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        assertThat(userStorage.getUsers().get(0).getAmount(), is(55));
        assertThat(userStorage.getUsers().get(1).getAmount(), is(80));
    }
}
