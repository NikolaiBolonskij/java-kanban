package ru.yandex.javacource.bolonskij.schedule.history;

import ru.yandex.javacource.bolonskij.schedule.task.Task;

import java.util.ArrayList;


public class inMemoryHistoryManager<T extends Task> implements HistoryManager<T> {

    ArrayList<T> history = new ArrayList<>();

    @Override
    public ArrayList<T> getHistory(){
        return history;
    }

    @Override
    public void add(T t) {

        if (history.size() < 10) {
            history.add(t);
        } else {
            history.add(t);
            history.removeFirst();
        }
    }
}
