package ru.yandex.javacource.bolonskij.schedule.task;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String description, Status status) {
        super(name, description, status);
    }

    public void setEpicId(int id) {
        if (id != getId()) {
        epicId = id;
        }
    }
    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                "} " + super.toString();
    }
}
