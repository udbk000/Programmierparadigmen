package Aufgabe5;

public interface Admin<X, T> {
    /**
     * Gibt ein neues Objekt vom Typ T zurück, das this um x erweitert.
     * Wenn this nicht um x erweiterbar ist, wird this zurückgegeben.
     * @param x das Element, mit dem erweitert werden soll
     * @return ein neues Objekt vom Typ T oder this, wenn keine Erweiterung möglich ist
     */
    T add(X x);

    /**
     * Gibt ein neues Objekt vom Typ T zurück, aus dem x entfernt ist.
     * Wenn x nicht aus this entfernt werden kann, wird this zurückgegeben.
     * @param x das Element, das entfernt werden soll
     * @return ein neues Objekt vom Typ T oder this, wenn keine Entfernung möglich ist
     */
    T remove(X x);
}
