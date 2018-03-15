package ru.job4j.tracker.startuisecondrealization;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
