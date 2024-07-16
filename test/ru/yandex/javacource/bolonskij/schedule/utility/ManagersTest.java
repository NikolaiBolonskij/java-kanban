package ru.yandex.javacource.bolonskij.schedule.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.bolonskij.schedule.manager.TaskManager;

class ManagersTest {

    @Test
    void addNewInitialisedTaskManager() {

        TaskManager manager = new Managers().getDefault();
        TaskManager taskManager = new Managers().getDefault();

        Assertions.assertNotEquals(manager, taskManager);
    }

}