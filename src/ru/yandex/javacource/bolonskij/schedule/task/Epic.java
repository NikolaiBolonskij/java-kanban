package ru.yandex.javacource.bolonskij.schedule.task;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public void setSubtask(int id) {
        removeSubtask(id);
        subtasksId.add(id);
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void clearSubtasks() {
        this.subtasksId.clear();
    }

    public void removeSubtask(int id) {
        for (int i = 0; i < subtasksId.size(); i++) {
            if (subtasksId.get(i) == id) subtasksId.remove(i);
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskId=" + subtasksId +
                "} " + super.toString();
    }
}
