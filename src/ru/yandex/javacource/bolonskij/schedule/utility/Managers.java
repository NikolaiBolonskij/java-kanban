package ru.yandex.javacource.bolonskij.schedule.utility;

import ru.yandex.javacource.bolonskij.schedule.history.inMemoryHistoryManager;
import ru.yandex.javacource.bolonskij.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.bolonskij.schedule.manager.TaskManager;
import ru.yandex.javacource.bolonskij.schedule.task.Task;

public class Managers {

    public TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public inMemoryHistoryManager<Task> getDefaultHistory() {
        return new inMemoryHistoryManager<>();
    }
}
