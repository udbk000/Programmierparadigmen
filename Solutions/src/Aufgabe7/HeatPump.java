package Aufgabe7;

/**
 * Abstract class to describe all methods that every type of heatpump implements.
 */
public interface HeatPump {
    /**
     * Performance soll als int variable gespeichert werden!
     * 1 - low performance pump
     * 2 - medium performance pump
     * 3 - high performance pump
     *
     * die Methode performance() soll diesen int zurückgeben
     */

    /**
     * Eine integer variable availability soll die Verfügbarkeit der Pumpe speichern!
     * 0 = Pumpe ist keinem Büro zugewiesen
     * 1 = Pumpe ist bereits einem Büro zugewiesen
     *
     * die Methode state() soll diesen Int zurückgeben
     */

    /**
     * Die Methode type() soll nur speicern, ob es eine Niedertemperaturpumpe oder eine Hochtemperaturpumpe ist und das
     * als String zurückgeben. Bitte eindeutig sein (=selber String, der in Office.getHeatingType() zurückgegeben wird),
     * damit die Methode assignHeatPump funktioniert.
     */
}
