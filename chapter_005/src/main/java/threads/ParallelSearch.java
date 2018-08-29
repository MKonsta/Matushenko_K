package threads;

import net.jcip.annotations.ThreadSafe;

/**
 * 2. Обеспечить остановку потребителя. [#66825]
 *
 * В этом задании нужно разработать механизм остановки потребителя, когда производитель закончил свою работу.
 */

@ThreadSafe
public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();

        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}
