package threads;

/**
 * 1. Проиллюстрировать проблемы с многопоточностью. [#1096]
 *
 * В данном примере мы создаем класс ресурс в котором имется переменная i. Также в классе есть метод changeI, который
 * просто добавляет к i еденицу.  В методе майн мы создаем 1 объект ресурс (и в конструкторе инициализируем i = 5),
 * и 3 объекта MyThread, в конструкторы которых передаем наш единственный объект resourse. Затем стартуем все три потока.
 * По логике вещей в итоговом результате на консоль должно было быть выведено 8.  Однако результат будет всегда разный -
 * 5, 6, 7, 8.   Это связано с тем, что переменная i может быть сохранена в кэше, а не в омновной памяти. А другой поток
 * может взять и обратиться в основную память, в которой результат еще не обновлен.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource(5);
        MyThread myThread = new MyThread(resource);
        MyThread myThread2 = new MyThread(resource);
        MyThread myThread3 = new MyThread(resource);
        myThread.start();
        myThread2.start();
        myThread3.start();
        System.out.println(resource.getI());
    }
}

class MyThread extends Thread {
    private Resource resource;

    public MyThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.changeI();
    }
}

class Resource {
    private volatile int i;

    public Resource(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void changeI() {
        int i = this.i;
        i++;
        this.i = i;
    }
}


