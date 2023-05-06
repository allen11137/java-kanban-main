package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private int ids = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private HistoryManager historyManager = InMemoryHistoryManager.getDefaultHistory();

    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(tasks.values());
        taskList.addAll(epics.values());
        taskList.addAll(subtasks.values());
        return taskList;
    }

    @Override
    public void removeAll() {
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    @Override
    public Task getById(int id) {
        if (tasks.get(id) != null) {
            Task task = tasks.get(id);
            updateHistory(task);
            return task;
        } else if (epics.get(id) != null) {
            Epic epic = epics.get(id);
            updateHistory(epic);
            return epic;
        } else if (subtasks.get(id) != null) {
            Subtask subtask = subtasks.get(id);
            updateHistory(subtask);
            return subtask;
        } else {
            return null;
        }
    }

    private void updateHistory(Task task) {
        historyManager.add(task);
    }

    public void createTask(Task task) {
        if (task instanceof Epic) {
            updateTaskIdAndStatus(task);
            epics.put(ids, (Epic) task);
        } else if (task instanceof Subtask) {
            updateTaskIdAndStatus(task);
            subtasks.put(ids, (Subtask) task);

            int epicId = ((Subtask) task).getEpicId();
            Epic epic = epics.get(epicId);
            epic.setStatus(Status.NEW);
            epic.setSubtasks((Subtask) task);
        } else {
            updateTaskIdAndStatus(task);
            tasks.put(ids, task);
        }
        ids++;
    }

    private void updateTaskIdAndStatus(Task task) {
        task.setId(ids);
        task.setStatus(Status.NEW);
    }

    @Override
    public void updateTask(Task task) {
        if (task instanceof Task) {
            tasks.put(task.getId(), task);
        } else if (task instanceof Epic) {
            epics.put(task.getId(), (Epic) task);
        } else if (task instanceof Subtask) {
            subtasks.put(task.getId(), (Subtask) task);
            int epicId = ((Subtask) task).getEpicId();
            Epic epic = epics.get(epicId);
            int countSubtasks = epic.getSubtasks().size();
            int statusDoneSubtasks = 0;
            for (Subtask subtask : epic.getSubtasks()) {
                if (subtask.getStatus().equals(Status.DONE)) {
                    statusDoneSubtasks++;
                }
            }
            if (statusDoneSubtasks == countSubtasks) {
                epic.setStatus(Status.DONE);
            }
        }
    }

    @Override
    public void removeById(int id) {
        if (tasks.get(id) != null) {
            tasks.remove(id);
            historyManager.remove(id);
            return;
        }
        if (epics.get(id) != null) {
            for (Subtask s : new ArrayList<>(subtasks.values())) {
                if (s.getEpicId() == id) {
                    subtasks.remove(s.getId());
                    historyManager.remove(s.getId());
                }
            }
            epics.remove(id);
            historyManager.remove(id);
            return;
        }
        if (subtasks.get(id) != null) {
            subtasks.remove(id);
            historyManager.remove(id);
        }
    }

    @Override
    public void getAllSubtaskByEpicId(int id) {
        Epic epic = epics.get(id);
        List<Subtask> subtasks = epic.getSubtasks();
        for (Subtask subtask : subtasks) {
            System.out.println(subtask);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}
