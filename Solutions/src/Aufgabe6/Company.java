package Aufgabe6;

/**
 * This class is a non-generic implementation of the CompanyInt interface. All methods behave as described in CompanyInt.
 */
public class Company implements CompanyInt{
    BuildingList buildingList = new BuildingList(null);
    @Override
    public void addBuilding(BuildingInt toAdd) {
        buildingList.addLast(toAdd);
    }

    @Override
    public void removeBuilding(BuildingInt toRemove) {
        buildingList.remove(toRemove);

    }

    @Override
    public void printBuildingInfo() {

    }
}
