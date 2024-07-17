package ru.yandex.javacource.bolonskij.schedule.history;

import ru.yandex.javacource.bolonskij.schedule.task.Task;

import java.util.List;

public interface HistoryManager<T extends Task> {
    List<T> getHistory();

    void add(T t);
}
