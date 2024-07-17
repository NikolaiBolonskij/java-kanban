package ru.yandex.javacource.bolonskij.schedule.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.bolonskij.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;

class EpicTest {

    @Test
    void CanNotAddEpicToItself() {

        InMemoryTaskManager manager = Managers.getDefault();

        Epic epic = new Epic("Epic" , "epic");
        final int epicId = manager.addEpic(epic);
        Task epic1 = (Task) manager.getEpic(epicId);

        try {
            manager.addSubtask((Subtask) epic1);
            Assertions.fail("Нельзя добавть эпик в самого себя в виде подзадачи");
        } catch (Exception exception){

        }
    }



}