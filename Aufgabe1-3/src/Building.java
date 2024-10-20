public interface Building {
    /** this interface contains methods shared between all types of buildings
     *
     */

    /**Method "ageOneYear" changes values of every aspect relevant to representation of the building, simulating the passage of a
     * year. The relevant variable aspects of the building change, the building ages.
     */
    void ageOneYear();

    /**Method "deconstruct" is called in order to deconstruct the building. The reason behind the deconstruction is
     * not further specified, the building ceases to exist when method is called
     * @return: returns true if building was deconstructed.
     */
    boolean deconstruct();

    /**Method "renovate" improves values of the building according to the renovation. Inhabitants' morale improves,
     * buildings' lifespan is reset to an earlier point, condition ameliorates, expenses rise.
     */
    void renovate();

    /**Method "susIndex" calculates the sustainability index of the building and returns it. All aspects are used to calculate the index.
     * @return Index between 1-100; 1... min sustainability, 100... max sustainability
     */
    //diese Methode evtl. im Interface vordefinieren, damit Index für alle Gebäude gleichartig berechnet wird?
    int susIndex();

    /**
     * This method simulates a fire inside the building. The building is damaged accordingly.
     */
    void fire();

    /**
     * This method simulates an earthquake hitting the building. The building is damaged accordingly.
     */
    void earthquake();

    /**
     * This method simulates flooding inside the building. The building is damaged accordingly.
     */
    void flooding();

    /**
     * This method prints the relevant average stats per inhabitant and year.
     */
    void printAvgStats();

    /**
     * This method prints the costs divided into decades. Only costs that occurred within the decade are printed.
     */
    void printCostsByDecade();

    /**
     * This method calculates and prints the satisfaction by decade.
     */
    void printSatisfactionByDecade();

    /**
     * This method helps calculate the average satisfaction for every decade.
     * @param startYear: starting year of the decade (incl.)
     * @param endYear: last year of the decade (excl.)
     * @return: average satisfaction in the given time
     */
    double calculateAvgSatisfactionForDecade(int startYear, int endYear);


}
