package Aufgabe5;


import java.util.Iterator;

/*public abstract class AdminSet<X extends Approvable<P, T>, P, T extends Admin<X, T>> implements ApprovableSet<X, P, T> {

    private MyList<X, P, T> list;

    public AdminSet() {
        list = new MyList<>();
    }

    @Override
    public void add(X entry) {
        // Überprüfen, ob das Element bereits vorhanden ist
        if (!contains(entry)) {
            list.add(entry);
        }
    }

    @Override
    public void addCriterion(P criterion) {
        // Kriterium hinzufügen (hier muss Ihre Logik entsprechend angepasst werden)
        // Wir fügen das Kriterium zur Liste hinzu, wie es für `MyList` erforderlich ist
        list.add((X) criterion);
    }

    @Override
    public MyIterator<X> iteratorAll() {
        // Rückgabe eines Iterators für alle Einträge
        return list.iterator();
    }

    @Override
    public MyIterator<X> iterator(P p) {
        // Iterator für Einträge, die das gegebene Kriterium erfüllen
        return list.iterator(); // Ihr Iterator muss dies unterstützen
    }

    @Override
    public MyIterator<X> iteratorNot(P criterion) {
        // Iterator für Einträge, die das gegebene Kriterium nicht erfüllen
        return list.iteratorNot(criterion); // Ihr Iterator muss dies unterstützen
    }

    @Override
    public MyIterator<P> iterator() {
        // Kriteriums-Iterator, der alle Kriterien zurückgibt
        return new MyIterator<P>() {
            private Iterator<P> criterionIterator = /* Ihr Iterator über die Kriterien;

            @Override
            public boolean hasNext() {
                return criterionIterator.hasNext();
            }

            @Override
            public P next() {
                return criterionIterator.next();
            }

            @Override
            public void remove() {
                criterionIterator.remove();
            }
        };
    }

    @Override
    public MyIterator<P> criterions() {
        // Iterator für Kriterien, der über alle Kriterien iteriert
        return iterator(); // Beispiel, hier könnten Sie auch eine eigene Liste von Kriterien haben
    }

    @Override
    public boolean contains(X entry) {
        return list.contains(entry);
    }

    @Override
    public boolean containsCriterion(P criterion) {
        // Überprüfen, ob das Kriterium in einer speziellen Kriteriumsliste enthalten ist
        // Beispiel (muss an Ihre Logik angepasst werden)
        return false; // Beispiel (muss entsprechend der Kriterienlogik geändert werden)
    }

    @Override
    public void extend() {
        // Iteriere über alle Kriterien
        MyIterator<P> criterionIterator = criterions();
        while (criterionIterator.hasNext()) {
            P p = criterionIterator.next();

            // Iteriere über alle Einträge, die das Kriterium erfüllen
            MyIterator<X> entryIterator = iterator(p);
            while (entryIterator.hasNext()) {
                X x = entryIterator.next();

                // Führt die Extend-Operation aus
                T approved = x.approved(p);
                if (approved != null) {
                    x.approve(p, approved.add(x));
                }
            }
        }
    }

    @Override
    public void shorten() {
        // Iteriere über alle Kriterien
        MyIterator<P> criterionIterator = criterions();
        while (criterionIterator.hasNext()) {
            P p = criterionIterator.next();

            // Iteriere über alle Einträge, die das Kriterium erfüllen
            MyIterator<X> entryIterator = iterator(p);
            while (entryIterator.hasNext()) {
                X x = entryIterator.next();

                // Führt die Shorten-Operation aus
                T approved = x.approved(p);
                if (approved != null) {
                    x.approve(p, approved.remove(x));
                }
            }
        }
    }

}*/

