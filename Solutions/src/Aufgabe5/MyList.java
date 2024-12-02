package Aufgabe5;

public class MyList<T extends Approvable<P, T>, P> {
    private Node<T> head;
    private int size;

    public MyList() {
        head = null;
        size = 0;
    }

    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    public int size() {
        return size;
    }

    // Methode zum Entfernen eines Knotens
    public void removeNode(Node<T> nodeToRemove) {
        if (head == null) return;

        // Wenn der Knoten der Kopf der Liste ist
        if (head == nodeToRemove) {
            head = head.getNext();
            size--;
            return;
        }

        // Durch die Liste iterieren, um den Knoten zu finden
        Node<T> current = head;
        while (current != null && current.getNext() != nodeToRemove) {
            current = current.getNext();
        }

        if (current != null) {
            current.setNext(current.getNext().getNext());  // Entferne den Knoten
            size--;
        }
    }

    // Iterator zurückgeben
    public MyListIterator<T> iterator() {
        return new MyListIterator<>(head, this.head.getData());
    }

}

