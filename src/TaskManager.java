import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static HashMap<Integer, Epic> epicTasks = new HashMap<>();
    private static HashMap<Integer, Subtask> allSubtasks = new HashMap<>();

    private static int id = 0;

    public static int createNewId() {
        id++;
        return id;
    }

    //СОХРАНЕНИЕ И ОБНОВЛЕНИЕ

    // сохраняет новую задачу Task
    public void setTask(Task task) {
        task.setId(createNewId());
        setNewStatus(task, null);
        tasks.put(task.getId(), task);
    }

    // сохраняет новый эпик
    public void setEpic(Epic epic) {
        epic.setStatus(getEpicStatus(epic));
        epic.setId(createNewId());
        epicTasks.put(epic.getId(), epic);
    }

    //обновляет эпик
    public void setNewEpic(Epic epic) {
        epic.setStatus(getEpicStatus(epic));
        epicTasks.put(epic.getId(), epic);
    }

    // сохраняет новую подзадачу в эпик
    public void setSubtask (Epic epic, Subtask subtask){
        subtask.setStatus("NEW");
        subtask.setId(createNewId());
        epic.setSubtask(subtask.getId());
        allSubtasks.put(subtask.getId(), subtask);
    }

    //ОБНОВЛЕНИЕ СТАТУСОВ

    //обновляет статус задачи Task
    public void setNewStatus(Task task, String status) {
        task.setStatus(status);
        tasks.put(task.getId(), task);
    }

    // расчитывает статус для эпика
    public String getEpicStatus(Epic epic) {
        String status = "";
        int checkDone = 0;

        if (epic.getSubtaskId() == null) {
            status = "NEW";
        } else {
            for (Integer subtaskId : epic.getSubtaskId()) {
                Subtask subtask = allSubtasks.get(subtaskId);
                Status subStatus = subtask.getStatus();
                if (subStatus == Status.DONE) {
                    checkDone++;
                }
            }

            if (checkDone == 0) {
                status = "NEW";
            } else if (checkDone < epic.getSubtaskId().size() && checkDone > 0) {
                status = "IN_PROGRESS";
            } else if (checkDone == epic.getSubtaskId().size()) {
                status = "DONE";
            }
        }
        return status;
    }

    // обновляет статус подзадачи
    public void setNewSubtaskStatus(Subtask subtask, String status) {
        Epic epic = getEpicBySubtaskId(subtask.getId());

        subtask.setStatus(status);
        allSubtasks.putIfAbsent(subtask.getId(), subtask);

        setNewEpic(epic);
    }

    //ПОЛУЧЕНИЕ ВСЕХ ЗАДАЧ СПИСКОМ

    //возвращает список всех задач Task
    public ArrayList<String> getTasks() {
        ArrayList<String> tasksToShow = new ArrayList<>();

        for(Task task : tasks.values()) {
            tasksToShow.add(task.toString());
        }
        return tasksToShow;
    }

    //возвращает список всех эпиков
    public ArrayList<String> getEpicsToString() {
        ArrayList<String> epics = new ArrayList<>();
        for(Epic epic : epicTasks.values()) {
            epics.add(epic.toString());
        }
        return epics;
    }

    //возвращает список строк со всеми подзадачами
    public ArrayList<String> getSubtasksToString() {
        ArrayList<String> subtasks = new ArrayList<>();
        for (Subtask subtask : allSubtasks.values()) {
            subtasks.add(subtask.toString());
        }
        return subtasks;
    }

    //возвращает список строк с подзадачами из определенного эпика
    public ArrayList<String> getSubtasksInEpicToString(Epic epic) {
        ArrayList<String> subtasksInEpic = new ArrayList<>();
        for (Integer id : epic.getSubtaskId()) {
            subtasksInEpic.add(allSubtasks.get(id).toString());
        }
        return subtasksInEpic;
    }

    //ПОЛУЧЕНИЕ ПО ИДЕНТИФИКАТОРУ

    // получает задачи Task по идентификатору
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    //получает эпик по идентификатору
    public Epic getEpic(int id) {
        return epicTasks.get(id);
    }

    // возвращает эпик по идентификатору подзадачи
    public Epic getEpicBySubtaskId(int subtaskId) {
        Epic huntedEpic = null;
        for (Epic epic : epicTasks.values()) {
            for (Integer id : epic.getSubtaskId()){
                if (id == subtaskId) {
                    huntedEpic = epic;
                    break;
                }
            }
        }
        return huntedEpic;
    }

    // возвращает подзадачу по идентификатору
    public Subtask getSubtaskById(int id) {
        return allSubtasks.get(id);
    }

    //УДАЛЕНИЕ ПО ИДЕНТИФИКАТОРУ

    // удаляет задачи Task по идентификатору
    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    // удаляет эпик по идентификатору
    public void removeEpic(int id) {
        Epic epic = getEpic(id);
        for (Integer subtaskId : epic.getSubtaskId()){
            allSubtasks.remove(subtaskId);
        }
        epicTasks.remove(id);
    }

    // удаляет все подзадачи в эпике по идентификатору
    public void removeAllSubtasksById(int id) {
        Epic epic = epicTasks.get(id);//getEpic(id);
        for (Integer identifier: epic.getSubtaskId()) {
            allSubtasks.remove(identifier);
        }
        epic.removeSubtasks();
        setNewEpic(epic);
    }

    // удаляет подзадачу по идентификатору обновляет статус эпика
    public void removeSubtaskById(int id) {
        Epic epic = getEpicBySubtaskId(id);

        allSubtasks.remove(id);
        epic.removeSubtask(id);
        setNewEpic(epic);
    }

    //УДАЛЕНИЕ ВСЕХ ЗАДАЧ

    // удаляет все задачи Task
    public void removeAllTasks() {
        tasks.clear();
    }

    //удаляет все эпики и их подзадачи
    public void removeAllEpics() {
        epicTasks.clear();
        allSubtasks.clear();
    }

    // удаляет все подзадачи, обновляет статусы эпиков
    public void removeAllSubtasks() {
        allSubtasks.clear();
        for (Epic epic : epicTasks.values()) {
            epic.clearSubtasks();
            setNewEpic(epic);
        }
    }
}