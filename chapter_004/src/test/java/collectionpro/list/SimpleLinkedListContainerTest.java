package collectionpro.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListContainerTest {

    private SimpleLinkedListContainer<Integer> slc;

    @Before
    public void beforeTest() {
        slc = new SimpleLinkedListContainer<>();
        slc.add(32);
        slc.add(48);
        slc.add(73);
    }

    @Test
    /**
     * Тест метода get
     */
    public void whenAddedThreeElements() {
        assertThat(slc.get(1), is(48));
    }

    /**
     * Если в методе get запросили не существующий индекс. Должен выбросить исключение
     */
    @Test (expected = NoSuchElementException.class)
    public void whenAskedTooBigIndex() {
        slc.get(70);
    }

    @Test
    public void testIteratora() {
        Iterator<Integer> it = slc.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(32));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(48));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(73));
        assertThat(it.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void kogdaModificirovaliSpisokVoVremyaIteracii() {
        Iterator<Integer> it = slc.iterator();
        it.next();
        slc.add(88);
        it.next();
    }

}
