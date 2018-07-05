package collectionpro.map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * 1. Создать модель User [#999]
 * Класс демонстирует смысл переопределения методов equals и hashCode.
 */
public class UserHashCodeAndEqualsTest {

    /**
     * В этом классе методы equals и hashCode не переопределены
     */
    static final class User {
        String name;
        int children;
        Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }
    }

    /**
     * Создаем 2 одинаковых объекта класса User. И сравниваем их хэши и по equals. В результате видим, что ни то ни другое
     * не совпадает.
     */
    @Test
    public void whenNonOverrideHashAndEqals() {
        User user = new User("Ivan", 2, new GregorianCalendar(1980, 4, 24));
        User user1 = new User("Ivan", 2, new GregorianCalendar(1980, 4, 24));
        assertThat(user.equals(user1), is(false));
        assertThat(user.hashCode(), is(not(user1.hashCode())));
    }

    /**
     * Теперь пишем такойже класс, но переопределяем equals и hashCode. Объекты будем сравнивать по полям "имя" и "возраст".
     * Количество детей не важно. Таким образом все Юзеры будут уникальны с высокой долей вероятности.
     */
    static final class UserWithHashAndEquals {
        String name;
        int children;
        Calendar birthday;


        public UserWithHashAndEquals(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UserWithHashAndEquals that = (UserWithHashAndEquals) o;
            return Objects.equals(name, that.name)
                    && Objects.equals(birthday, that.birthday);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, birthday);
        }
    }

    /**
     * Создаем 2 одинаковых объекта класса UserWithHashAndEquals с переопределенными методами. И из тестов видим что они
     * равны и по иквэлс и по хэшам.
     */
    @Test
    public void whenOverrideHashAndEqals() {
        UserWithHashAndEquals user = new UserWithHashAndEquals("Ivan", 2, new GregorianCalendar(1980, 4, 24));
        UserWithHashAndEquals user1 = new UserWithHashAndEquals("Ivan", 2, new GregorianCalendar(1980, 4, 24));
        assertThat(user.equals(user1), is(true));
        assertThat(user.hashCode(), is(user1.hashCode()));
    }
}
