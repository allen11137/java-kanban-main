package service;

class Node<Task> {
    Task task;
    Node<Task> next;
    Node<Task> before;

    public Node(Task task, Node<Task> before, Node<Task> next) {
        this.task = task;
        this.next = next;
        this.before = before;
    }
}

