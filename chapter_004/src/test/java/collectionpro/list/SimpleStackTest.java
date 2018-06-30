package collectionpro.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {


    @Test
    public void whenPulledThreeElements() {
        SimpleStack<Integer> sq = new SimpleStack<>();
        sq.push(1);
        sq.push(2);
        sq.push(3);
        assertThat(sq.pull(), is(3));
        assertThat(sq.pull(), is(2));
        assertThat(sq.pull(), is(1));
    }
}
