package ru.yandex.javacource.bolonskij.schedule.manager;

import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;

import java.util.ArrayList;

public interface TaskManager {
    int nextId();

    int addTask(Task task);

    int addEpic(Epic epic);

    Integer addSubtask(Subtask subtask, int epicId);

    Task getTask(int id);

    ArrayList<Task> getAllTasks();

    Epic getEpic(int id);

    ArrayList<Epic> getAllEpics();

    Subtask getSubtask(int id);

    ArrayList<Subtask> getAllSubtasks();

    ArrayList<Subtask> getSubtasksInEpic(int epicId);

    void updateTask(Task updatedTask, Status status);

    void updateSubtask(Subtask updatedSubtask, Status status);

    void updateEpic(Epic updatedEpic);

    void removeTask(int id);

    void removeEpic(int id);

    void removeSubtask(int id);

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    void updateEpicStatus(Epic epic);

    ArrayList<Task> getHistory();

}
