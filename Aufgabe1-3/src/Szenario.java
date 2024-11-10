/**
 * This class describes a scenario of a building. It contains all the necessary information to run the algorithms implemented in
 * the "Building" class. This class does not provide own methods, as it only depicts a context of a building. Therefore, it is possible
 * to implement all kinds of scenarios of a building, only the values of each variable need to be defined. The current implementation
 * makes use of the values of several predefined scenarios given in the database.
 */
public class Szenario {

    /**
     * The name of the building. Should identify the building correctly. Cannot be NULL
     */
    private String name;

    /**
     * This float variable provides the amount of co2Emissions produced by one person per year in this building. Given in t/year.
     * Cannot be negative.
     */
    private float co2Emission;

    /**
     * This String variable indicates the material used for this building, giving further information on the lifespan of the building. Cannot be NULL
     */
    private String material;

    /**
     * This variable indicates the expected age of a building with this material.
     */
    private int materialAge;

    /**
     * This variable stands for the amount of waste produced by this building per person per year. Given in 1000t/year. Cannot be negative.
     */
    private float waste;

    /**
     * This variable provides the costs of the construction of this building. It is given in 1000€/m2. Cannot be negative.
     */
    private float constructionCosts;

    /**
     * This variable provides the maintenance costs of this building. It is given in 1000€/user/year. Cannot be negative.
     */
    private float mainCosts;


    /**
     * The landscape in which a building is placed in manipulates the lifespan of the building as well as the probability of different
     * catastrophes occurring. Cannot be NULL.
     */
    private Landscape landscape;

    /**
     * This constructor is called to specify one particular scenario of a construction.
     * @param name identifies the scenario, not NULL
     * @param co2Emission not negative
     * @param material not NULL
     * @param waste not negative
     * @param constructionCosts not negative
     * @param mainCosts not negative
     * @param materialAge not negative
     * @param landscape not NULL, contains information about occurrences
     */
    public Szenario(String name, float co2Emission, String material, float waste, float constructionCosts, float mainCosts, int materialAge, Landscape landscape) {
        this.name = name;
        this.co2Emission = co2Emission;
        this.material = material;
        this.waste = waste;
        this.constructionCosts = constructionCosts;
        this.mainCosts = mainCosts;
        this.materialAge = materialAge;
        this.landscape = landscape;
    }

    public String getName(){
        return name;
    }

    public float getCo2Emission() {
        return co2Emission;
    }

    public String getMaterial(){
        return material;
    }

    public float getWaste(){
        return waste;
    }

    public float getConstructionCosts(){
        return constructionCosts;
    }

    public float getMainCosts(){
        return mainCosts;
    }

    public Landscape getLandscape(){
        return landscape;
    }

    public int getMaterialAge(){
        return materialAge;
    }


    /**
     * Calculates the adjusted life expectancy of the building based on the material age and the landscape's age factor.
     * @return integer value >= 0 indicating the life expectancy of this building in this landscape.
     */
    public int calculateLifeExpectancy() {
        return (int) (materialAge * landscape.getAgeFactor());
    }

    /**
     * Triggers a random catastrophe based on the landscape.
     */
    public void triggerCatastrophe() {
        System.out.println(landscape.triggerRandomDisaster());
    }


}
