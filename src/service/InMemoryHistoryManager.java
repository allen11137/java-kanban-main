package service;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList history = new CustomLinkedList();
    private static final int COUNT = 10;

    public void removeNode(Node<Task> node) {
        history.nodeMap.remove(node.task.getId(), node);
    }

    @Override
    public void add(Task task) {
        history.linkLast(task);
    }

    @Override
    public void remove(int id) {
        history.remove(id);
//        for (Node<Task> n : history.nodeMap.values()) {
//            if (n.task.getId() == id) {
//                history.nodeMap.values().remove(n);
//                return;
//            }
//        }
    }

    @Override
    public List<Task> getHistory() {
        return history.getTasks();
    }

    public static InMemoryHistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static class CustomLinkedList {
        private final Map<Integer, Node<Task>> nodeMap = new HashMap<>();
        private Node<Task> first;
        private Node<Task> last;

        public List<Task> getTasks() {
            List<Task> tasks = new ArrayList<>();
            Node<Task> node = first;
            while (node != null) {
                tasks.add(node.task);
                node = node.next;
            }
            return tasks;
        }

        public void linkLast(Task task) {
            if (nodeMap.containsKey(task.getId())) {
                nodeMap.get(task.getId()).task = task;
            } else {
                final Node<Task> node = new Node<>(task, last, null);
                if (first == null) {
                    first = node;
                } else {
                    last.next = node;
                }
                last = node;
                nodeMap.put(task.getId(), node);
            }
        }

        public void remove(int id) {
            if (nodeMap.containsKey(id)) {
                Task task = nodeMap.get(id).task;
                if (checkInFirstAndSetNext(task)) {
                    return;
                }
                Node<Task> currentNode = first;
                if (updateNextInNew(task, currentNode)) {
                    return;
                }
                nodeMap.remove(id);
            }
        }

        private boolean checkInFirstAndSetNext(Task task) {
            if (first == null) {
                return true;
            }
            if (first.task.equals(task)) {
                first = first.next;
                return true;
            }
            return false;
        }

        private boolean updateNextInNew(Task task, Node<Task> currentNode) {
            while (currentNode.next != null) {
                if (currentNode.next.task.equals(task)) {
                    currentNode.next = currentNode.next.next;
                    return true;
                }
                currentNode = currentNode.next;
            }
            return false;
        }
    }
}
