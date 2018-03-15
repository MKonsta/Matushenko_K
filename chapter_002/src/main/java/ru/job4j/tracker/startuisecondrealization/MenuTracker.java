package ru.job4j.tracker.startuisecondrealization;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.Item;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] acttions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.acttions[0] = this.new AddItem();
        this.acttions[1] = new MenuTracker.ShowItems();
        this.acttions[2] = new MenuTracker.EditItem();
        this.acttions[3] = new MenuTracker.DeleteItem();
        this.acttions[4] = new MenuTracker.FindItemById();
        this.acttions[5] = new MenuTracker.FindItemName();
    }

    public void select(int key) {
        this.acttions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.acttions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter the tasks name: ");
            String desk = input.ask("Please enter the tasks desk: ");
            String time = input.ask("Please enter the time created: ");
            Item item = new Item(name, desk, Long.parseLong(time));
            tracker.add(item);
        }
        public String info() {
            return String.valueOf(this.key()) + ". " + "Add the new Item. ";
        }
    }

    private static class ShowItems implements UserAction {
        public int key() {
            return 1;
        }
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.println(String.format("%s. %s", item.getId(), item.getName()));
            }
        }
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    class EditItem implements UserAction {
        public int key() {
            return 2;
        }
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id задачи, которую нужно изменить");
            String name = input.ask("Please enter the tasks name: ");
            String desk = input.ask("Please enter the tasks desk: ");
            String time = input.ask("Please enter the time created: ");
            Item item = new Item(name, desk, Long.parseLong(time));
            tracker.replace(id, item);
        }
        public String info() {
            return String.format("%s. %s", this.key(), "Edit the item");
        }
    }

    class DeleteItem implements UserAction {
        public int key() {
            return 3;
        }
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id задачи, которую нужно удалить");
            tracker.delete(id);
        }
        public String info() {
            return String.format("%s. %s", this.key(), "Delete Item");
        }
    }

    class FindItemById implements UserAction {
        public int key() {
            return 4;
        }
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id искомой задачи");
            System.out.println(String.format("%s, %s, %s", tracker.finfById(id).getId(), tracker.finfById(id).getName(), String.valueOf(tracker.finfById(id).getCreated())));
        }
        public String info() {
            return this.key() + ". Find item by ID";
        }
    }

    class FindItemName implements UserAction {
        public int key() {
            return 5;
        }
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите name искомой задачи");
            for (Item rr : tracker.findByName(name)) {
                System.out.println(rr.getId() + "  " + rr.getName());
            }
        }
        public String info() {
            return this.key() + ". Find item by name";
        }
    }

}
