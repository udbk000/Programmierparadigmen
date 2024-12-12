package Aufgabe6;

/**
 * Wer hat was gemacht?
 * Usaid Al-Dabak, 12216291: Room, SideRoom, UsableRoom, WindowRoom, LightRoom, OfficeInt, OfficeGen, BuildingInt, CompanyInt (Implementierung und Dokumentation)
 * Stefan Slanar, 12327076: RoomList, RoomNode, BuildingList, BuildingNode, OfficeList, OfficeNode, IterableList, LinkedListIterator
 * Andrii Makarenko, 12229205: MyList, Office, OfficeGen, Building, BuildingGen, Test
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Testing non-generic implementations...");

        Office office1 = new Office();
        Office office2 = new Office();
        office1.addRoom(new SideRoom(5.0f, 4.0f));
        office1.addRoom(new LightRoom(3.0f, 3.0f));
        System.out.println("Office 1 Number: " + office1.getOfficeNumber());
        System.out.println("Office 1 Entire Area: " + office1.getEntireArea());
        System.out.println("Office 1 Average Usable Room Area: " + office1.getAvgAreaUsableRooms());

        Building building1 = new Building();
        building1.addOffice(office1);
        building1.addOffice(office2);
        System.out.println("Building 1 Office Information:");
        building1.printOfficeInfo();

        Company company1 = new Company();
        company1.addBuilding(building1);
        System.out.println("Company 1 Building Information:");
        company1.printBuildingInfo();

        System.out.println("\nTesting generic implementations...");

        OfficeGen<Room> officeGen1 = new OfficeGen<>();
        OfficeGen<Room> officeGen2 = new OfficeGen<>();
        officeGen1.addRoom(new SideRoom(6.0f, 4.0f));
        officeGen1.addRoom(new LightRoom(4.0f, 4.0f));
        System.out.println("OfficeGen 1 Number: " + officeGen1.getOfficeNumber());
        System.out.println("OfficeGen 1 Entire Area: " + officeGen1.getEntireArea());
        System.out.println("OfficeGen 1 Average Usable Room Area: " + officeGen1.getAvgAreaUsableRooms());

        BuildingGen<OfficeGen<Room>> buildingGen1 = new BuildingGen<>();
        buildingGen1.addOffice(officeGen1);
        buildingGen1.addOffice(officeGen2);
        System.out.println("BuildingGen 1 Office Information:");
        buildingGen1.printOfficeInfo();

        CompanyGen<BuildingGen<OfficeGen<Room>>> companyGen1 = new CompanyGen<>();
        companyGen1.addBuilding(buildingGen1);
        System.out.println("CompanyGen 1 Building Information:");
        companyGen1.printBuildingInfo();

        System.out.println("\nTesting edge cases...");

        office1.removeRoom(new SideRoom(5.0f, 4.0f));
        building1.removeOffice(office2);
        company1.removeBuilding(building1);

        try {
            officeGen1.addRoom(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception as expected: " + e.getMessage());
        }
    }
}
