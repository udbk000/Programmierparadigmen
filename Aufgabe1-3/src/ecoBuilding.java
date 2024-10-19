public class ecoBuilding implements Building{
    /**
     * This class implements the interface "Building" as an ecologically sustainable Building. Materials used for the construction
     * of this building produce less emissions. As a downside, this type of building is rather costly. Approximately half
     * of the used materials in this building are reusable. Inhabitants of this building are more satisfied than those living
     * in a minimalist building. Lifespan and cycles of renovation are similar to minimalistic buildings. This type of buidling
     * requires less energy.
     */

    /**
     * Variable co2Emissions defines the amount of CO2 emissions produced by an ecoBuilding.
     * These Co2Emissions are an average amount of CO2 emissions in tons per year per inhabitant of this building.
      */
    private double co2Emissions;

    /**
     * Variable waste defines the average amount of waste produced by a single inhabitant per year
     * in tons. This waste is neither recyclable nor reusable.
     */
    private double waste;

    /**
     *
     */
    private double cost;
    private double satisfaction;
    private int age;
    private int inhabitants;
    private int apartments;
    @Override
    public void build(int apartments, int inhabitants) {

    }

    @Override
    public void age() {

    }

    @Override
    public boolean deconstruct() {
        return false;
    }

    @Override
    public void renovate() {

    }

    @Override
    public int susIndex() {
        return 0;
    }
}
