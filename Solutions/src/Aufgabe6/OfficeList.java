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
        if (this.head != null) {
            OfficeNode lastNode = this.getLast();
            lastNode.setNext(new OfficeNode(office));
        }
    }

    // this method prepends a OfficeNode with val = office to the head node
    public void addFirst(OfficeInt office) {
        if (this.head != null) {
            OfficeNode newNode = new OfficeNode(office);
            newNode.setNext(this.head);
            this.head = newNode;
        }
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

    // this method removes the node which was passed to the function as a parameter
    public void remove(OfficeInt toRemove) {
        if (this.head != null) {
            if (this.head.getNext() != null) {
                OfficeNode current = this.head;
                while (current.getVal() != toRemove) {
                    current = current.getNext();
                }
                OfficeNode current2 = this.head;
                while (current2.getNext().getVal() != toRemove) {
                    current2 = current2.getNext();
                }
                current2.setNext(current.getNext());
                current.setNext(null);
            } else {
                if (toRemove == this.head.getVal()) {
                    this.head = null;
                }
            }
        }
    }

    // this method return true if the given Office is in a Node in the list or flase if not
    public boolean contains(OfficeInt node) {
        OfficeNode current = this.head;
        OfficeNode last = this.getLast();
        while (current.getVal() != node) {
            if (current == last && current.getVal() != node) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    public LinkedListIterator<OfficeInt> iterator() {
        return new LinkedListIterator<>(convertToGenericNodes());
    }

    private LinkedListIterator.Node<OfficeInt> convertToGenericNodes() {
        if (head == null) return null;
        LinkedListIterator.Node<OfficeInt> genericHead = new LinkedListIterator.Node<>(head.getVal());
        LinkedListIterator.Node<OfficeInt> currentGeneric = genericHead;
        OfficeNode current = head.getNext();

        while (current != null) {
            currentGeneric.next = new LinkedListIterator.Node<>(current.getVal());
            currentGeneric = currentGeneric.next;
            current = current.getNext();
        }

        return genericHead;
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
