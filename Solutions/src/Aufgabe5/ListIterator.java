package Aufgabe5;

public class ListIterator<T> {
    private Node<T> current;
    private Node<T> previous;
    private MyList<T> list;
    private boolean canRemove;

    public ListIterator(Node<T> head, MyList<T> list) {
        this.current = head;
        this.previous = null;
        this.list = list;
        this.canRemove = false;
    }

    public boolean hasNext() {
        return current != null;
    }

    public T next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        T data = current.data;
        previous = current;
        current = current.next;
        canRemove = true;
        return data;
    }

    public void remove() {
        if (!canRemove) {
            throw new IllegalStateException("Next has not been called or element already removed");
        }

        // Die Methode removeNode() in der Liste wird aufgerufen, um das Element zu entfernen
        list.removeNode(previous);

        // Setze den current-Knoten auf den nächsten, damit der Iterator weiterhin funktioniert
        current = previous.next;
        canRemove = false;  // Nach der Entfernung kann nicht sofort ein weiteres Element entfernt werden
    }
}

