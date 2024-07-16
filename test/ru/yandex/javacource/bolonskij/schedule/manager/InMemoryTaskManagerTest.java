package ru.yandex.javacource.bolonskij.schedule.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;

import java.util.ArrayList;
import java.util.HashMap;

class InMemoryTaskManagerTest {

    protected TaskManager manager = new  Managers().getDefault();

    final Task task = new Task("Task" , "TaskDesk", Status.NEW);
    final int saved = manager.addTask(task);


    Epic epic = new Epic("Epic", "EpicDesk");
    int savedEpic = manager.addEpic(epic);

    final Subtask subtask = new Subtask("Subtask", "SubtaskDesk", Status.NEW);
    final int savedSubtask = manager.addSubtask(subtask, savedEpic);

    @Test
    void taskDoesNotChangesAfterAddition() {

        Task savedTask = manager.getTask(saved);

        Assertions.assertEquals(task.getName(), savedTask.getName());
        Assertions.assertEquals(task.getDescription(), savedTask.getDescription());
        Assertions.assertEquals(task.getStatus(), savedTask.getStatus());
        Assertions.assertEquals(task.getId(), savedTask.getId());
    }

    @Test
    void tasksWithEqualIdAreEqualTask(){

        final Task actual = manager.getTask(saved);

        Assertions.assertEquals(task, actual);
    }
    
    @Test
    void epicsWithEqualIdAreEqual() {
        final Epic actual = manager.getEpic(savedEpic);

        Assertions.assertEquals(epic, actual);
    }

    @Test
    void subtasksWithEqualIdAreEqual() {

        final Subtask actual = manager.getSubtask(savedSubtask);

        Assertions.assertEquals(subtask, actual);
    }

