package ru.job4j.tracker;

public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    public String ask(String question) {
        return answers[position++];
    }

    public static void main(String[] args) {


        Tracker tracker = new Tracker();
        String[] arg = {"0", "new item", "test desk", "6546", "6"};
        Input input = new StubInput(arg);
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
        System.out.println(tracker.findAll()[0].getName());

    }
}
