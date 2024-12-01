package Aufgabe5;

import java.util.NoSuchElementException;

public class MyList<X extends Approvable<?,?>> implements Iterable {
    private Node<X> head;
    private int size;

    public MyList() {
        head = null;
        size = 0;
    }


    public void add(X item) {
        Node<X> toAdd = new Node(item);
        head.add(toAdd);
        head= toAdd;
        size++;
    }

    public int size() {
        return size;
    }

    public void remove(Node toRemove){
        toRemove.remove();
    }

    // Iterator zurückgeben
    @Override
    public MyIterator<X> iterator() {
        return new MyIterator<X>() {
            private Node<X> current = head;
            private boolean removable = false;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public X next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                X data = current.getData();
                current = current.getNext();
                removable = true;

                return data;
            }

            public boolean isRemovable(){
                return removable;
            }
            @Override
            public void remove() {
                current.remove();
                size--;
            }
        };
    }

}



