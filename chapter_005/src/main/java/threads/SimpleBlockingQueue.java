package threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 1. Реализовать шаблон Producer Consumer. [#1098]
 * Реализуйте шаблон Producer Consumer.
 * Для этого вам необходимо реализовать собственную версию bounded blocking queue. Это блокирующая очередь,
 * ограниченная по размеру. В данном шаблоне Producer помещает данные в очередь, а Consumer извлекает данные из очереди.
 * Если очередь заполнена полностью, то при попытке добавления поток Producer блокируется, до тех пор
 * пока Consumer не извлечет очередные данные, т.е. в очереди появится свободное место. И наоборот если очередь пуста
 * поток Consumer блокируется, до тех пор пока Producer не поместит в очередь данные.
 * В задании нельзя использовать потокобезопасные коллекции реализованные в JDK. Ваша задача используя, wait/notify
 * реализовать блокирующую очередь.
 * @param <T>
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final Object lock = new Object();
    /**
     * Задаем фиксированный размер нашей очереди
     */
    private final int limit = 3;
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Метод добавления элементов в очередь
     * @param value
     * @throws InterruptedException
     */
    public void offer(T value) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == limit) {
                lock.wait();
            }
            queue.offer(value);
            lock.notify();
        }
    }

    /**
     * Метод получения последнего элемента из очереди
     * @return
     * @throws InterruptedException
     */
    public T poll() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }
            T result = queue.poll();
            lock.notify();
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> sq = new SimpleBlockingQueue<>();

        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        sq.offer(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread consumer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    try {
                        sq.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        System.out.println("finish");
    }
}




