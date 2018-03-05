package ru.job4j;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceOldItemAndChangeItToNew() {
        Tracker tracker = new Tracker();
        Item item = new Item("Заявка1", "Стол1", 234L);
        tracker.add(item);
        Item nextItem = new Item("Заявка №2", "Стол №2", 234L);
        tracker.replace(item.getId(), nextItem);
        assertThat(tracker.finfById(item.getId()).getName(), is("Заявка №2"));
    }

    @Test
    public void findAllWhenAddOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Заявка1", "Стол1", 234L);
        tracker.add(item);
        assertThat(tracker.findAll().length, is(1));
    }

    @Test
    public void findAllWhenAddTwoItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("Заявка1", "Стол1", 234L);
        Item item1 = new Item("Заявка2", "Стол2", 284L);
        tracker.add(item);
        tracker.add(item1);
        assertThat(tracker.findAll().length, is(2));
    }
    @Test
    public void finByNameWhenAddTwoItemsWithDifferentNames() {
        Tracker tracker = new Tracker();
        Item item = new Item("Заявка1", "Стол1", 234L);
        Item item1 = new Item("Заявка2", "Стол2", 284L);
        tracker.add(item);
        tracker.add(item1);
        assertThat(tracker.findByName("Заявка1").length, is(1));
    }
    @Test
    public void finByNameWhenAddTwoItemsWithEqualNames() {
        Tracker tracker = new Tracker();
        Item item = new Item("Заявка1", "Стол1", 234L);
        Item item1 = new Item("Заявка1", "Стол2", 284L);
        tracker.add(item);
        tracker.add(item1);
        assertThat(tracker.findByName("Заявка1").length, is(2));
    }
    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("Заявка1", "Стол1", 234L);
        Item item1 = new Item("Заявка2", "Стол2", 284L);
        tracker.add(item);
        tracker.add(item1);
        assertThat(tracker.finfById(item1.getId()).getName(), is("Заявка2"));
    }
    @Test
    public void whenAddTwoItemsAndDeleteFirst() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("item1", "desk1", 234L));
        tracker.add(new Item("item2", "desk2", 879L));
        tracker.delete(tracker.findAll()[0].getId());
        assertThat(tracker.findAll().length, is(1));
    }
}
