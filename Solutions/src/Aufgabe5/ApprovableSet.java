package Aufgabe5;

public interface ApprovableSet<X extends Approvable<P, T>, P, T> {
    /**
     * Fügt ein neues Element vom Typ X hinzu, falls es noch nicht im Container enthalten ist.
     * @param x das Element, das hinzugefügt werden soll
     */
    void add(X x);

    /**
     * Fügt ein neues Kriterium vom Typ P hinzu, falls es noch nicht im Container enthalten ist.
     * @param p das Kriterium, das hinzugefügt werden soll
     */
    void addCriterion(P p);

    /**
     * Gibt einen eigenen Iterator über alle Einträge vom Typ X zurück.
     * @return ein MyIterator über alle Einträge
     */
    MyIterator<X> iteratorAll();

    /**
     * Gibt einen eigenen Iterator über alle Einträge vom Typ X zurück, die `x.approved(p)` ungleich `null` liefern.
     * @param p das Kriterium, nach dem gefiltert wird
     * @return ein MyIterator über die gefilterten Einträge
     */
    MyIterator<X> iterator(P p);

    /**
     * Gibt einen eigenen Iterator über alle Einträge vom Typ X zurück, die `x.approved(p)` gleich `null` liefern.
     * @param p das Kriterium, nach dem gefiltert wird
     * @return ein MyIterator über die gefilterten Einträge
     */
    MyIterator<X> iteratorNot(P p);

    /**
     * Gibt einen eigenen Iterator über alle Einträge vom Typ X zurück, die für jedes Kriterium p ein Ergebnis ungleich `null` liefern.
     * @return ein MyIterator über die gefilterten Einträge
     */
    MyIterator<X> iterator();

    /**
     * Gibt einen eigenen Iterator über alle Kriterien vom Typ P zurück.
     * @return ein MyIterator über die Kriterien
     */
    MyIterator<P> criterions();
}

