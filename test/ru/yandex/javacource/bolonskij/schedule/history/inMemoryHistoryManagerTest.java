package ru.yandex.javacource.bolonskij.schedule.history;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.bolonskij.schedule.manager.TaskManager;
import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;

import java.util.ArrayList;

class inMemoryHistoryManagerTest {

    @Test
    void tasksCorrectSaveInHistory() {
        TaskManager manager = new Managers().getDefault();

        final int idTask1 = manager.addTask(new Task("Test1", "deskTest1", Status.NEW));
        final int idTask2 = manager.addTask(new Task("Test2", "deskTest2", Status.NEW));

        Task task1 = manager.getTask(idTask1);
        Task task2 = manager.getTask(idTask2);

        ArrayList<Task> tasksSavedInHistory = manager.getHistory();

        Assertions.assertEquals(task1, tasksSavedInHistory.get(0), "Задача в истории не совпадает");
        Assertions.assertEquals(task2, tasksSavedInHistory.get(1), "Задача в истории не совпадает");

    }



}