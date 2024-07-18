package ru.yandex.javacource.bolonskij.schedule.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.bolonskij.schedule.manager.TaskManager;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;

class SubtaskTest {

    @Test
    void canNotAddSubtaskAsEpicToItself() {

        TaskManager manager = Managers.getDefault();
        Epic epic = new Epic("1", "1");
        int epicID = manager.addEpic(epic);
        Task subtask = new Subtask("Subtask", "subtask", epicID, Status.NEW);
        int subtaskID = manager.addSubtask((Subtask) subtask);


        try {
            manager.addEpic((Epic) subtask);
            Assertions.fail("Нельзя сделать подзадачу своим же эпиком");
        } catch (Exception exception) {
        }
    }

}