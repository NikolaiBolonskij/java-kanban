package ru.yandex.javacource.bolonskij.schedule.utility;

import ru.yandex.javacource.bolonskij.schedule.history.HistoryManager;
import ru.yandex.javacource.bolonskij.schedule.history.InMemoryHistoryManager;
import ru.yandex.javacource.bolonskij.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.bolonskij.schedule.manager.TaskManager;
import ru.yandex.javacource.bolonskij.schedule.task.Task;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager<Task> getDefaultHistory() {
        return new InMemoryHistoryManager<>();
    }
}
