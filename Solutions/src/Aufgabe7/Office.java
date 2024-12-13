package Aufgabe7;

/**
 * Absract interface to describe all methods every type of office implements.
 */
public interface Office {
    /**
     * Büros sollen eindeutige Namen haben. Die Methode HeatPump.assignedIn() gibt dann diesen Namen als String zurück.
     * Die Methode getName() gibt den Namen zurück.
     */

    /**
     * Büros sollen speichern, ob sie eine Bodenheizung oder Rippenheizkörper haben. Die Methode getHeatingType() soll das
     * als String zurückgeben. Bitte eindeutig sein, damit die Methode assignHeatPump in Inventory funktioniert.
     */

    /**
     * Größe des Büros soll als int variable gespeichert werden!
     * 1 - kleines Büro
     * 2 - mittelgroßes Büro
     * 3 - großes Büro
     *
     * die Methode getSize() soll diesen int zurückgeben
     */

    /**
     * Integer variable pumpState soll speichern, ob dieses Büro aktuell eine Pumpe hat!
     * 0 - keine Pumpe
     * 1 - dieses Büro hat eine Pumpe
     *
     * die Methode getPumpPerformance() gibt den Performance Level der Pumpe zurück (1/2/3), wenn eine Pumpe installiert ist.
     * (ruft die methode pump.performance() auf und retourniert ebendiesen Wert.)
     * Wenn keine Pumpe installiert ist, gibt sie 0 zurück.
     *
     * die Methode receive() erhält eine pumpe, die diesem büro zugewiesen wird. Das Büro soll dann diese Pumpe speichern
     * Nach dem aufruf von receive() soll die oben genannte variable entsprechend auf 1 gesetzt werden. (setPumpState(1))
     * Methode hasPump() gibt diesen int zurück.
     */
}
