package Aufgabe5;

public interface Approvable<P, T> {
    /**
     * Gibt ein genehmigtes Objekt vom Typ T zurück, das im Zusammenhang mit this und dem Kriterium p steht,
     * oder null, wenn kein solches Objekt existiert.
     *
     * @param p das Kriterium für die Genehmigung
     * @return ein genehmigtes Objekt vom Typ T oder null, wenn keine Genehmigung vorliegt
     */
    T approved(P p);

    /**
     * Genehmigt ein Objekt t für das Kriterium p.
     * Nach einem Aufruf von approve(p, t) gibt approved(p) den Wert t zurück.
     *
     * @param p das Kriterium für die Genehmigung (darf nicht null sein)
     * @param t das zu genehmigende Objekt (kann null sein)
     */
    void approve(P p, T t);
}

