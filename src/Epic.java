import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subtaskId = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }


    public void setSubtask(int id) {
        subtaskId.add(id);
    }

    public ArrayList<Integer> getSubtaskId() {
        return subtaskId;
    }

    public void clearSubtasks() {
        subtaskId.clear();
    }

    public void removeSubtasks() {
        this.subtaskId.clear();
    }

    public void removeSubtask(int id) {
        for (int i = 0; i < subtaskId.size(); i++) {
            if (subtaskId.get(i) == id) subtaskId.remove(i);
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskId=" + subtaskId +
                "} " + super.toString();
    }
}
