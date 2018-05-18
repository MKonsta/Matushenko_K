package ru.job4j.searchqueue;

import java.util.LinkedList;

/**
 * Очередь с приоритетом на LinkedList
 */

public class PrioriytQueue {

    private LinkedList<Task> tasks = new LinkedList<>();

    public void put(Task task) {
        int index = 0;
        for (Task task1 : tasks) {
            if (task.getPriority() <= task1.getPriority()) {
                index = tasks.indexOf(task1);
                break;
            } else if (task1.equals(tasks.getLast())) {
                index = tasks.indexOf(tasks.getLast()) + 1;
                break;
            }
        }
        tasks.add(index, task);

        /*if (tasks.size() != 0) { //Также рабочий цикл
            for (Task task1 : tasks) {
                if (task.getPriority() <= task1.getPriority()) {
                  tasks.add(tasks.indexOf(task1), task);
                  break;
                } else if (task1.equals(tasks.getLast())) {
                    tasks.addLast(task);
                    break;
                }
            }
        } else {
            tasks.addFirst(task);
        }*/
    }

    public Task take() {
        return this.tasks.poll();
    }

    public static void main(String[] args) {
        PrioriytQueue prioriytQueue = new PrioriytQueue();
        prioriytQueue.put(new Task("buhgal", 5));
        prioriytQueue.put(new Task("otk", 2));
        prioriytQueue.put(new Task("kassa", 8));
        prioriytQueue.put(new Task("podaji", 3));
        prioriytQueue.put(new Task("administraciya", 10));
        prioriytQueue.put(new Task("ohrana", 1));

        for (Task task : prioriytQueue.tasks) {
            System.out.println(task);
        }
    }
}