    @Test
    void addNewTask() {

        TaskManager manager = new Managers().getDefault();
        Task task = new Task("Test addNewTask", "Test addNewTask description", Status.NEW);
        final int taskId = manager.addTask(task);
        final Task savedTask = manager.getTask(taskId);

        Assertions.assertNotNull(savedTask, "Задача не найдена.");
        Assertions.assertEquals(task, savedTask, "Задачи не совпадают.");

        final ArrayList<Task> tasks = manager.getAllTasks();

        Assertions.assertNotNull(tasks, "Задачи не возвращаются.");
        Assertions.assertEquals(1, tasks.size(), "Неверное количество задач.");
        Assertions.assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void addDifferentTasks() {

        TaskManager manager = new Managers().getDefault();
        Epic epic1 = new Epic("TestEpic1", "TestEpic1");
        Epic epic2 = new Epic("TestEpic2", "TestEpic2");
        Subtask subtask1 = new Subtask("TestSubtask1", "TestSubtask1", Status.NEW);
        Subtask subtask2 = new Subtask("TestSubtask2", "TestSubtask2", Status.NEW);
        Subtask subtask3 = new Subtask("TestSubtask3", "TestSubtask3", Status.NEW);

        int epic1Id = manager.addEpic(epic1);
        int epic2Id = manager.addEpic(epic2);
        final int subtask1Id = manager.addSubtask(subtask1, epic1Id);
        final int subtask2Id = manager.addSubtask(subtask2, epic1Id);
        final int subtask3Id = manager.addSubtask(subtask3, epic2Id);

        final Epic savedEpic1 = manager.getEpic(epic1Id);
        final Epic savedEpic2 = manager.getEpic(epic2Id);
        final Subtask savedSubtask1 = manager.getSubtask(subtask1Id);
        final Subtask savedSubtask2 = manager.getSubtask(subtask2Id);
        final Subtask savedSubtask3 = manager.getSubtask(subtask3Id);

        final ArrayList<Epic> epics = manager.getAllEpics();
        final ArrayList<Subtask> subtasks = manager.getAllSubtasks();
        final ArrayList<Subtask> subtasksInEpic1 = manager.getSubtasksInEpic(epic1Id);
        final ArrayList<Subtask> subtasksInEpic2 = manager.getSubtasksInEpic(epic2Id);

        Assertions.assertNotNull(epics, "Эпики не возвращаются");
        Assertions.assertEquals(2, epics.size(), "Неверное количество эпиков");
        Assertions.assertEquals(epic1, manager.getEpic(epic1Id), "Эпики не совпадают");

        Assertions.assertNotNull(subtasks, "Подзадачи не возвращаются");
        Assertions.assertEquals(3, subtasks.size(), "Неверное количество подзадач");
        Assertions.assertEquals(subtask1, manager.getSubtask(subtask1Id), "Подзадачи не совпадают");

        Assertions.assertNotNull(subtasksInEpic1, "Подзадачи не привязываются к эпику");
        Assertions.assertEquals(savedSubtask1, subtasksInEpic1.get(0),
                "Задачи в менеджере и в эпике не совпадают");
        Assertions.assertEquals(savedSubtask3, subtasksInEpic2.get(0),
                "Задачи в менеджере и в эпике не совпадают");
    }

    @Test
    void taskStatusChangeTest() {

        TaskManager manager = new Managers().getDefault();
        Task task1 = new Task("task1", "taskDescription1", Status.NEW);
        Task task2 = new Task("task2", "taskDescription2", Status.NEW);

        int idTask1 = manager.addTask(task1);
        int idTask2 = manager.addTask(task2);

        Epic epic1 = new Epic("epic1", "epic1Description1");
        Epic epic2 = new Epic("epic2", "epic1Description2");

        int idEpic1 = manager.addEpic(epic1);
        int idEpic2 = manager.addEpic(epic2);

        Subtask subtask1 = new Subtask("subtask1", "subtaskDescription1", Status.NEW);
        Subtask subtask2 = new Subtask("subtask2", "subtaskDescription2", Status.NEW);
        Subtask subtask3 = new Subtask("subtask3", "subtaskDescription3", Status.NEW);
        Subtask subtask4 = new Subtask("subtask4", "subtaskDescription4", Status.NEW);
        Subtask subtask5 = new Subtask("subtask5", "subtaskDescription5", Status.NEW);

        int idSubtask1 = manager.addSubtask(subtask1, idEpic1);
        int idSubtask2 = manager.addSubtask(subtask2, idEpic1);
        int idSubtask3 = manager.addSubtask(subtask3, idEpic1);
        int idSubtask4 = manager.addSubtask(subtask4, idEpic2);
        int idSubtask5 = manager.addSubtask(subtask5, idEpic2);

        manager.updateTask(task1, Status.DONE);
        manager.updateTask(task2,Status.IN_PROGRESS);
        manager.updateSubtask(subtask1, Status.DONE);
        manager.updateSubtask(subtask2, Status.DONE);
        manager.updateSubtask(subtask3, Status.DONE);
        manager.updateSubtask(subtask4, Status.IN_PROGRESS);
        manager.updateSubtask(subtask5, Status.DONE);

        Assertions.assertEquals(task1.getStatus(), Status.DONE, "Не обновляется статус задачи");
        Assertions.assertEquals(epic1.getStatus(), Status.DONE, "Не обновляется статус эпика");
        Assertions.assertEquals(epic2.getStatus(), Status.IN_PROGRESS, "Не обновляется статус эпика");
        Assertions.assertEquals(subtask1.getStatus(), Status.DONE, "Не обновляется статус подзадачи");
    }

    @Test
    void removeTasksById() {
        TaskManager manager1 = manager;
        manager1.removeTask(saved);
        manager1.removeEpic(savedEpic);
        manager1.removeSubtask(savedSubtask);

        Assertions.assertNull(manager1.getTask(saved));
        Assertions.assertNull(manager1.getEpic(savedEpic));
        Assertions.assertNull(manager1.getSubtask(savedSubtask));
    }

    @Test
    void removeAll() {
        TaskManager rmManager = manager;
        ArrayList<Task> clearList = new ArrayList<>();

        rmManager.removeAllSubtasks();
        Assertions.assertEquals(clearList, rmManager.getAllSubtasks(), "Подзадачи не удалены");
        Assertions.assertEquals(clearList, rmManager.getEpic(savedEpic).getSubtasks(), "Подзадачи не удалены из эпика");

        rmManager.removeAllTasks();
        rmManager.removeAllEpics();

        Assertions.assertEquals(clearList, rmManager.getAllTasks(), "Задачи не удалены");
        Assertions.assertEquals(clearList, rmManager.getAllEpics(), "Эпики не удалены");
    }
}