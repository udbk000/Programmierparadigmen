package Aufgabe6;

/**
 * This class is a generic implementation of the CompanyInt interface. All methods behave as mentioned in CompanyInt.
 * @param <X> an Instance of BuildingInt
 */
public class CompanyGen<X extends BuildingInt> implements CompanyInt{
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
