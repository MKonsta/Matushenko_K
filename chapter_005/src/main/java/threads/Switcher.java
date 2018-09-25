package threads;

import net.jcip.annotations.ThreadSafe;

/**
 * Свитчер [#50581]
 * 1) Реализуйте объект, который хранит в себе строку или ее представление. Объекту необходимо:
 * - содержать метод, который принимает на вход значение типа int, конвертирует его в строковое представление
 * (например, 4 -> "4"), а затем добавляет к концу строки.
 * - по требованию возвращать эту строку.
 * 2) Реализуйте 2 потока, которые в цикле добавляют всегда одно и то же число (1-й поток число 1, второй поток число 2)
 * в строку из пункта 1.
 * Работа потоков должна быть организована таким образом, чтобы числа добавлялись в строку в следующем порядке: сначала
 * 10 чисел из первого потока, затем 10 чисел из второго, затем снова 10 чисел из первого и так далее.
 */

@ThreadSafe
public class Switcher {
    private String str = "";

    public synchronized void covert(int i) {
        str = str + i;
    }

    public String getStr() {
        return this.str;
    }

    public static void main(String[] args) throws InterruptedException {
        Switcher sw = new Switcher();
        Object lock = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            sw.covert(1);
                        }
                        System.out.println(Thread.currentThread().getName() + sw.getStr());
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            sw.covert(2);
                        }
                        System.out.println(Thread.currentThread().getName() + sw.getStr());
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
