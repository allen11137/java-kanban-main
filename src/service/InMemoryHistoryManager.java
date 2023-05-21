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
        if (history.nodeMap.containsKey(task.getId())) {
            remove(task.getId());
        }
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
            final Node<Task> node = new Node<>(task, last, null);
            if (first == null) {
                first = node;
            } else {
                last.next = node;
            }
            last = node;
            nodeMap.put(task.getId(), node);
        }

        public boolean remove(int id) {
            Task task = nodeMap.get(id).task;
            if (task == null) {
                for (Node<Task> x = first; x != null; x = x.next) {
                    if (x.task == null) {
                        unlink(x);
                        return true;
                    }
                }
            } else {
                for (Node<Task> x = first; x != null; x = x.next) {
                    if (task.equals(x.task)) {
                        unlink(x);
                        return true;
                    }
                }
            }
            return false;
        }

        void unlink(Node<Task> x) {
            final Node<Task> next = x.next;
            final Node<Task> prev = x.before;

            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                x.before = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.before = prev;
                x.next = null;
            }

            x.task = null;
        }
    }
}
