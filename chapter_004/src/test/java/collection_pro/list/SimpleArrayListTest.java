package collection_pro.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(57);
        list.add(9);
        list.add(60);
    }

    @Test
    public void testMetodaGet() {
        assertThat(list.get(0), is(57));
        assertThat(list.get(2), is(60));
    }

    @Test
    public void testMetodaIterator() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(57));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(60));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModificatedContainerAtIterationTime() {
        Iterator<Integer> it = list.iterator();
        it.next();
        list.add(88);
        it.next();
    }

}
