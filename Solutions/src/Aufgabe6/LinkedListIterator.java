package Aufgabe6;

public class LinkedListIterator<T> {
    private Node<T> current;

    // Generischer Knoten
    public static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public LinkedListIterator(Node<T> head) {
        this.current = head;
    }

    public boolean hasNext() {
        return current != null;
    }

    public T next() {
        if (!hasNext()) {
            return null;
        }
        T data = current.data;
        current = current.next;
        return data;
    }
}