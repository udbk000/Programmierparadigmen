package Aufgabe5;

import java.util.Iterator;

/**
 * Dieses Interface beschreibt einen generischen Iterator, welcher sequentiellen Zugriff auf Elemente einer Collection erlaubt.
 * Die herkömmlichen Methoden zum Traversieren und elementweise Entfernen werden geboten, zusätzlich die ÜBerprüfung, ob
 * ein nächstes Element existiert.
 *
 * @param <T> der Typ der Elemente, die vom Iterator zurückgegeben werden.
 */
public interface MyIterator<T> extends Iterator<T> {

    /**
     * Methode überprüft, ob ein nächstes Element in der Menge vorhanden ist.
     *
     * @return true, wenn ein nächstes Element existiert, false sonst.
     */
    boolean hasNext();

    /**
     * Methode gibt das nächste Element der Iteration zurück. Diese Methode stellt den Fortschritt des Iterators dar. Das
     * Element der aktuellen Position wird retourniert.
     *
     * @return das Element auf der aktuellen Position der Iterationen des Iterators.
     */
    T next();

    /**
     * Methode entfernt den zuletzt von next() retournierten Eintrag.
     */
    void remove();
}
