package Aufgabe6;

public class MyList <X> {

    public static class Node<X>{
        X data;
        Node<X> next;

        Node(X data){
            this.data = data;
            next = null;
        }


    }

    Node<X> head;

    MyList(X data){
        head = new Node<>(data);
    }

    X getData(int index){
        return head.data;
    }

}
