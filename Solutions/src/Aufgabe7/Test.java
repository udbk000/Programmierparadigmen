package Aufgabe7;

@Verantwortlich(name = "Stefan Slanar", mnr = 12327076)
public class Test {
    public static void main(String[] args) {
        // Klassen mit Annotation inspizieren
        printAnnotationDetails(HeatPump.class);
        printAnnotationDetails(LowTempHeatPump.class);
        completeTests();
    }

    @Verantwortlich(name = "Andrii Makarenko", mnr = 12229205)
    public static void completeTests(){
        HeatPump lowTempSmall = new LowTempHeatPump(1, 200);
        HeatPump lowTempMedium = new LowTempHeatPump(2, 500);
        HeatPump highTempLarge = new HighTempHeatPump(3, 1000);

        Office smallOffice = new SmallOffice("Small Office 1", "LowTemp");
        Office midOffice = new MidOffice("Mid Office 1", "HighTemp");
        Office largeOffice = new LargeOffice("Large Office 1", "HighTemp");

        System.out.println("=== Test 1: Assign Heat Pumps to Offices ===");
        System.out.println("Assigning heat pump to " + smallOffice.getName());
        smallOffice.receive(lowTempSmall);
        lowTempSmall.setAvailability(1);
        System.out.println("Office Heating Type: " + smallOffice.getHeatingType());
        System.out.println("Pump Assigned: " + (smallOffice.hasPump() == 1 ? "Yes" : "No"));
        System.out.println("Pump Performance Level: " + smallOffice.getPumpPerformance());

        System.out.println("Assigning heat pump to " + midOffice.getName());
        midOffice.receive(lowTempMedium);
        lowTempMedium.setAvailability(1);
        System.out.println("Office Heating Type: " + midOffice.getHeatingType());
        System.out.println("Pump Assigned: " + (midOffice.hasPump() == 1 ? "Yes" : "No"));
        System.out.println("Pump Performance Level: " + midOffice.getPumpPerformance());

        System.out.println("Assigning heat pump to " + largeOffice.getName());
        largeOffice.receive(highTempLarge);
        highTempLarge.setAvailability(1);
        System.out.println("Office Heating Type: " + largeOffice.getHeatingType());
        System.out.println("Pump Assigned: " + (largeOffice.hasPump() == 1 ? "Yes" : "No"));
        System.out.println("Pump Performance Level: " + largeOffice.getPumpPerformance());

        // Test 2: Rückgabe der Wärmepumpen ins Inventar
        System.out.println("\n=== Test 2: Return Heat Pumps ===");
        System.out.println("Returning heat pump from " + smallOffice.getName());
        smallOffice.setPumpState(0);
        lowTempSmall.setAvailability(0);
        System.out.println("Pump removed. Pump state: " + (smallOffice.hasPump() == 0 ? "No Pump" : "Pump Still Installed"));

        System.out.println("Returning heat pump from " + midOffice.getName());
        midOffice.setPumpState(0);
        lowTempMedium.setAvailability(0);
        System.out.println("Pump removed. Pump state: " + (midOffice.hasPump() == 0 ? "No Pump" : "Pump Still Installed"));

        System.out.println("Returning heat pump from " + largeOffice.getName());
        largeOffice.setPumpState(0);
        highTempLarge.setAvailability(0);
        System.out.println("Pump removed. Pump state: " + (largeOffice.hasPump() == 0 ? "No Pump" : "Pump Still Installed"));

        // Test 3: Preisberechnung
        System.out.println("\n=== Test 3: Price Calculation ===");
        Inventory inventory = new Inventory();
        inventory.addHeatPump(lowTempSmall);
        inventory.addHeatPump(lowTempMedium);
        inventory.addHeatPump(highTempLarge);
        inventory.priceAvailable();

        // Test 4: Anzeigen der Büros
        System.out.println("\n=== Test 4: Show Office Details ===");
        OfficeManager officeManager = new OfficeManager();
        officeManager.addOffice(smallOffice);
        officeManager.addOffice(midOffice);
        officeManager.addOffice(largeOffice);
        officeManager.showOffices();
    }

    public static void printAnnotationDetails(Class<?> clazz) {
        // Prüfen, ob die Klasse die Annotation @Verantwortlich besitzt
        if (clazz.isAnnotationPresent(Verantwortlich.class)) {
            Verantwortlich annotation = clazz.getAnnotation(Verantwortlich.class);
            System.out.println("Class: " + clazz.getSimpleName());
            System.out.println("  Author: " + annotation.name());
            System.out.println("  Matrikelnummer: " + annotation.mnr());
        } else {
            System.out.println("Class: " + clazz.getSimpleName() + " has no @Verantwortlich annotation.");
        }
    }


}
