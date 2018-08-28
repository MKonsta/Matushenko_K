package threads;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>();

    /**
     * В тесте поток продюсер заполняет очередь 4-мя элементами. А очередь ограничена 3-мя.
     * Поток кастомер вытаскивает по 1-му элементу 3 раза.
     * По логике, после остановки потоков, в очереди должен остаться 1 элемент = 3.
     * Однако, фактически поток кастомер, или продюсер замирают в бесконечном ожидании
     * @throws InterruptedException
     */
    @Test
    public void whenAdded4ElementsAndStayOnlyOne() throws InterruptedException {

        Thread produser = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    try {
                        sbq.offer(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread customer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        sbq.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        produser.start();
        customer.start();

        produser.join();
        customer.join();

        assertThat(sbq.poll(), is(3));
    }
}
