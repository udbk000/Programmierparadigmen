package Aufgabe4;

import java.util.List;
import java.util.ArrayList;

public class Building implements Entity {
    private String name;
    private List<Entity> entities;  // Andere Entitäten (z. B. Gebäude in einem Komplex)
    private List<Space> spaces;    // Räume und Außenbereiche, die zum Gebäude gehören

    public Building(String name) {
        this.name = name;
        this.entities = new ArrayList<>();
        this.spaces = new ArrayList<>();
    }

    @Override
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    @Override
    public List<Space> getSpaces() {
        return spaces;
    }

    // Gibt alle untergeordneten Gebäude zurück
    public List<Building> getBuildings() {
        List<Building> buildingList = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Building) {
                buildingList.add((Building) entity);
            }
        }
        return buildingList;
    }

    // Fügt einen Raum oder Außenbereich zum Gebäude hinzu
    public void addSpace(Space space) {
        spaces.add(space);
    }

    // Entfernt einen Raum oder Außenbereich aus dem Gebäude
    public void removeSpace(Space space) {
        spaces.remove(space);
    }

    // Beispielmethode zum Anzeigen der Gebäudedetails
    public void displayBuildingInfo() {
        System.out.println("Gebäude: " + name);
        System.out.println("Enthaltene Entitäten: " + entities.size());
        System.out.println("Räume: " + spaces.size());
    }
}
