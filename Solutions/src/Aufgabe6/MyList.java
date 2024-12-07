package Aufgabe6;

public class MyList <X> {
    Node<X> head;

    MyList(X data){
        head = new Node<>(data);
    }

    MyList(){
        head = null;
    }

    X getData(int index){
        if(index == 0){
            return head.data;
        }

        Node<X> temp = head;

        while(temp.next != null){
            index--;
            if(index == 0){
                return temp.data;
            }
            temp = temp.next;
        }
        throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
    }

    void remove(X data){
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node<X> temp = head;
        while (temp.next != null) {
            if (temp.next.data.equals(data)) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }


    boolean find(X data){
        Node<X> temp = head;
        while(temp != null){
            if(temp.data.equals(data)){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    void add(X data){
        if(head == null){
            head = new Node<X>(data);
        }
        else{
            Node<X> newNode = new Node<X>(data);
            Node<X> temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public static class Node<X>{
        X data;
        Node<X> next;

        Node(X data){
            this.data = data;
            next = null;
        }
    }
}
