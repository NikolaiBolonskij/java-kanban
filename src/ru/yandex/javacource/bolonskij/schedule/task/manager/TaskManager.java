package ru.yandex.javacource.bolonskij.schedule.task.manager;

import ru.yandex.javacource.bolonskij.schedule.task.Epic;
import ru.yandex.javacource.bolonskij.schedule.task.Status;
import ru.yandex.javacource.bolonskij.schedule.task.Subtask;
import ru.yandex.javacource.bolonskij.schedule.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int id = 0;

    public int nextId() {
        id++;
        return id;
    }

                                                        // добавление задач

    public int addTask(Task task, Status status) {
        int id = nextId();
        task.setStatus(status);
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public int addEpic(Epic epic) {
        int id = nextId();
        epic.setId(id);
        updateEpicStatus(id);
        epics.put(id, epic);

        return id;
    }

    public Integer addSubtask(int epicId, Subtask subtask, Status status) {
        if (epics.get(epicId) == null) {
            return null;
        }
        int id = nextId();
        subtask.setId(id);
        subtask.setEpicId(epicId);
        subtask.setStatus(status);
        subtasks.put(id, subtask);
        epics.get(epicId).setSubtask(id);
        updateEpicStatus(epicId);
        return id;
    }

                                                    //получение задач

    public Task getTask(int id) {
        if (tasks.get(id) == null) {
            return null;
        }
        return tasks.get(id);
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Epic getEpic(int id) {
        if (epics.get(id) == null) {
            return  null;
        }
        return epics.get(id);
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public Subtask getSubtask(int id) {
        if (subtasks.get(id) == null) {
            return null;
        }
        return subtasks.get(id);
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Subtask> getSubtasksInEpic(int epicId) {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>();
        Epic epic = epics.get(epicId);
            if (epic == null) {
                return null;
            }
        for (Integer id : epic.getSubtasks()) {
            if (subtasks.containsKey(id)) {
                subtaskArrayList.add(subtasks.get(id));
            }
        }
        return subtaskArrayList;
    }

                                                            //обновление

    public void updateTask(Task updatedTask, Status status) {
        final int id = updatedTask.getId();
        final Task savedTask = tasks.get(id);
        if (savedTask == null) {
            return;
        }
        updatedTask.setStatus(status);
        tasks.put(id, updatedTask);
    }

    public void updateSubtask(Subtask updatedSubtask, Status status) {
        Subtask savedSubtask = subtasks.get(updatedSubtask.getId());
        if (savedSubtask == null) {
            return;
        }
        int epicId = savedSubtask.getEpicId();
        updatedSubtask.setStatus(status);
        subtasks.putIfAbsent(id, updatedSubtask);
        epics.get(epicId).setSubtask(updatedSubtask.getId());
        updateEpicStatus(epicId);
    }


    public void updateEpic(Epic updatedEpic) {
        Epic savedEpic = epics.get(updatedEpic.getId());
        if (savedEpic == null) {
            return;
        }
        savedEpic.setName(updatedEpic.getName());
        savedEpic.setDescription(updatedEpic.getDescription());
    }

    private void updateEpicStatus(int epicId) {
        Status status = null;
        int checkDone = 0;
        Epic epic = getEpic(epicId);
        if (epic == null) {
            return;
        }

        if (epic.getSubtasks() == null) {
            status = Status.NEW;
        } else {
            for (Integer subtaskId : epic.getSubtasks()) {
                Subtask subtask = subtasks.get(subtaskId);
                Status subStatus = subtask.getStatus();
                if (subStatus == Status.DONE) {
                    checkDone++;
                }
            }

            if (checkDone == 0) {
                status = Status.NEW;
            } else if (checkDone < epic.getSubtasks().size() && checkDone > 0) {
                status = Status.IN_PROGRESS;
            } else if (checkDone == epic.getSubtasks().size()) {
                status = Status.DONE;
            }
        }
        epic.setStatus(status);
    }

                                                                //удаление

    public void removeTask(int id) {
        if (tasks.get(id) == null) {
            return;
        }
        tasks.remove(id);
    }

    public void removeEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic == null) {
            return;
        }
        for (Integer subtaskId : epic.getSubtasks()){
            subtasks.remove(subtaskId);
        }
    }

    public void removeSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeSubtask(id);
        updateEpicStatus(epic.getId());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            updateEpicStatus(epic.getId());
        }
    }
}