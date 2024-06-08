public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("task1", "taskDescription1");
        taskManager.setTask(task1);

        Task task2 = new Task("task2", "taskDescription2");
        taskManager.setTask(task2);

        Epic epic1 = new Epic("epic1", "epic1Description1");
        Epic epic2 = new Epic("epic2", "epic1Description2");

        Subtask subtask1 = new Subtask("subtask1", "subtaskDescription1");
        Subtask subtask2 = new Subtask("subtask2", "subtaskDescription2");
        Subtask subtask3 = new Subtask("subtask3", "subtaskDescription3");
        Subtask subtask4 = new Subtask("subtask4", "subtaskDescription4");
        Subtask subtask5 = new Subtask("subtask5", "subtaskDescription5");

        taskManager.setSubtask(epic1, subtask1);
        taskManager.setSubtask(epic1, subtask2);
        taskManager.setSubtask(epic2, subtask3);
        taskManager.setSubtask(epic2, subtask4);
        taskManager.setSubtask(epic2, subtask5);

        taskManager.setEpic(epic1);
        taskManager.setEpic(epic2);

        printAll();

        System.out.println("Изменяю статусы задач и подзадач\n");

        taskManager.setNewStatus(task1, "DONE");
        taskManager.setNewStatus(task2, "IN_PROGRESS");
        taskManager.setNewSubtaskStatus(subtask1, "DONE");
        taskManager.setNewSubtaskStatus(subtask2, "DONE");
        taskManager.setNewSubtaskStatus(subtask3, "DONE");
        taskManager.setNewSubtaskStatus(subtask4, "IN_PROGRESS");
        taskManager.setNewSubtaskStatus(subtask5, "DONE");

        System.out.println("Проверка обновления статусов\n");

        printAll();

        System.out.println("Удаляю все подздачи в эпике\n");

        taskManager.removeAllSubtasksById(8);
        System.out.println("Список эпиков:\n");
        System.out.println(taskManager.getEpicsToString());
        System.out.println();


        System.out.println("Удаляю одну задачу, один эпик и одну подзадачу\n");

        taskManager.removeTaskById(1);
        taskManager.removeEpic(8);
        taskManager.removeSubtaskById(5);



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
        TaskManager taskManager= new TaskManager();

        System.out.println("Список задач:\n");
        System.out.println(taskManager.getTasks());
        System.out.println();
        System.out.println("Список эпиков:\n");
        System.out.println(taskManager.getEpicsToString());
        System.out.println();
        System.out.println("Список подзадач:\n");
        System.out.println(taskManager.getSubtasksToString());
        System.out.println();
    }
}
