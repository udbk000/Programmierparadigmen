package Aufgabe6;

/**
 * This class is a non-generic implementation of the interface BuildingInt. All methods behave as described in BuildingInt.
 */
public class Building implements BuildingInt{
    /**
     * A list of all offices inside this building.
     */
    OfficeList officeList = new OfficeList(null);
    @Override
    public void addOffice(OfficeInt toAdd) {
        officeList.addLast(toAdd);
    }


    @Override
    public void removeOffice(OfficeInt toRemove) {
        officeList.remove(toRemove);
    }

    @Override
    public void printOfficeInfo() {
        System.out.println(officeList.toString());
    }
}
