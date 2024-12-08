package Aufgabe6;

/**
 * This class is a generic implementation of the interface BuildingInt. All methods behave as mentioned in BuildingInt.
 * @param <X> subtypes of OfficeInt
 */
public class BuildingGen<X extends OfficeInt> implements BuildingInt {

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

    }
}
