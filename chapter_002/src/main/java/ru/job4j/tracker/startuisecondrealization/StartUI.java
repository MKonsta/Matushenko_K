package ru.job4j.tracker.startuisecondrealization;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.ConsoleInput;

    //!!!ЭТО ВТОРАЯ (АЛЬЕРНАТИВНАЯ) РЕАЛИЗАЦИЯ РАБОТЫ КЛАССА StartUI. ТУТ ИСПОЛЬЗУЮТСЯ ВНУТРЕННИЕ КЛАССЫ!!!

public class StartUI {
    private Input input;
    public StartUI(Input input) {
        this.input = input;
    }

    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Select:"));
            menu.select(key);
        } while (!"y".equals(this.input.ask("Exit? (y)")));
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartUI(input).init();
    }

}
