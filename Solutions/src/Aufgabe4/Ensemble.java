package Aufgabe4;

import java.util.List;
import java.util.ArrayList;

public class Ensemble implements Entity {
    private String name;
    private List<Entity> entities;  // Gebäude oder Gebäudekomplexe, die zum Ensemble gehören
    private Space space;            // Der Bereich, der durch das Ensemble umschlossen wird

    public Ensemble(String name, Space space) {
        this.name = name;
        this.entities = new ArrayList<>();
        this.space = space;
    }

    /**
     * Fügt ein Gebäude oder einen Gebäudekomplex zum Ensemble hinzu.
     *
     * @param entity Die zugehörige Entity (Gebäude oder Gebäudekomplex).
     */
    @Override
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Entfernt ein Gebäude oder einen Gebäudekomplex aus dem Ensemble.
     *
     * @param entity Die zu entfernende Entity.
     */
    @Override
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    @Override
    public List<Space> getSpaces() {
        List<Space> space = new ArrayList<>();
        space.add(this.space);
        return space;
    }

    /**
     * Gibt die Menge der Gebäude (oder Gebäudekomplexe) zurück, die zum Ensemble gehören.
     *
     * @return Eine Liste der Entity-Objekte, die zum Ensemble gehören.
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Gibt den Bereich zurück, der durch das Ensemble umschlossen wird.
     * Gibt null zurück, wenn dieser Bereich nicht existiert.
     *
     * @return Der Bereich des Ensembles oder null, wenn nicht definiert.
     */
    public Space getSpace() {
        return space;
    }

    /**
     * Beispielmethode zum Anzeigen der Ensembleinformationen.
     */
    public void displayEnsembleInfo() {
        System.out.println("Ensemble: " + name);
        System.out.println("Anzahl der enthaltenen Gebäude/Komplexe: " + entities.size());

    }
}
