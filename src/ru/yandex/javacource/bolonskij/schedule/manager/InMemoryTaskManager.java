package ru.yandex.javacource.bolonskij.schedule.manager;

import ru.yandex.javacource.bolonskij.schedule.history.HistoryManager;
import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;
import ru.yandex.javacource.bolonskij.schedule.utility.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final HistoryManager<Task> historyManager = Managers.getDefaultHistory();

    private int id = 0;

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
        int epicId = subtask.getEpicId();

        int id = nextId();
        subtask.setId(id);
        subtasks.put(id, subtask);

        epics.get(epicId).setSubtask(subtask);
        updateEpicStatus(epics.get(epicId));

        return id;
    }

                                                    //получение задач

    @Override
    public Task getTask(int id) {
        final Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Epic getEpic(int id) {
        final Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public Subtask getSubtask(int id) {
        final Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasksInEpic(int epicId) {
        ArrayList<Subtask> subtaskList = new ArrayList<>();
        Epic epic = epics.get(epicId);
            if (epic == null) {
                return null;
            }
            if (epic.getSubtasksId() == null) return null;
        for (Integer id : epic.getSubtasksId()) {
            if (subtasks.containsKey(id)) {
                subtaskList.add(subtasks.get(id));
            }
        }
        return subtaskList;
    }

                                                            //обновление

    @Override
    public void updateTask(Task task) {
        final Task savedTask = tasks.get(task.getId());
        if (savedTask == null) {
            return;
        }
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        Subtask savedSubtask = subtasks.get(subtask.getId());
        if (savedSubtask == null) {
            return;
        }
        int epicId = subtask.getEpicId();
        subtasks.put(subtask.getId(), subtask);
        epics.get(epicId).setSubtask(subtask);
        updateEpicStatus(epics.get(epicId));
    }


    @Override
    public void updateEpic(Epic epic) {
        final Epic savedEpic = epics.get(epic.getId());
        if (savedEpic == null) {
            return;
        }
        for(Subtask subtask : getSubtasksInEpic(savedEpic.getId())) {
            epic.setSubtask(subtask);
        }
        epic.setStatus(savedEpic.getStatus());
        epics.put(epic.getId(), epic);
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
        epic.removeSubtask(subtask.getId());
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
    public List<Task> getHistoryManager() {
        return historyManager.getHistory();
    }

    private void updateEpicStatus(Epic epic) {
        Status status = null;
        int checkDone = 0;
        if (epic == null) {
            return;
        }
        if (epic.getSubtasksId() == null) {
            status = Status.NEW;
        } else {
            for (Subtask subtask : getSubtasksInEpic(epic.getId())) {
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

    private int nextId() {
        id++;
        return id;
    }
}