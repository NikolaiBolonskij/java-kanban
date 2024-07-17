package ru.yandex.javacource.bolonskij.schedule.manager;

import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    int addTask(Task task);

    int addEpic(Epic epic);

    Integer addSubtask(Subtask subtask);

    Task getTask(int id);

    ArrayList<Task> getAllTasks();

    Epic getEpic(int id);

    ArrayList<Epic> getAllEpics();

    Subtask getSubtask(int id);

    ArrayList<Subtask> getAllSubtasks();

    ArrayList<Subtask> getSubtasksInEpic(int epicId);

    void updateTask(Task updatedTask);

    void updateSubtask(Subtask updatedSubtask);

    void updateEpic(Epic updatedEpic);

    void removeTask(int id);

    void removeEpic(int id);

    void removeSubtask(int id);

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    List<Task> getHistoryManager();

}
