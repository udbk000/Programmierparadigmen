package Aufgabe6;

/**
 * This interface defines a building which can contain several offices from the OfficeInt interface.
 * All buildings have unique and identifying names.
 */
public interface BuildingInt {
    /**
     * This method adds an office to this building. If this office is part of the building already, nothing happens.
     * @param toAdd an instance of OfficeInt being not null.
     */
    void addOffice(Room toAdd);

    /**
     * This method removes an office which is currently part of the building. The office should be part of the building, otherwise nothing happens.
     * This method can only be called once toAdd has been called and only as often as the method toAdd has been previously called
     * (you can only remove as many offices as there are part of this building).
     * @param toRemove an instance of OfficeInt being not null
     */
    void removeOffice(OfficeInt toRemove);

    /**
     * This method prints out all the important information available about the offices that are part of this building.
     */
    void printOfficeInfo();
}
