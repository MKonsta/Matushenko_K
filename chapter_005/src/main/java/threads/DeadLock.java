package threads;

import java.util.Random;

/**
 * Гарантированный дедлок. [#57309]
 *
 * Программа эмулирует работу с банковскими счетами. Есть 2 счета. На каждом имеется по 10000. Создаем 2 потока, и в
 * каждом потоке переводим с одного аккаунта на другой рандомную сумму. Проводим эту операцию по 10000 раз. В конце выводим
 * сумму балансов 1-го и 2-го аккаунта. Должно всегда получаться 20000.
 *
 * Пример DeadLock-а приведен в классе Runner с пояснениями
 */
public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.finished();
    }
}

/**
 * Тут показан пример дедЛока. Для того чтобы безопасно перевести средства с одного счета на другой, нужно синхронизироваться
 * на 2-х объектах. Будем синхронизироваться на account1 и account2.
 */
class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    public void firstThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            synchronized (account2) {
                //Первый тред находится сдесь. Он начал сврю работу и уже заблокировал account 2. Но продолжать он не может,
                //так как Второй тред тоже в работе, и в настоящий момоент он уже заблокировал account1
                // Программа вечно будет висеть
                synchronized (account1) {
                    Account.transfer(account1, account2, random.nextInt(100));
                }
            }
        }
    }

    public void secondThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            synchronized (account1) {
                //второй тред находится сдесь. Он также начал сврю работу и уже заблокировал account 1. Но продолжать он
                // тоже не может, так как Второй тред тоже в работе, и в настоящий момоент он уже заблокировал account2.
                // Для того, чтобы разрешить данный дедЛок, нужно в обоих тредах синхонизироваться последовательно одинаково
                // сначала на account1, затем на account2
                synchronized (account2) {
                    Account.transfer(account2, account1, random.nextInt(100));
                }
            }
        }
    }

    public void finished() {
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        System.out.println("Total balance " + (account1.getBalance() + account2.getBalance()));
    }
}

/**
 * Класс эмулирует работу с банковскими счетами
 */
class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withDraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withDraw(amount);
        acc2.deposit(amount);
    }
}
