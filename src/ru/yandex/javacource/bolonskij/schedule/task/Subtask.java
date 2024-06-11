package ru.yandex.javacource.bolonskij.schedule.task;

public class Subtask extends Task {
    private int epicHolder;

    public Subtask(String name, String description) {
        super(name, description);
    }

    public int getEpicId() {
        return epicHolder;
    }

    public void setEpicId(int epicHolder) {
        this.epicHolder = epicHolder;
    }
}
