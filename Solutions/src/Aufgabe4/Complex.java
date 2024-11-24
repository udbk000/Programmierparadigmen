package Aufgabe4;

import java.util.List;
import java.util.ArrayList;


public class Complex implements Entity {
    private String name;
    private List<Entity> entities;  // Gebäude oder andere Komplexe
    private List<Space> spaces;    // Außenbereiche, die zum Komplex gehören

    public Complex(String name) {
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

    // Gibt alle Gebäude im Komplex zurück
    public List<Building> getBuildings() {
        List<Building> buildingList = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Building) {
                buildingList.add((Building) entity);
            }
        }
        return buildingList;
    }

    // Gibt die Außenbereiche zurück, die nicht in den Gebäuden enthalten sind
    public List<Space> spacesNotInBuildings() {
        return spaces;
    }

    // Beispielmethode zum Anzeigen der Komplexdetails
    public void displayComplexInfo() {
        System.out.println("Komplex: " + name);
        System.out.println("Enthaltene Gebäude: " + getBuildings().size());
        System.out.println("Außenbereiche: " + spaces.size());
    }
}


