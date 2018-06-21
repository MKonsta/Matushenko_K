package generics.base;

import generics.SimpleArray;

/**
 * Класс хранилище. Объекты хранятся массиве импортируемого класса SimpleArrey.
 * Данный абстрактный класс - родитель для классов UserStore и RoleStore
 * @param <T>
 */

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> simpleArray;

    /**
     * Переменная размера массива для хранения объектов <T>
     */
    private int size;

    /**
     * Конструктор для создания нового объекта
     * @param size размер массива для хранения объектов типа <T>
     */
    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<T>(size);
        this.size = size;
    }

    /**
     * метод для добавления новых объектов типа <T> в массив
     * непосредственно для добавления используем метод add из класса SimpleArray
     */
    public void add(T model) {
        simpleArray.add(model);
    }

    /**
     * Метод для замещения оъекта <T> по id
     */
    public boolean replase(String id, T model) {
        boolean result = false;
        for (int i = 0; i < simpleArray.getArray().length; i++) {
            if (simpleArray.getArray()[i].getId().equals(id)) {
                simpleArray.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для удаления оъекта из массива по id
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < simpleArray.getArray().length; i++) {
            if (id.equals(simpleArray.getArray()[i].getId())) {
                simpleArray.delete(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возращает объект <T> по id
     */
    public T findById(String id) {
        T result = null;
        for (int i = 0; i < simpleArray.getArray().length; i++) {
            if (id.equals(simpleArray.getArray()[i])) {
                result = simpleArray.getArray()[i];
            }
        }
        if (result == null) {
            throw new NoSuchFieldError();
        } else {
            return result;
        }
    }

}
