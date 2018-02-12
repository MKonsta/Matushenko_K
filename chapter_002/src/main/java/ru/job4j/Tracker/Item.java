package ru.job4j.Tracker;

public class Item {
    private String id;
    private String name;
    private String desk;
    private long created;
    private String comments;

    public Item (String name, String desk, long created) {
        this.name = name;
        this.desk = desk;
        this.created = created;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
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
    public long getCreated() {
        return created;
    }
    public void setCreated(long created) {
        this.created = created;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
}
