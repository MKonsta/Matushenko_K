package threads;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NonBlockingCacheTest {
    NonBlockingCache nbc = new NonBlockingCache();


    @Test
    public void cathcException() throws InterruptedException {

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                nbc.add(new Base(1, "zero"));
                nbc.update(new Base(1, "one"));
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                nbc.update(new Base(1, "five"));
            }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(nbc.map);
        assertThat(nbc.map.get(1).getVersion(), is(2));
    }
}
