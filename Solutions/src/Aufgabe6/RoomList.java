package Aufgabe6;

public class RoomList {
    private RoomNode head;

    public RoomList(Room head) {
        this.head = new RoomNode(head);
    }

    // this method returns the last node of the list, i.e. the node furthest away from the head node
    public RoomNode getLast() {
        RoomNode temp = this.head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp;
    }

    // this method returns the head node of the list
    public RoomNode getFirst() {
        return this.head;
    }

    // this method appends a RoomNode with val = room to the head node
    public void addLast(Room room) {
        RoomNode lastNode = this.getLast();
        lastNode.setNext(new RoomNode(room));
    }

    // this method prepends a RoomNode with val = room to the head node
    public void addFirst(Room room) {
        RoomNode newNode = new RoomNode(room);
        newNode.setNext(this.head);
        this.head = newNode;
    }

    // this method removes the head node and returns its room object
    public Room pollFirst() {
        RoomNode temp = this.head;
        this.head = this.head.getNext();
        return temp.getVal();
    }

    // this method removes the last node of the list and returs its room object
    public Room pollLast() {
        RoomNode last = this.getLast();
        RoomNode temp = this.head;
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
        RoomNode current = this.head;
        String s = "";
        while (current.getNext() != null) {
            s += "val:..., next:...";
            current = current.getNext();
        }
        return s;
    }
}
