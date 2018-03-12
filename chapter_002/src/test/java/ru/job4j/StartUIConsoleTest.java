package ru.job4j;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StartUIConsoleTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenChooseShowAllItems() {
        StringBuilder menu = new StringBuilder();
        menu.append("0. Add new Item");
        menu.append(System.lineSeparator());
        menu.append("1. Show all items");
        menu.append(System.lineSeparator());
        menu.append("2. Edit item");
        menu.append(System.lineSeparator());
        menu.append("3. Delete item");
        menu.append(System.lineSeparator());
        menu.append("4. Find item by Id");
        menu.append(System.lineSeparator());
        menu.append("5. Find items by name");
        menu.append(System.lineSeparator());
        menu.append("6. Exit Program");
        menu.append(System.lineSeparator());
        menu.append(" ");
        menu.append(System.lineSeparator());

        Tracker tracker = new Tracker();
        tracker.add(new Item("name item", "desk name", 567L));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();

        StringBuilder str = new StringBuilder();
        str.append(menu);
        str.append("Список всех заявок: ");
        str.append(System.lineSeparator());
        str.append("name item");
        str.append(System.lineSeparator());
        str.append(menu);

        assertThat(out.toString(), is(str.toString()));
    }

    @Test
    public void whenChooseFindByID() {
        StringBuilder menu = new StringBuilder();
        menu.append("0. Add new Item");
        menu.append(System.lineSeparator());
        menu.append("1. Show all items");
        menu.append(System.lineSeparator());
        menu.append("2. Edit item");
        menu.append(System.lineSeparator());
        menu.append("3. Delete item");
        menu.append(System.lineSeparator());
        menu.append("4. Find item by Id");
        menu.append(System.lineSeparator());
        menu.append("5. Find items by name");
        menu.append(System.lineSeparator());
        menu.append("6. Exit Program");
        menu.append(System.lineSeparator());
        menu.append(" ");
        menu.append(System.lineSeparator());

        Tracker tracker = new Tracker();
        tracker.add(new Item("name item", "desk name", 567L));
        Input input = new StubInput(new String[]{"4", tracker.findAll()[0].getId(), "6"});
        StartUI su = new StartUI(input, tracker);
        su.init();

        StringBuilder str = new StringBuilder();
        str.append(menu);
        str.append("name item");
        str.append(System.lineSeparator());
        str.append(menu);

        assertThat(out.toString(), is(str.toString()));
    }

    @Test
    public void whenChooseFindByName() {
        StringBuilder menu = new StringBuilder();
        menu.append("0. Add new Item");
        menu.append(System.lineSeparator());
        menu.append("1. Show all items");
        menu.append(System.lineSeparator());
        menu.append("2. Edit item");
        menu.append(System.lineSeparator());
        menu.append("3. Delete item");
        menu.append(System.lineSeparator());
        menu.append("4. Find item by Id");
        menu.append(System.lineSeparator());
        menu.append("5. Find items by name");
        menu.append(System.lineSeparator());
        menu.append("6. Exit Program");
        menu.append(System.lineSeparator());
        menu.append(" ");
        menu.append(System.lineSeparator());

        Tracker tracker = new Tracker();
        tracker.add(new Item("name item", "desk name", 567L));
        Input input = new StubInput(new String[]{"5", tracker.findAll()[0].getName(), "6"});
        StartUI su = new StartUI(input, tracker);
        su.init();

        StringBuilder str = new StringBuilder();
        str.append(menu);
        str.append("name item");
        str.append(System.lineSeparator());
        str.append(menu);

        assertThat(out.toString(), is(str.toString()));
    }

}
