package service;

import model.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();
    void removeAll();
    Task getById(int id);
    void createTask(Task task);
    void updateTask(Task task);
    void removeById(int id);
    void getAllSubtaskByEpicId(int id);
    List<Task> getHistory();
}
