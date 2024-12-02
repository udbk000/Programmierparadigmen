package Aufgabe5;

import java.util.Iterator;
import java.util.Objects;

public class Path<X> implements Admin<X, Path<X>>, Iterable<X> {

    private final MyList<X> elements;

    public Path() {
        this.elements = new MyList<>();
    }

    private Path(MyList<X> elements) {
        this.elements = new MyList<>(elements);
    }

    public Path<X> add(X x) {
        Objects.requireNonNull(x);
        if (!elements.contains(x)) {
            elements.add(x);
            return new Path<>(this.elements);
        }
        return this;
    }

    public Path<X> remove(X x) {
        return this;
    }

    @Override
    public Iterator<X> iterator() {
        return null;
    }
}
