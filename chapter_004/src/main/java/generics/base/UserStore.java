package generics.base;


import generics.SimpleArray;

/**
 * Класс хранилище юзеров. Объекты хранятся массиве импортируемого класса SimpleArrey.
 */
public class UserStore implements Store<User> {

    private SimpleArray<User> simpleArray;

    /**
     * Переменная размера массива для хранения юзеров
     */
    private int size;

    /**
     * Конструктор для создания нового объекта
     * @param size размер массива для хранения юзеров
     */
    public UserStore(int size) {
        this.simpleArray = new SimpleArray<User>(size);
        this.size = size;
    }

    @Override
    /**
     * метод для добавления новых объектов типа Юзер в массив
     * непосредственно для добавления используем метод add из класса SimpleArray
     */
    public void add(User model) {
        simpleArray.add(model);
    }

    @Override
    /**
     * Метод для замещения оъекта Юзер по id
     */
    public boolean replase(String id, User model) {
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

    @Override
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

    @Override
    /**
     * Метод возращает объект User по id
     */
    public User findById(String id) {
        User result = null;
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

    public static void main(String[] args) {
        UserStore us = new UserStore(5);
        us.add(new User("tytryry"));
    }
}
