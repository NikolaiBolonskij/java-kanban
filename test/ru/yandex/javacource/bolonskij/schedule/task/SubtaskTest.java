package ru.yandex.javacource.bolonskij.schedule.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.bolonskij.schedule.manager.TaskManager;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;

class SubtaskTest {

    @Test
    void canNotAddSubtaskAsEpicToItself() {

        TaskManager manager = new Managers().getDefault();
        Epic epic = new Epic("1", "1");
        int epicID = manager.addEpic(epic);
        Subtask subtask = new Subtask("Subtask", "subtask", Status.NEW);
        int subtaskID = manager.addSubtask(subtask, epicID);


        try {
            int exception = manager.addSubtask(subtask, subtaskID);
            Assertions.fail("Нельзя сделать подзадачу своим же эпиком");
        } catch (Exception exception) {

        }
    }

}