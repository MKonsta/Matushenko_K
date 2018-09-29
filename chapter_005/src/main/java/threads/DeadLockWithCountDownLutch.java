package threads;

import java.util.concurrent.CountDownLatch;

/**
 * Пример дэдЛока с ипользованием CountDownLatch.
 */

public class DeadLockWithCountDownLutch {

    public static void main(String[] args) throws InterruptedException {
        Object object1 = new Object();
        Object object2 = new Object();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(new DeadLocker(object1, object2, countDownLatch));
        Thread t2 = new Thread(new DeadLocker(object1, object2, countDownLatch));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}

class DeadLocker implements Runnable{
    private CountDownLatch countDownLatch;
    private final Object lock1;
    private final Object lock2;

    public DeadLocker(Object obj1, Object obj2, CountDownLatch countDownLatch) {
        this.lock1 = obj1;
        this.lock2 = obj2;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            countDownLatch.countDown();
            try {
                // Один из двух тредов находится тут. Он заблокировал lock1, и ждет итерации CountDownLatch.
                //Итерация произойти не может, так как второй поток не может зайти в свой блок synchronized по причине
                // блокироки lock1
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + " is finishing");
        }
    }
}