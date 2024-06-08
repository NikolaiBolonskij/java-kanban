import java.util.Objects;

public class Task {
    private final String name;
    private final String description;
    private int id;
    private Status status;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        switch (status) {
            case "NEW":
            case null:
                this.status = Status.NEW;
                break;
            case "IN_PROGRESS":
                this.status = Status.IN_PROGRESS;
                break;
            case "DONE":
                this.status = Status.DONE;
                break;
            default:
                break;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\n' +
                ", description='" + description + "\n'" +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
