package Aufgabe6;

import java.io.Serializable;

public class UsableRoom implements Room {
    @Override
    public void call(){
        System.out.println("Usable room was called");
    }
}
