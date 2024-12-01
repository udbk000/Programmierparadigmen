package Aufgabe5;

public interface ApprovableSet<X extends Approvable<P, T>, P, T> extends Iterable<X> {
    /**
     * Fügt ein neues Element vom Typ X hinzu, falls es noch nicht im Container enthalten ist. Ist es bereits enthalten, macht die Methode nichts.
     * @param x das Element, das hinzugefügt werden soll
     */
    void add(X x);

    /**
     * Fügt ein neues Kriterium vom Typ P hinzu, falls es noch nicht im Container enthalten ist.
     * @param p das Kriterium, das hinzugefügt werden soll
     */
    void addCriterion(P p);

    /**
     * Gibt einen eigenen Iterator über alle Einträge vom Typ X zurück, die mittels add() hinzugefügt wurden.
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
     * Gibt einen Iterator zurück, der pber alle Einträge x im Container läuft, die mittels add eingefügt wurden und für die
     * x.approved(p) für jeden mittels addCriterion eingefügten Eintrag p ein Ergebnis ungleich null liefert.
     * @return ein MyIterator laut Beschreibung
     */
    MyIterator<X> iterator();


    /**
     * Gibt einen eigenen Iterator über alle Einträge zurück, die mittels addCriterion() hinzugefügt wurden.
     * @return ein MyIterator über die Kriterien
     */
    MyIterator<P> criterions();
}
