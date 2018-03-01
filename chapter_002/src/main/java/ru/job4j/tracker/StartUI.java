package ru.job4j.tracker;

public class StartUI {
    private static final String ADD = "0";
    private static final String SHOWALLITEMS = "1";
    private static final String EDITITEM = "2";
    private static final String DELETEITEM = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";
    private static final String EXITPROGRAM = "6";
    Input input = new ConsoleInput();
    Tracker tracker = new Tracker();

    public StartUI(Input input, Tracker tracker) {
        this.tracker = tracker;
        this.input = input;
    }
    public void outputStartMmenu() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println(" ");
    }
    public void init() {
        for (;;) {
            outputStartMmenu();
            String menuNumber = input.ask("Выберите номер опции");
            if (menuNumber.equals("0")) {
                String name = input.ask("Введите имя заявки");
                String desk = input.ask("Введите название вашего отдела");
                String time = input.ask("Введите время поступления заявки");
                Item item = new Item(name, desk, time);
                tracker.add(item);
            } else if (menuNumber.equals("1")) {
                System.out.println("Список всех заявок: ");
                for (Item item : tracker.findAll()) {
                    System.out.println(item.getName());
                }
            } else if (menuNumber.equals("2")) {
                String id = input.ask("Введите ID изменяемой заявки");
                String name = input.ask("Введите имя новой заявки");
                String desk = input.ask("Введите название Вашего отдела");
                String time = input.ask("Введите время поступления заявки");
                Item item = new Item(name, desk, time);
                tracker.replace(id, item);
            } else if (menuNumber.equals("3")) {
                String id = input.ask("Введите ID заявки, которую Вы хотите удалить");
                tracker.delete(id);
            } else if (menuNumber.equals("4")) {
                String id = input.ask("Введите ID заявки, которую Вы хотите найти");
                System.out.println(tracker.finfById(id).getName());
            } else if (menuNumber.equals("5")) {
                String key = input.ask("Введите ключевое слово, по которому Вы хотите найти заявку");
                for (Item item : tracker.findByName(key)) {
                    System.out.println(item.getName());
                }
            } else if (menuNumber.equals("6")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Input cons = new ConsoleInput();
        Tracker tracker = new Tracker();
        StartUI ss = new StartUI(cons, tracker);
        ss.init();
    }
}
