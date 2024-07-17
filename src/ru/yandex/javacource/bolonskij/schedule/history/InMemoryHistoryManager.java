package ru.yandex.javacource.bolonskij.schedule.history;

import ru.yandex.javacource.bolonskij.schedule.task.Task;

import java.util.ArrayList;
import java.util.List;


public class InMemoryHistoryManager<T extends Task> implements HistoryManager<T> {

    public static final int MAX_SIZE = 10;

    private final List<T> history = new ArrayList<>();

    @Override
    public List<T> getHistory(){
        return history;
    }

    @Override
    public void add(T task) {
        if (task == null) {
            return;
        }
        if (history.size() >= MAX_SIZE) {
            history.removeFirst();
        }
        history.add(task);
    }
}
