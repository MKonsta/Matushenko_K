package ru.job4j.tracker.startuisecondrealization;

public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
