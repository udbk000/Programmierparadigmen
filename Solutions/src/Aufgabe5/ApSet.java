package Aufgabe5;

public class ApSet<X extends Approvable<P, T>, P, T> implements ApprovableSet<X, P, T> {
    private MyList<X> elements;  // Liste für die Elemente
    private MyList<P> criterions;  // Liste für die Kriterien

    public ApSet() {
        elements = new MyList<>();
        criterions = new MyList<>();
    }

    @Override
    public void add(X x) {
        elements.add(x);
    }

    @Override
    public void addCriterion(P p) {
        criterions.add(p);
    }

    @Override
    public MyIterator<X> iteratorAll() {
        return elements.iterator();  // Iterator über alle Elemente
    }

    @Override
    public MyIterator<X> iterator(P p) {
        return new MyIterator<X>() {
            private MyIterator<X> allIterator = elements.iterator();
            private X current = null;

            @Override
            public boolean hasNext() {
                while (allIterator.hasNext()) {
                    current = allIterator.next();
                    if (current.approved(p) != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public X next() {
                return current;
            }

            @Override
            public void remove() {
                elements.remove(current);  // Entferne das aktuelle Element aus der Liste
            }
        };
    }

    @Override
    public MyIterator<X> iteratorNot(P p) {
        return new MyIterator<X>() {
            private MyIterator<X> allIterator = elements.iterator();
            private X current = null;

            @Override
            public boolean hasNext() {
                while (allIterator.hasNext()) {
                    current = allIterator.next();
                    if (current.approved(p) == null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public X next() {
                return current;
            }

            @Override
            public void remove() {
                elements.remove(current);  // Entferne das aktuelle Element aus der Liste
            }
        };
    }

    @Override
    public MyIterator<X> iterator() {
        return null;
    }

    @Override
    public MyIterator<P> iteratorCriteria() {
        return criterions.iterator();  // Iterator über die Kriterien
    }

    @Override
    public MyIterator<P> criterions() {
        return criterions.iterator();  // Gibt die Liste der Kriterien zurück
    }
}
