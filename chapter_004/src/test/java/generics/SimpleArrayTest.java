package generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleArrayTest {
    @Test
    /**
     * Тест метода add
     */
    public void whenAddOneElement() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("Test");
        String[] expect = new String[5];
        expect[0] = "Test";
        assertThat(array.getArray(), is(expect));
    }

    @Test
    /**
     * Тест метода set
     */
    public void whenSetSecondElement() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.set(2, "Test");
        String[] expect = new String[5];
        expect[2] = "Test";
        assertThat(array.getArray(), is(expect));
    }

    @Test
    /**
     * Тест метода delete
     */
    public void whenDelAllElements() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.set(2, "Test");
        array.delete(2);
        String[] expect = new String[5];
        assertThat(array.getArray(), is(expect));
    }

    @Test
    /**
     * Тест метода get
     */
    public void testGet() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.set(2, "Test");
        assertThat(array.get(2), is("Test"));
    }

}
