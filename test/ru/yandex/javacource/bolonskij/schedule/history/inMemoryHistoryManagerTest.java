package ru.yandex.javacource.bolonskij.schedule.history;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.bolonskij.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Task;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;

import java.util.List;

class InMemoryHistoryManagerTest {

    @Test
    void tasksCorrectSaveInHistory() {
        InMemoryTaskManager manager = Managers.getDefault();

        Task task1 = new Task("Name", "Desc");
        int idTask1 = manager.addTask(task1);
        manager.getTask(idTask1);
        List<Task> tasks = manager.getHistoryManager();
        Task savedTask = tasks.get(0);
        Task task2 = new Task(idTask1, "Name", "NewDesk", Status.IN_PROGRESS);
        manager.updateTask(task2);
        List<Task> updatedTasks = manager.getHistoryManager();
        Task updatedTask = updatedTasks.get(0);
        Assertions.assertEquals(savedTask, updatedTask);

    }



}