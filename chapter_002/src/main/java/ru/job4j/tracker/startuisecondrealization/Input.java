package ru.job4j.tracker.startuisecondrealization;

public interface Input {
    String ask(String question);
    int ask(String question, int[] range);
}
