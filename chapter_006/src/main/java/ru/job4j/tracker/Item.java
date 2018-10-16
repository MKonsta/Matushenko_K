package ru.job4j.tracker;

public class Item {
    private int id;
    private String name;
    private String desk;
    private String created;
    private String comments;

    public Item(String name, String desk, String comments) {
        this.name = name;
        this.desk = desk;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", desk='" + desk + '\'' + ", created='"
                + created + '\'' + ", comments='" + comments + '\'' + '}';
    }
}
