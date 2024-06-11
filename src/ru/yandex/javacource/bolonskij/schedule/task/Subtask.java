package ru.yandex.javacource.bolonskij.schedule.task;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String description, int epicId, Status status) {
        super(name, description, status);
        this.epicId =  epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicHolder) {
        this.epicId = epicHolder;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                "} " + super.toString();
    }
}
