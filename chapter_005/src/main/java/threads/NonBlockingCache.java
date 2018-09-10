package threads;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. Неблокирующий кеш [#4741]
 *  Необходимо сделать кеш для хранение моделей. в кеше должны быть методы
 * add(Base model), update(Base model) delete(Base model),
 * Для хранения данных в кеше нужно использовать ConcurrentHashMap<Integer, Base>.
 * В качестве ключа используйте int id. в качестве значения Base модель
 * В кеше должна быть возможность проверять актуальность данных. Для этого в модели данных должно быть после int version.
 * Это после должно увеличиваться на единицу каждый раз, когда произвели изменения данных в модели.
 * Например. Два пользователя прочитали данные task_1
 * первый пользователь изменил поле имя и второй сделал тоже самое. нужно перед обновлением данных проверить,
 * что текущий пользователь не затер данные другого пользователя. если данные затерты
 * то выбросить OptimisticException - такая реализация достигается за счет введение в модель поля version.
 * Перед обновлением данных необходимо проверять текущую версию и ту что обновляем и увеличивать на единицу каждый раз,
 * когда произошло обновление. Если версии не равны -  кидать исключение.
 */

public class NonBlockingCache {
    private final ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    public ConcurrentHashMap<Integer, Base> getMap() {
        return this.map;
    }

    /**
     * Метод добавления элементов в коллекцию
     * @param model
     */
    public void add(Base model) {
        map.put(model.getId(), model);
    }

    /**
     * Метод удаления элементов
     * @param model
     * @return
     */
    public boolean delete(Base model) {
        return map.remove(model.getId()) != null;
    }

    /**
     * Метод для изменения поля name в элементах коллекции. Поле version - это счетчик количества изменений. Если поле
     * name одновременно меняется разными потоками, выбрасываеся исключение OptimisticException
     * @param model
     * @throws OptimisticException
     */
    public void update(Base model) throws OptimisticException {
        int currentVersion = model.getVersion();
        if (map.containsKey(model.getId())) {
            map.computeIfPresent(model.getId(), (k, v) -> {
                if (currentVersion == model.getVersion()) {
                    return new Base(model.getId(), map.get(model.getId()).getVersion() + 1, model.getName());
                } else {
                    throw new OptimisticException("the note is changed");
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NonBlockingCache nb = new NonBlockingCache();
        Thread thread = new Thread() {
            @Override
            public void run() {
                nb.add(new Base(5, "gerb"));
                nb.add(new Base(8, "eight"));
                nb.update(new Base(5, "five"));
            }
        };

        thread.start();
        thread.join();

        System.out.println(nb.getMap());
    }
}

class Base {
    private int id;
    private int version = 0;
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Base{" + "id=" + id + ", version=" + version + ", name='" + name + '\'' + '}';
    }
}

class OptimisticException extends RuntimeException {
    public OptimisticException(String mesage) {
        super(mesage);
    }
}
