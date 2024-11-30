package Aufgabe5;

public class MyList<T> {
    private Node<T> head;
    private int size;

    public MyList() {
        head = null;
        size = 0;
    }

    // Konstruktor, der neue MyList aus bestehenden Elementen erzeugt
    public MyList(MyList<T> elements) {
        MyList<T> newList = new MyList<>();
        Node<T> headOfElements = elements.head;
        ListIterator<T> it = new ListIterator<>(headOfElements, elements);
        Node<T> node = headOfElements;
        while (it.hasNext()) {
            newList.add(node);
            node = node.getNext();
        }
    }

    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
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
            head = head.next;
            size--;
            return;
        }

        // Durch die Liste iterieren, um den Knoten zu finden
        Node<T> current = head;
        while (current != null && current.next != nodeToRemove) {
            current = current.next;
        }

        if (current != null) {
            current.next = current.next.next;  // Entferne den Knoten
            size--;
        }
    }

    // Iterator zurückgeben
    public ListIterator<T> iterator() {
        return new ListIterator<>(head, this);
    }

}

