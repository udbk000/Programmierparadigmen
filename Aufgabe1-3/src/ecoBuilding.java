


/**
 * This class implements the interface "Building" as an ecologically sustainable Building. Materials used for the construction
 * of this building produce less emissions. As a downside, this type of building is rather costly. Approximately half
 * of the used materials in this building are reusable. Inhabitants of this building are more satisfied than those living
 * in a minimalist building. Lifespan and cycles of renovation are similar to minimalistic buildings. This type of buidling
 * requires less energy.
 */
public class ecoBuilding implements Building{

    /**
     * Variable co2Emissions defines the amount of CO2 emissions produced by an ecoBuilding in tons.
      */
    private double co2Emissions;

    /**
     * Variable waste defines amount of waste produced in this building in tons. This waste is neither recyclable nor reusable.
     */
    private double waste;

    /**
     * Variable cost defines the cost of this building in euros. All expenses are added to this variable.
     */
    private double cost;

    /**
     * Variable satisfaction is an index of the average satisfaction of an inhabitant in this building.
     * This variable can only accept values between 0.0 (low satisfaction) and 100.0 (max satisfaction).
      */
    private int satisfaction;

    /**
     * Variable age refers to the age of the building. It stands for the years which this building has been standing.
     * The expected lifespan of an ecological building is around 50 years. It can be elongated if the building is renovated.
     */
    private int age;

    /**
     * This variable reflects the amount of people living in this ecological building. To keep the
     * simulation simple, the number of inhabitants does not change as long as the building is standing.
     */
    private int inhabitants;

    /**
     * This constructor is called to construct an ecological building. The variables being set define the minimal cost of
     * every aspect (i.e. variable) of this building. These aspects are made dependent on the size of the building, therefore
     * the amount of people living in this building. The initial values are considered the values of each variable at the moment
     * of construction.
     * @param inhabitants: defines the size of the building; is immediately written into the variable inhabitants.
     */
    public ecoBuilding(int inhabitants){
        this.inhabitants = inhabitants;
        cost = 200000 + 2500*inhabitants; //200000 = construction cost; 2500 variable costs per inhabitant
        waste = 2.5 + 0.3*inhabitants; //2.5 = waste through construction, 0.3 = waste per inhabitant
        age = 0;
        satisfaction = 55 + (int) (Math.random() * (85-55)+1); //55= min. initial satisfaction, 85= max. initial satisfaction
        co2Emissions = 30 + inhabitants*2; //30 = emissions through construction, 2= emission per inhabitant
    }

    @Override
    public void ageOneYear() {

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
