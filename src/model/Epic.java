package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private int id;
    private String name;
    private String additional;
    private Status status;

    @Override
    public String toString() {
        return "model.Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", additional='" + additional + '\'' +
                ", status=" + status +
                ", subtasks=" + subtasks +
                '}';
    }

    private List<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String additional) {
        this.name = name;
        this.additional = additional;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Subtask subtask) {
        subtasks.add(subtask);
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
