package Aufgabe6;

public class RoomList implements IterableList<Room> {
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
        if (this.head != null) {
            RoomNode lastNode = this.getLast();
            lastNode.setNext(new RoomNode(room));
        }
    }

    // this method prepends a RoomNode with val = room to the head node
    public void addFirst(Room room) {
        if (this.head != null) {
            RoomNode newNode = new RoomNode(room);
            newNode.setNext(this.head);
            this.head = newNode;
        }
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

    float getArea(){
        if(head == null){
            return 0;
        }
        float area = 0;
        RoomNode temp = this.head;
        while(temp.getNext() != null){
            area += temp.getVal().area();
            temp = temp.getNext();
        }
        return area;
    }

    int getCount(){
        if(head == null){
            return 0;
        }
        int count = 0;
        RoomNode temp = this.head;
        while(temp.getNext() != null){
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    int getWorkplaceSum(){
        if(head == null)
            return 0;
        int sum = 0;
        RoomNode temp = this.head;
        while(temp.getNext() != null){
            sum += temp.getVal().getWorkplace();
            temp = temp.getNext();
        }
        return sum;
    }

    float getStorageSum(){
        if(head == null)
            return 0;
        float sum = 0;
        RoomNode temp = this.head;
        while(temp.getNext() != null){
            sum += temp.getVal().getStorage();
            temp = temp.getNext();
        }
        return sum;
    }

    float getWindowToArea(){
        if(head == null || !(head.getVal() instanceof WindowRoom))
            return 0;
        float sum = 0;
        RoomNode temp = this.head;
        while(temp.getNext() != null){
            sum += temp.getVal().getWindowArea() / temp.getVal().area();
            temp = temp.getNext();
        }
        return sum/getCount();
    }

    float getLightToArea(){
        if(head == null || !(head.getVal() instanceof LightRoom))
            return 0;
        float sum = 0;
        RoomNode temp = this.head;
        while(temp.getNext() != null){
            sum += temp.getVal().getLumen() / temp.getVal().area();
            temp = temp.getNext();
        }
        return sum/getCount();
    }

    // this method removes the node which was passed to the function as a parameter
    public void remove(Room toRemove) {
        if (this.head != null) {
            if (this.head.getNext() != null) {
                RoomNode current = this.head;
                while (current.getVal() != toRemove) {
                    current = current.getNext();
                }
                RoomNode current2 = this.head;
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

    // this method return true if the given Room is in a Node in the list or flase if not
    public boolean contains(Room node) {
        RoomNode current = this.head;
        RoomNode last = this.getLast();
        while (current.getVal() != node) {
            if (current == last && current.getVal() != node) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    public LinkedListIterator<Room> iterator() {
        return new LinkedListIterator<>(convertToGenericNodes());
    }

    private LinkedListIterator.Node<Room> convertToGenericNodes() {
        if (head == null) return null;
        LinkedListIterator.Node<Room> genericHead = new LinkedListIterator.Node<>(head.getVal());
        LinkedListIterator.Node<Room> currentGeneric = genericHead;
        RoomNode current = head.getNext();

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
        RoomNode current = this.head;
        String s = "";
        while (current.getNext() != null) {
            s += "val:..., next:...";
            current = current.getNext();
        }
        return s;
    }
}
