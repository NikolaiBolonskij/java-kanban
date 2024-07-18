package ru.yandex.javacource.bolonskij.schedule.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.bolonskij.schedule.manager.TaskManager;

class ManagersTest {

    @Test
    void addNewInitialisedTaskManager() {

        TaskManager manager = Managers.getDefault();
        TaskManager taskManager = Managers.getDefault();

        Assertions.assertNotEquals(manager, taskManager);
    }

}