package Aufgabe4;

/**
 * Wer hat was gemacht?
 * Usaid Al-Dabak, 12216291: Modell der Untertypbeziehungen erarbeitet und durchdacht, Implementierung des Typs Entity und alle Untertypen von Entity
 * Andrii Makarenko 12229205: Test Klasse
 * */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

class Test {

    public static void main(String[] args) {
        // Test Building
        System.out.println("Testing Building:");
        Building building = new Building("Building A");
        Space space1 = new SimpleSpace();
        Space space2 = new SimpleSpace();
        building.addSpace(space1);
        building.addSpace(space2);
        System.out.println("Building spaces count (expected 2): " + building.getSpaces().size());
        building.removeSpace(space1);
        System.out.println("Building spaces count after removal (expected 1): " + building.getSpaces().size());

        // Test Complex
        System.out.println("\nTesting Complex:");
        Complex complex = new Complex("Complex A");
        Building building1 = new Building("Building 1");
        Building building2 = new Building("Building 2");
        complex.addEntity(building1);
        complex.addEntity(building2);
        System.out.println("Complex building count (expected 2): " + complex.getBuildings().size());
        Space exteriorSpace = new SimpleSpace();
        complex.getSpaces().add(exteriorSpace);
        System.out.println("Complex spaces count (expected 1): " + complex.getSpaces().size());

        // Test Ensemble
        System.out.println("\nTesting Ensemble:");
        Space enclosedSpace = new SimpleSpace();
        Ensemble ensemble = new Ensemble("Ensemble A", enclosedSpace);
        ensemble.addEntity(building);
        System.out.println("Ensemble entities count (expected 1): " + ensemble.getEntities().size());
        System.out.println("Ensemble spaces count (expected 1): " + ensemble.getSpaces().size());

        // Test Lift
        System.out.println("\nTesting Lift:");
        Lift lift = new Lift();
        System.out.println("Lift escape (expected null): " + lift.escape());
        System.out.println("Lift entity (expected null): " + lift.entity());

        // Test PublicRoad
        System.out.println("\nTesting PublicRoad:");
        PublicRoad publicRoad = new PublicRoad();
        System.out.println("PublicRoad escape (expected null): " + publicRoad.escape());
        System.out.println("PublicRoad entity (expected null): " + publicRoad.entity());
    }

    // A minimal Space implementation to test the functionality
    static class SimpleSpace implements Space {
        @Override
        public Escape escape() {
            return null;
        }

        @Override
        public Entity entity() {
            return null;
        }

        @Override
        public void remove() {
            // No-op for testing
        }
    }
}
