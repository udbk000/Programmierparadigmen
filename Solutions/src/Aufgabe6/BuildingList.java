package Aufgabe6;

public class BuildingList {
    private BuildingNode head;

    public BuildingList(Building head) {
        this.head = new BuildingNode(head);
    }

    // this method returns the last node of the list, i.e. the node furthest away from the head node
    public BuildingNode getLast() {
        BuildingNode temp = this.head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp;
    }

    // this method returns the head node of the list
    public BuildingNode getFirst() {
        return this.head;
    }

    // this method appends a BuildingNode with val = building to the head node
    public void addLast(Building building) {
        BuildingNode lastNode = this.getLast();
        lastNode.setNext(new BuildingNode(building));
    }

    // this method prepends a BuildingNode with val = building to the head node
    public void addFirst(Building building) {
        BuildingNode newNode = new BuildingNode(building);
        newNode.setNext(this.head);
        this.head = newNode;
    }

    // this method removes the head node and returns its building object
    public Building pollFirst() {
        BuildingNode temp = this.head;
        this.head = this.head.getNext();
        return temp.getVal();
    }

    // this method removes the last node of the list and returs its building object
    public Building pollLast() {
        BuildingNode last = this.getLast();
        BuildingNode temp = this.head;
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
        BuildingNode current = this.head;
        String s = "";
        while (current.getNext() != null) {
            s += "val:..., next:...";
            current = current.getNext();
        }
        return s;
    }
}
