package ru.yandex.javacource.bolonskij.schedule.history;

import ru.yandex.javacource.bolonskij.schedule.task.Task;

import java.util.ArrayList;

public interface HistoryManager<T extends Task> {
    ArrayList<T> getHistory();

    void add(T t);
}
