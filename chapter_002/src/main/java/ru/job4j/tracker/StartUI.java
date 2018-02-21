package ru.job4j.tracker;

public class StartUI {
    private static final String ADD = "0";
    private static final String SHOWALLITEMS = "1";
    private static final String EDITITEM = "2";
    private static final String DELETEITEM = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";
    private static final String EXITPROGRAM = "6";
    ConsoleInput consoleInput = new ConsoleInput();
    Tracker tracker = new Tracker();

    public StartUI(ConsoleInput consoleInput, Tracker tracker) {
        this.tracker = tracker;
        this.consoleInput = consoleInput;
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
            String menuNumber = consoleInput.ask("Выберите номер опции");
            if (menuNumber.equals("0")) {
                String name = consoleInput.ask("Введите имя заявки");
                String desk = consoleInput.ask("Введите название вашего отдела");
                String time = consoleInput.ask("Введите время поступления заявки");
                Item item = new Item(name, desk, Long.parseLong(time));
                tracker.add(item);
            } else if (menuNumber.equals("1")) {
                System.out.println("Список всех заявок: ");
                for (Item item : tracker.findAll()) {
                    System.out.println(item.getName());
                }
            } else if (menuNumber.equals("2")) {
                String id = consoleInput.ask("Введите ID изменяемой заявки");
                String name = consoleInput.ask("Введите имя новой заявки");
                String desk = consoleInput.ask("Введите название Вашего отдела");
                String time = consoleInput.ask("Введите время поступления заявки");
                Item item = new Item(name, desk, Long.parseLong(time));
                tracker.replace(id, item);
            } else if (menuNumber.equals("3")) {
                String id = consoleInput.ask("Введите ID заявки, которую Вы хотите удалить");
                tracker.delete(id);
            } else if (menuNumber.equals("4")) {
                String id = consoleInput.ask("Введите ID заявки, которую Вы хотите удалить");
                System.out.println(tracker.finfById(id).getName());
            } else if (menuNumber.equals("5")) {
                String key = consoleInput.ask("Введите ключевое слово, по которому Вы хотите найти заявку");
                for (Item item : tracker.findByName(key)) {
                    System.out.println(item.getName());
                }
            } else if (menuNumber.equals("6")) {
                break;
            }
        }
    }

//    public static void main(String[] args) {
//        StartUI startUI = new StartUI();
//        startUI.init();
//    }
}
