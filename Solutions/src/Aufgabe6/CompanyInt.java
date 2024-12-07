package Aufgabe6;

/**
 * This interface describes a company. A company can possess several buildings from the BuildingInt interface.
 * Each company has a unique and identifying name.
 */
public interface CompanyInt {
    /**
     * This method adds a building to this company's possessions. If this building is already part of the company, nothing happens.
     * @param toAdd a Building being not null
     */
    void addBuilding(BuildingInt toAdd);

    /**
     * This method removes a building which is currently part of the company. The building should be part of the company, otherwise nothing happens.
     * This method can only be called once toAdd has been called and only as often as the method toAdd has been previously called
     * (you can only remove as many buildings as there are part of this company).
     * @param toRemove a Building being not null
     */
    void removeBuilding(BuildingInt toRemove);

    /**
     * This method prints out all important information available about the buildings that are part of this company.
     */
    void printBuildingInfo();
}
