package ru.yandex.javacource.bolonskij.schedule;

import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;
import ru.yandex.javacource.bolonskij.schedule.task.manager.TaskManager;


public class Main {

    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        System.out.println("Поехали!");


        Task task1 = new Task("task1", "taskDescription1");
        Task task2 = new Task("task2", "taskDescription2");

        int idTask1 = taskManager.addTask(task1, Status.NEW);
        int idTask2 = taskManager.addTask(task2, Status.NEW);

        Epic epic1 = new Epic("epic1", "epic1Description1");
        Epic epic2 = new Epic("epic2", "epic1Description2");

        int idEpic1 = taskManager.addEpic(epic1);
        int idEpic2 = taskManager.addEpic(epic2);

        Subtask subtask1 = new Subtask("subtask1", "subtaskDescription1");
        Subtask subtask2 = new Subtask("subtask2", "subtaskDescription2");
        Subtask subtask3 = new Subtask("subtask3", "subtaskDescription3");
        Subtask subtask4 = new Subtask("subtask4", "subtaskDescription4");
        Subtask subtask5 = new Subtask("subtask5", "subtaskDescription5");

        int idSubtask1 = taskManager.addSubtask(idEpic1, subtask1, Status.NEW);
        int idSubtask2 = taskManager.addSubtask(idEpic1, subtask2, Status.NEW);
        int idSubtask3 = taskManager.addSubtask(idEpic2, subtask3, Status.NEW);
        int idSubtask4 = taskManager.addSubtask(idEpic2, subtask4, Status.NEW);
        int idSubtask5 = taskManager.addSubtask(idEpic2, subtask5, Status.NEW);

        System.out.println(taskManager.getTask(idTask2));
        System.out.println(taskManager.getEpic(idEpic1));
        System.out.println(taskManager.getSubtask(idSubtask5));
        System.out.println(taskManager.getSubtasksInEpic(idEpic1));

        printAll();

        System.out.println("Изменяю статусы задач и подзадач\n");

        taskManager.updateTask(task1, Status.DONE);
        taskManager.updateTask(task2,Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask1, Status.DONE);
        taskManager.updateSubtask(subtask2, Status.DONE);
        taskManager.updateSubtask(subtask3, Status.DONE);
        taskManager.updateSubtask(subtask4, Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask5, Status.DONE);

        System.out.println("Проверка обновления статусов\n");

        printAll();

        System.out.println("Удаляю одну задачу, один эпик и одну подзадачу\n");

        taskManager.removeTask(idTask1);
        taskManager.removeEpic(idEpic1);
        taskManager.removeSubtask(idSubtask3);

        printAll();

        System.out.println("Удаляю все задачи\n");

        taskManager.removeAllTasks();
        printAll();

        System.out.println("Удаляю все подзадачи\n");

        taskManager.removeAllSubtasks();
        printAll();

        System.out.println("Удаляю все эпики\n");

        taskManager.removeAllEpics();
        printAll();

    }


    public static void printAll(){

        System.out.println("Список задач:\n");
        System.out.println(taskManager.getAllTasks());
        System.out.println();
        System.out.println("Список эпиков:\n");
        System.out.println(taskManager.getAllEpics());
        System.out.println();
        System.out.println("Список подзадач:\n");
        System.out.println(taskManager.getAllSubtasks());
        System.out.println();
    }
}
