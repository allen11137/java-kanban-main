package model;

public class Subtask extends Task {
    private int id;
    private int epicId;
    private String name;
    private String additional;
    private Status status;

    @Override
    public String toString() {
        return "model.Subtask{" +
                "id=" + id +
                ", epicId=" + epicId +
                ", name='" + name + '\'' +
                ", additional='" + additional + '\'' +
                ", status=" + status +
                '}';
    }

    public Subtask(int epicId, String name, String additional) {
        this.epicId = epicId;
        this.name = name;
        this.additional = additional;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
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
