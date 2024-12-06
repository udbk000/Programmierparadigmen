package Aufgabe6;

public class OfficeNode {
    private Office val;
    private OfficeNode next;

    public OfficeNode(Office val) {
        this.val = val;
        this.next = null;
    }

    public void setNext(OfficeNode next) {
        this.next = next;
    }

    public OfficeNode getNext() {
        return this.next;
    }

    public Office getVal() {
        return this.val;
    }
}
