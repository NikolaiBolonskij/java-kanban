package ru.yandex.javacource.bolonskij.schedule.task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtasksId = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(int id, String name, String description) {
        super(id, name, description);
    }

    public void setSubtask(Subtask subtask) {
        if (subtask.getId() != getId()) {
        removeSubtask(subtask.getId());
        subtasksId.add(subtask.getId());
        }
    }

    public List<Integer> getSubtasksId() {
        if (!subtasksId.isEmpty()) return subtasksId;
        return null;
    }

    public void clearSubtasks() {
        this.subtasksId.clear();
    }

    public void removeSubtask(int id) {
        Object i = id;
        subtasksId.remove(i);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskId=" + getSubtasksId() +
                "} " + super.toString();
    }
}
