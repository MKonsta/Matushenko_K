package collectionpro.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetOnLinkedListTest {

    @Test
    public void whenAddedTwoDublicates() {
        SimpleSetOnLinkedList<Integer> ss = new SimpleSetOnLinkedList<>();
        ss.add(1);
        ss.add(6);
        ss.add(7);
        ss.add(8);
        ss.add(6);
        ss.add(7);
        Iterator it = ss.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
    }
}
