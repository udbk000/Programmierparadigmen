package Aufgabe6;

public class OfficeList {
    private OfficeNode head;

    public OfficeList(OfficeInt head) {
        this.head = new OfficeNode(head);
    }

    // this method returns the last node of the list, i.e. the node furthest away from the head node
    public OfficeNode getLast() {
        OfficeNode temp = this.head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp;
    }

    // this method returns the head node of the list
    public OfficeNode getFirst() {
        return this.head;
    }

    // this method appends a OfficeNode with val = office to the head node
    public void addLast(OfficeInt office) {
        OfficeNode lastNode = this.getLast();
        lastNode.setNext(new OfficeNode(office));
    }

    // this method prepends a OfficeNode with val = office to the head node
    public void addFirst(OfficeInt office) {
        OfficeNode newNode = new OfficeNode(office);
        newNode.setNext(this.head);
        this.head = newNode;
    }

    // this method removes the head node and returns its office object
    public OfficeInt pollFirst() {
        OfficeNode temp = this.head;
        this.head = this.head.getNext();
        return temp.getVal();
    }

    // this method removes the last node of the list and returs its office object
    public OfficeInt pollLast() {
        OfficeNode last = this.getLast();
        OfficeNode temp = this.head;
        while (temp.getNext() != last) {
            temp = temp.getNext();
        }
        temp.setNext(null);
        return last.getVal();
    }

    // this method can be used to output the list for testing purposes
    // incomplete
    @Override
    public String toString() {
        OfficeNode current = this.head;
        String s = "";
        while (current.getNext() != null) {
            s += "val:..., next:...";
            current = current.getNext();
        }
        return s;
    }
}
