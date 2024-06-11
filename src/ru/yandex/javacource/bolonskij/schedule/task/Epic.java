package ru.yandex.javacource.bolonskij.schedule.task;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public void setSubtask(int id) {
        removeSubtask(id);
        subtasks.add(id);
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasks;
    }

    public void clearSubtasks() {
        this.subtasks.clear();
    }

    public void removeSubtask(int id) {
        for (int i = 0; i < subtasks.size(); i++) {
            if (subtasks.get(i) == id) subtasks.remove(i);
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskId=" + subtasks +
                "} " + super.toString();
    }
}
