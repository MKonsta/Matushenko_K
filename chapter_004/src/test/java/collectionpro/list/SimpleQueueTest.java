package collectionpro.list;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {


    @Test
    public void whenPulledThreeElements() {
        SimpleQueue<Integer> sq = new SimpleQueue<>();
        sq.push(3);
        sq.push(2);
        sq.push(1);
        assertThat(sq.pull(), is(3));
        assertThat(sq.pull(), is(2));
        assertThat(sq.pull(), is(1));
    }
}
