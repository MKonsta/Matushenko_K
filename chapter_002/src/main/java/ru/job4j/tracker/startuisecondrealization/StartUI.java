package ru.job4j.tracker.startuisecondrealization;

public class StartUI {
    //private int[] range = new int[]{0, 1, 2, 3, 4, 5};
    private int[] range = range();
    private int[] range() {
        Tracker tracker = new Tracker();
        MenuTracker temp = new MenuTracker(this.input, tracker);
        int[] res = new int[temp.getActtions().length];
        for (int i = 0; i < res.length; i++) {
            res[i] = i;
        }
        return res;
    }
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
            menu.select(input.ask("select", range));
        } while (!"y".equals(this.input.ask("Exit? (y)")));
    }

    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input).init();
    }

}
