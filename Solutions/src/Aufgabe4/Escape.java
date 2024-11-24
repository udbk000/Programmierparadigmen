package Aufgabe4;

import java.util.ArrayList;
import java.util.List;

public class Escape implements Circulation {
    List<Circulation> circ = new ArrayList<>();

    @Override
    public Escape escape() {
        return this;
    }

    @Override
    public Entity entity() {
        return null;
    }

    @Override
    public void remove() {

    }
}
