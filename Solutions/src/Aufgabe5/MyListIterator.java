package Aufgabe5;


public class MyListIterator<T> implements MyIterator<T> {
    private Node<T> current;
    private boolean filter;
    private T criterion;

    public MyListIterator(Node<T> head) {
        this.current = head;
        this.filter = false;
    }

    public MyListIterator(Node<T> head, T criterion) {
        this.current = head;
        this.filter = true;
        this.criterion = criterion;
    }

    public MyListIterator(Node<T> head, T criterion, boolean filter) {
        this.current = head;
        this.filter = filter;
        this.criterion = criterion;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }

        T data = current.getData();
        if (filter && !data.equals(criterion)) {
            current = current.getNext();  // Skip element if it doesn't match the filter
            return next();
        }

        current = current.getNext();
        return data;
    }

    @Override
    public void remove() {
        // Entfernen eines Elements ist in dieser Version nicht unterstützt
        throw new UnsupportedOperationException("Remove operation not supported");
    }
}


