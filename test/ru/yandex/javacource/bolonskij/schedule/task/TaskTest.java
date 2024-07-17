package ru.yandex.javacource.bolonskij.schedule.task;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void shouldBeEqualOnlyThenEqual() {

        final Task task = new Task("TestTask1", "TestDesk1", Status.NEW);
        final Task equalTask = task;
        final Task task2 = new Task("TestTask2", "TestDesk2", Status.NEW);

        Assertions.assertTrue(task.equals(equalTask), "Одинаковые задачи не совпадают");
        Assertions.assertFalse(task.equals(task2), "Разные задачи распознаются как одинаковые");
        Assertions.assertFalse(task.equals(null), "Не возвращает False при передаче пустого значения");
    }

}