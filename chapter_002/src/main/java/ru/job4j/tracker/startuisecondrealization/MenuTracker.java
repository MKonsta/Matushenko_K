package ru.job4j.tracker.startuisecondrealization;

public class MenuTracker {
    private Input input;
    private Tracker tracker;

    private UserAction[] acttions = new UserAction[6];

    public UserAction[] getActtions() {
        return acttions;
    }

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.acttions[0] = this.new AddItem(0, "Add new item");
        this.acttions[1] = this.new ShowItems(1, "Show all items");
        this.acttions[2] = this.new EditItem(2, "Edit item");
        this.acttions[3] = this.new DeleteItem(3, "Delete item");
        this.acttions[4] = this.new FindItemById(4, "Find item by ID");
        this.acttions[5] = this.new FindByName(5, "Find items by name");

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

    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter name:");
            String desk = input.ask("Enter desk");
            String time = input.ask("Enter time created");
            tracker.add(new Item(name, desk, Long.parseLong(time)));
        }

    }

    private class ShowItems extends BaseAction {
        ShowItems(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getItems()) {
                System.out.println(String.format("%s. %s", item.getId(), item.getName()));
            }
            /*tracker.findAll();
            for (Item item : tracker.findAll()) {
                System.out.println(String.format("%s. %s", item.getId(), item.getName()));
            }*/
        }
    }

    class EditItem extends BaseAction {

        protected EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter items ID, which you want change:");
            String name = input.ask("Enter new name");
            String desk = input.ask("Enter new desk");
            String time = input.ask("Enter new time");
            Item item = new Item(name, desk, Long.parseLong(time));
            tracker.replace(id, item);
        }
    }

    private class DeleteItem extends BaseAction {
        protected DeleteItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter items ID which you want delete:");
            tracker.delete(id);
        }
    }

    private class FindItemById extends BaseAction {

        protected FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter ID:");
            System.out.println(String.format("%s, %s", tracker.finfById(id).getId(), tracker.finfById(id).getName()));
        }
    }

    private class FindByName extends BaseAction {

        protected FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String keyWord = input.ask("Enter key word:");
            for (Item item : tracker.findByName(keyWord)) {
                System.out.println(String.format("%s, %s", item.getId(), item.getName()));
            }
        }
    }

}