package Aufgabe5;

public class Node<X extends Approvable<?, ?>> {
    private X data;
    private Node<X> next;
    private Node<X> previous;

    public Node(X data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public void add(Node toAdd){
        next = toAdd;
        toAdd.previous = this;
    }

    public void remove(){
        previous.next = this.next;
        next.previous = this.previous;
    }

    public X getData() {
        return data;
    }

    public void setNext(Node<X> next) {
        this.next = next;
    }

    public Node<X> getNext() {
        return next;
    }
}

