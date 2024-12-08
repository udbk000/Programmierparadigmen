package Aufgabe6;

public class BuildingList {
    private BuildingNode head;

    public BuildingList(BuildingInt head) {
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
    public void addLast(BuildingInt building) {
        if (this.head != null) {
            BuildingNode lastNode = this.getLast();
            lastNode.setNext(new BuildingNode(building));
        }
    }

    // this method prepends a BuildingNode with val = building to the head node
    public void addFirst(BuildingInt building) {
        if (this.head != null) {
            BuildingNode newNode = new BuildingNode(building);
            newNode.setNext(this.head);
            this.head = newNode;
        }
    }

    // this method removes the head node and returns its building object
    public BuildingInt pollFirst() {
        BuildingNode temp = this.head;
        this.head = this.head.getNext();
        return temp.getVal();
    }

    // this method removes the last node of the list and returs its building object
    public BuildingInt pollLast() {
        BuildingNode last = this.getLast();
        BuildingNode temp = this.head;
        while (temp.getNext() != last) {
            temp = temp.getNext();
        }
        temp.setNext(null);
        return last.getVal();
    }

    // this method removes the node which was passed to the function as a parameter
    public void remove(Building toRemove) {
        if (this.head != null) {
            if (this.head.getNext() != null) {
                BuildingNode current = this.head;
                while (current.getVal() != toRemove) {
                    current = current.getNext();
                }
                BuildingNode current2 = this.head;
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

    // this method return true if the given Building is in a Node in the list or flase if not
    public boolean contains(Building node) {
        BuildingNode current = this.head;
        BuildingNode last = this.getLast();
        while (current.getVal() != node) {
            if (current == last && current.getVal() != node) {
                return false;
            }
            current = current.getNext();
        }
        return true;
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
