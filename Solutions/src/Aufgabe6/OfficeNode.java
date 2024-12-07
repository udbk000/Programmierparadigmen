package Aufgabe6;

public class OfficeNode {
    private OfficeInt val;
    private OfficeNode next;

    public OfficeNode(OfficeInt val) {
        this.val = val;
        this.next = null;
    }

    public void setNext(OfficeNode next) {
        this.next = next;
    }

    public OfficeNode getNext() {
        return this.next;
    }

    public OfficeInt getVal() {
        return this.val;
    }
}
