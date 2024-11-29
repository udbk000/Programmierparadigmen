package Aufgabe5;
//TODO: Klasse fertigstellen
public class ApSet<X extends Approvable<P, T>, P, T> implements ApprovableSet<X, P, T> {
    private MyList<X> list; // Deine verkettete Liste als Membervariable

    public ApSet() {
        this.list = new MyList<>();
    }

    @Override
    public void add(X entry) {
        if (!list.contains(entry)) {
            list.add(entry); // Füge das Element zur Liste hinzu

        }

    }

    @Override
    public boolean addCriterion(P criterion) {
        if (!containsCriterion(criterion)) {
            // Criterion-Logik kann je nach Bedarf angepasst werden
            // Wenn deine Liste dies unterstützt, kann das Kriterium als Objekt gespeichert werden.
            // Zum Beispiel könntest du eine Liste für Kriterien separat verwalten.
            list.add(entry); // Platzhalter, angepasst an deine Logik für Criterion
            return true;
        }
        return false;
    }

    @Override
    public Iterator<X> iteratorAll() {
        return list.iterator(); // Benutze den Iterator deiner Liste
    }

    @Override
    public Iterator<X> iterator(P criterion) {
        return list.iterator(criterion); // Benutze den Iterator der Liste mit Kriterium
    }

    @Override
    public Iterator<X> iteratorNot(P criterion) {
        return list.iteratorNot(criterion); // Benutze den Iterator der Liste mit Kriterium
    }

    @Override
    public MyIterator<X> iterator() {
        return null;
    }

    @Override
    public Iterator<P> criterions() {
        // Du kannst einen speziellen Iterator für Kriterien einführen
        // Wenn du die Kriterien in einer separaten Liste verwalten möchtest
        return list.iterator(); // Beispiel, je nach Logik anpassen
    }

    @Override
    public boolean contains(X entry) {
        return list.contains(entry);
    }

    @Override
    public boolean containsCriterion(P criterion) {
        // Kriterium suchen (kann von deiner Liste abhängen)
        return false; // Placeholder
    }
}
