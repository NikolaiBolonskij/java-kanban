package ru.yandex.javacource.bolonskij.schedule.utility;

import ru.yandex.javacource.bolonskij.schedule.history.InMemoryHistoryManager;
import ru.yandex.javacource.bolonskij.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.bolonskij.schedule.task.Task;

public class Managers {

    public static InMemoryTaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static InMemoryHistoryManager<Task> getDefaultHistory() {
        return new InMemoryHistoryManager<>();
    }
}
