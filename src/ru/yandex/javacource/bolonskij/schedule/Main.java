package ru.yandex.javacource.bolonskij.schedule;

import ru.yandex.javacource.bolonskij.schedule.manager.TaskManager;
import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;


public class Main {

    static TaskManager manager = new Managers().getDefault();

    public static void main(String[] args) {
        System.out.println("Поехали!");


        Task task1 = new Task("task1", "taskDescription1", Status.NEW);
        Task task2 = new Task("task2", "taskDescription2", Status.NEW);

        int idTask1 = manager.addTask(task1);
        int idTask2 = manager.addTask(task2);

        Epic epic1 = new Epic("epic1", "epic1Description1");
        Epic epic2 = new Epic("epic2", "epic1Description2");

        int idEpic1 = manager.addEpic(epic1);
        int idEpic2 = manager.addEpic(epic2);

        Subtask subtask1 = new Subtask("subtask1", "subtaskDescription1", idEpic1, Status.NEW);
        Subtask subtask2 = new Subtask("subtask2", "subtaskDescription2", idEpic1, Status.NEW);
        Subtask subtask3 = new Subtask("subtask3", "subtaskDescription3", idEpic2, Status.NEW);
        Subtask subtask4 = new Subtask("subtask4", "subtaskDescription4", idEpic2, Status.NEW);
        Subtask subtask5 = new Subtask("subtask5", "subtaskDescription5", idEpic2, Status.NEW);

        int idSubtask1 = manager.addSubtask(subtask1);
        int idSubtask2 = manager.addSubtask(subtask2);
        int idSubtask3 = manager.addSubtask(subtask3);
        int idSubtask4 = manager.addSubtask(subtask4);
        int idSubtask5 = manager.addSubtask(subtask5);

        System.out.println(manager.getTask(idTask2));
        System.out.println(manager.getEpic(idEpic1));
        System.out.println(manager.getSubtask(idSubtask5));
        System.out.println(manager.getSubtasksInEpic(idEpic1));

        printAll();

        System.out.println("Изменяю статусы задач и подзадач\n");

        manager.updateTask(task1, Status.DONE);
        manager.updateTask(task2,Status.IN_PROGRESS);
        manager.updateSubtask(subtask1, Status.DONE);
        manager.updateSubtask(subtask2, Status.DONE);
        manager.updateSubtask(subtask3, Status.DONE);
        manager.updateSubtask(subtask4, Status.IN_PROGRESS);
        manager.updateSubtask(subtask5, Status.DONE);

        System.out.println("Проверка обновления статусов\n");

        printAll();

        System.out.println("Проверяю историю \n");

        manager.getTask(idTask1);
        manager.getTask(idTask2);
        manager.getTask(idTask2);
        manager.getEpic(idEpic1);
        manager.getSubtask(idSubtask5);
        printHistory();
        manager.getEpic(idEpic2);
        manager.getEpic(idEpic2);
        manager.getTask(idTask1);
        printHistory();

        System.out.println("Удаляю одну задачу, один эпик и одну подзадачу\n");

        manager.removeTask(idTask1);
        manager.removeEpic(idEpic1);
        manager.removeSubtask(idSubtask3);

        printAll();

        System.out.println("Удаляю все задачи\n");

        manager.removeAllTasks();
        printAll();

        System.out.println("Удаляю все подзадачи\n");

        manager.removeAllSubtasks();
        printAll();

        System.out.println("Удаляю все эпики\n");

        manager.removeAllEpics();
        printAll();



    }


    public static void printAll(){

        System.out.println("Список задач:\n");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task + "\n");
        }

        System.out.println("------------\n");
        System.out.println("Список эпиков:\n");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println("\t !!!\t" + epic + "\n");

            for (Task task : manager.getSubtasksInEpic(epic.getId())) {
                System.out.println("\t -->\t" + task + "\n");
            }
        }

        System.out.println("------------\n");
        System.out.println("Список подзадач:\n");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask + "\n");
        }

        printHistory();
    }

    public static void printHistory() {
        System.out.println("------------\n");
        System.out.println("История запросов");
        for (Task task : manager.getHistory()) {
            System.out.println("\t" + task + "\n");
        }
    }
}
