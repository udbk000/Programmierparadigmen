package Aufgabe6;

public class BuildingNode {
    private BuildingInt val;
    private BuildingNode next;

    public BuildingNode(BuildingInt val) {
        this.val = val;
        this.next = null;
    }

    public void setNext(BuildingNode next) {
        this.next = next;
    }

    public BuildingNode getNext() {
        return this.next;
    }

    public BuildingInt getVal() {
        return this.val;
    }
}
