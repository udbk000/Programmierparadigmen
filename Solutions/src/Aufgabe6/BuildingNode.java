package Aufgabe6;

public class BuildingNode {
    private Building val;
    private BuildingNode next;

    public BuildingNode(Building val) {
        this.val = val;
        this.next = null;
    }

    public void setNext(BuildingNode next) {
        this.next = next;
    }

    public BuildingNode getNext() {
        return this.next;
    }

    public Building getVal() {
        return this.val;
    }
}
