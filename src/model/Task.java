package model;

public class Task {
    private int id;
    private String name;
    private String additional;
    private Status status;

    public Task(String name, String additional) {
        this.name = name;
        this.additional = additional;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "model.Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", additional='" + additional + '\'' +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
