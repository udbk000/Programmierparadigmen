package Aufgabe6;

public class RoomNode {
    private Room val;
    private RoomNode next;

    public RoomNode(Room val) {
        this.val = val;
        this.next = null;
    }

    public void setNext(RoomNode next) {
        this.next = next;
    }

    public RoomNode getNext() {
        return this.next;
    }

    public Room getVal() {
        return this.val;
    }
}
