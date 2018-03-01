package ru.job4j;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import ru.job4j.tracker.*;

public class StartUITest {
    @Test
    public void whenAddNewItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "новая тестовая заявка", "отдел", "время создания", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("новая тестовая заявка"));
    }

    @Test
    public void whenEditOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("First item name", "first desk", "first time");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", item.getId(), "edited item", "edited desk", "edited time", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("edited item"));
    }

    @Test
    public void whenDeleteOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("name item", "desk name", "timeCreated");
        tracker.add(item);
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
    }

}
