package collectionpro.set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.Iterator;

public class SimpleSetTest {

    @Test
    public void whenAddedTwoDublicates() {
        SimpleSet<Integer> ss = new SimpleSet<>();
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
