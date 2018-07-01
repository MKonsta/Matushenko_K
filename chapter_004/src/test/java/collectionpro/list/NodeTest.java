package collectionpro.list;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NodeTest {

    Node<Integer> first = new Node<>(1);
    Node<Integer> second = new Node<>(2);
    Node<Integer> third = new Node<>(3);
    Node<Integer> fourth = new Node<>(4);
    Node<Integer> fiveth = new Node<>(5);

    @Test
    public void whenNotLoop() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fiveth;
        assertThat(first.hasCycle(first), is(false));
    }

    @Test
    public void whenLoopOnFirst() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fiveth;
        fiveth.next = first;
        assertThat(first.hasCycle(first), is(true));
    }

    @Test
    public void whenLoopOnSecond() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fiveth;
        fiveth.next = second;
        assertThat(first.hasCycle(first), is(true));
    }

    @Test
    public void whenLoopOnThird() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fiveth;
        fiveth.next = third;
        assertThat(first.hasCycle(first), is(true));
    }
}
