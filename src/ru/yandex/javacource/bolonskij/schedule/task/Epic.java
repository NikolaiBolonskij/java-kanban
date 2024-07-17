package ru.yandex.javacource.bolonskij.schedule.task;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, Subtask> subtasksId = new HashMap<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public void setSubtask(Subtask subtask) {
        if (subtask.getId() != getId()) {
        removeSubtask(subtask.getId());
        subtasksId.put(subtask.getId(), subtask);
        }
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasksId.values());
    }

    public ArrayList<Integer> getSubtasksId() {
        return new ArrayList<>(subtasksId.keySet());
    }

    public void clearSubtasks() {
        this.subtasksId.clear();
    }

    public void removeSubtask(int id) {
        subtasksId.remove(id);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskId=" + getSubtasksId() +
                "} " + super.toString();
    }
}
