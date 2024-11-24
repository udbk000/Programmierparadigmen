package Aufgabe4;

import java.util.List;

/**
 * Dieses Interface beschreibt eine gebaute Einheit.
 */
public interface Entity {

    /**
     * Fügt eine gebaute Einheit zu dieser Entity hinzu.
     *
     * @param entity Die hinzuzufügende gebaute Einheit.
     */
    public void addEntity(Entity entity);

    /**
     * Entfernt eine gebaute Einheit aus dieser Entity.
     *
     * @param entity Die zu entfernende gebaute Einheit.
     */
    void removeEntity(Entity entity);

    /**
     * Gibt die Sammlung von Spaces (Räume oder Außenbereiche) für diese Entity zurück.
     *
     * @return Eine Liste von Spaces.
     */
    public List<Space> getSpaces();
}

