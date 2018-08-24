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
    private Queue<T> queue = new LinkedList<>();

    /**
     * Метод добавления элементов в очередь
     * @param value
     * @throws InterruptedException
     */
    public synchronized void offer(T value) throws InterruptedException {
        synchronized (lock) {
            if (queue.size() == limit) {
                System.out.println("produser wait");
                this.lock.wait();
            }
            queue.offer(value);
            this.lock.notify();
        }
    }

    /**
     * Метод получения последнего элемента из очереди
     * @return
     * @throws InterruptedException
     */
    public synchronized T poll() throws InterruptedException {
        T result;
        synchronized (lock) {
            if (queue.size() == 0) {
                System.out.println("customer wait");
                this.lock.wait();
            }
            result = queue.poll();
            this.lock.notify();
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> sq = new SimpleBlockingQueue<>();
        Random random = new Random();

        /**
         * Создаем поток для заполнения очереди. Заполняться будет в цикле (10 итераций) рандомными значениями (0 - 100)
         */
        Thread produser = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        sq.offer(random.nextInt(100));
                        System.out.println("produser queue size: " + sq.queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        /**
         * Поток для "доставания" элементов из очереди. Взятие элементов выполняется в бесконечном цикле
         */
        Thread customer = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("customer value: " + sq.poll());
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
    }
}




