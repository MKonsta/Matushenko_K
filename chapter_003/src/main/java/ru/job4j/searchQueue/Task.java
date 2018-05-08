package ru.job4j.searchQueue;

import java.util.Objects;

public class Task {
    private String desk;
    private int priority;

    public Task(String desk, int priority) {
        this.desk = desk;
        this.priority = priority;
    }

    public String getDesk() {
        return desk;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return priority == task1.priority &&
                Objects.equals(desk, task1.desk);
    }

    @Override
    public int hashCode() {

        return Objects.hash(desk, priority);
    }

    @Override
    public String toString() {
        return "Task{" +
                "desk='" + desk + '\'' +
                ", priority=" + priority +
                '}';
    }
}
