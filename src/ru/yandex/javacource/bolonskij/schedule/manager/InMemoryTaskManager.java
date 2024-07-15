package ru.yandex.javacource.bolonskij.schedule.manager;

import ru.yandex.javacource.bolonskij.schedule.history.inMemoryHistoryManager;
import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private inMemoryHistoryManager<Task> history = new Managers().getDefaultHistory();

    private int id = 0;

    @Override
    public int nextId() {
        id++;
        return id;
    }

                                                        // добавление задач

    @Override
    public int addTask(Task task) {
        int id = nextId();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    @Override
    public int addEpic(Epic epic) {
        int id = nextId();
        epic.setId(id);
        updateEpicStatus(epic);
        epics.put(id, epic);
        return id;
    }

    @Override
    public Integer addSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return null;
        }
        int id = nextId();
        subtask.setId(id);
        subtasks.put(id, subtask);

        epic.setSubtask(id);
        updateEpicStatus(epic);

        return id;
    }

                                                    //получение задач

    @Override
    public Task getTask(int id) {
        history.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Epic getEpic(int id) {
        history.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public Subtask getSubtask(int id) {
        history.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasksInEpic(int epicId) {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>();
        Epic epic = epics.get(epicId);
            if (epic == null) {
                return null;
            }
        for (Integer id : epic.getSubtasksId()) {
            if (subtasks.containsKey(id)) {
                subtaskArrayList.add(subtasks.get(id));
            }
        }
        return subtaskArrayList;
    }

                                                            //обновление

    @Override
    public void updateTask(Task updatedTask, Status status) {
        final int id = updatedTask.getId();
        final Task savedTask = tasks.get(id);
        if (savedTask == null) {
            return;
        }
        updatedTask.setStatus(status);
        tasks.put(id, updatedTask);
    }

    @Override
    public void updateSubtask(Subtask updatedSubtask, Status status) {
        Subtask savedSubtask = subtasks.get(updatedSubtask.getId());
        if (savedSubtask == null) {
            return;
        }
        int epicId = savedSubtask.getEpicId();
        updatedSubtask.setStatus(status);
        subtasks.putIfAbsent(id, updatedSubtask);
        epics.get(epicId).setSubtask(updatedSubtask.getId());
        updateEpicStatus(epics.get(epicId));
    }


    @Override
    public void updateEpic(Epic updatedEpic) {
        Epic savedEpic = epics.get(updatedEpic.getId());
        if (savedEpic == null) {
            return;
        }
        savedEpic.setName(updatedEpic.getName());
        savedEpic.setDescription(updatedEpic.getDescription());
    }

                                                                //удаление

    @Override
    public void removeTask(int id) {
        if (tasks.get(id) == null) {
            return;
        }
        tasks.remove(id);
    }

    @Override
    public void removeEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic == null) {
            return;
        }
        for (Integer subtaskId : epic.getSubtasksId()){
            subtasks.remove(subtaskId);
        }
    }

    @Override
    public void removeSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeSubtask(id);
        updateEpicStatus(epic);
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            updateEpicStatus(epic);
        }
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        Status status = null;
        int checkDone = 0;
        if (epic == null) {
            return;
        }
        if (epic.getSubtasksId() == null) {
            status = Status.NEW;
        } else {
            for (Integer subtaskId : epic.getSubtasksId()) {
                Subtask subtask = subtasks.get(subtaskId);
                Status subStatus = subtask.getStatus();
                if (subStatus == Status.DONE) {
                    checkDone++;
                }
            }
            if (checkDone == 0) {
                status = Status.NEW;
            } else if (checkDone < epic.getSubtasksId().size() && checkDone > 0) {
                status = Status.IN_PROGRESS;
            } else if (checkDone == epic.getSubtasksId().size()) {
                status = Status.DONE;
            }
        }
        epic.setStatus(status);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history.getHistory();
    }
}