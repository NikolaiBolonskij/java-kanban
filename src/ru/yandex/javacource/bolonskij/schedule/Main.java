package ru.yandex.javacource.bolonskij.schedule;

import ru.yandex.javacource.bolonskij.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;


public class Main {

    static InMemoryTaskManager manager = Managers.getDefault();

    public static void main(String[] args) {

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
        for (Task task : manager.getHistoryManager()) {
            System.out.println("\t" + task + "\n");
        }
    }
}
