package ru.job4j.tracker.startuisecondrealization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Tracker implements ITracker {
    //private List<Item> items = new ArrayList<>();
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RANDOM = new Random();

    public Item[] getItems() {
        return items;
    }

    private String generateId() {
        Date date = new Date();
        return String.valueOf(date.getTime())/* + String.valueOf(RANDOM.nextInt())*/;
    }

    public Item add(Item item) {
        item.setId(this.generateId());
        //this.items[this.position++] = item;
        return item;
    }

    public void replace(String id, Item item) {
        for (Item r : items) {
            if (r != null && r.getId().equals(id)) {
                r.setName(item.getName());
                r.setDesk(item.getDesk());
                r.setCreated(item.getCreated());
                r.setComments(item.getComments());
                break;
            }
        }
    }
    public void delete(String id) {
//        for (Item item : items) {
//            if (id.equals(item.getId())) {
//                items.remove(item);
//            }
//        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && id.equals(items[i].getId())) {
                items[i] = null;
                System.arraycopy(items, i + 1, items, i, items.length - i - 1);
                items[items.length - 1] = null;
                break;
            }
        }
    }

    public Item[] findAll() { //после смены массивов на arrayList метод стал ненужным
        int index = 0;
        while (items[index] != null) {
            index++;
        }
        Item[] result = new Item[index];
        for (int i = 0; i < result.length; i++) {
            result[i] = items[i];
        }
        return result;
    }

    /**
     * Метод ищет Items по имени и возвращает их в виде ArrayList
     * @param key
     * @return
     */
//    public List<Item> findByName(String key) {
//        List<Item> result = new ArrayList<>();
//        for (Item item : items) {
//            if (item.getName().equals(key)) {
//                result.add(item);
//            }
//        }
//        return result;
//    }
    public Item[] findByName(String key) {
        int index = 0;
        Item[] result = null;
        for (int i = 0; i < findAll().length; i++) {
            if (findAll()[i].getName().equals(key)) {
                index++;
            }
        }
        if (index > 0) {
            result = new Item[index];
            for (int i = 0; i < index; i++) {
                if (findAll()[i].getName().equals(key)) {
                    result[i] = findAll()[i];
                }
            }
        }
        return result;
    }

    /**
     * Метод ищет Item по ID
     * @param id
     * @return
     */
    public Item findById(String id) {
        Item result = null;
//        for (Item item : items) {
//            if (item.getId().equals(id)) {
//                result = item;
//                break;
//            }
//        }
        for (int index = 0; index < findAll().length; index++) {
            if (findAll()[index].getId().equals(id)) {
                result = findAll()[index];
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        /*Tracker tracker = new Tracker();
        Item item = new Item("thrt", "gerg", 4352L);
        Item item1 = new Item("rthe", "tyjr", 7L);
        //tracker.add(item);
        tracker.add(item1);
        System.out.println(tracker.findAll().length);
        tracker.delete(tracker.findAll()[0].getId());
        System.out.println(tracker.findAll().length);
        System.out.println(tracker.findAll()[1].getId());*/
    }
}
