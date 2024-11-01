/**
 * This class describes a scenario of a building. It contains all the necessary information to run the algorithms implemented in
 * the "Building" class. This class does not provide own methods, as it only depicts a context of a building. Therefore, it is possible
 * to implement all kinds of scenarios of a building, only the values of each variable need to be defined. The current implementation
 * makes use of the values of several predefined scenarios given in the database.
 */
public class Szenario {

    /**
     * The name of the building. Should identify the building correctly.
     */
    private String name;

    /**
     * This float variable provides the amount of co2Emissions produced by this type of building. It is given in 1000t/year.
     */
    private float co2Emission;

    /**
     * This String variable indicates the material used for this building, giving further information on the lifespan of the building.
     */
    private String material;

    /**
     * This variable stands for the amount of waste produced by this building per year. Given in 1000t/year.
     */
    private float waste;

    /**
     * This variable provides the costs of the construction of this building. It is given in 1000€/m2.
     */
    private float constructionCosts;

    /**
     * This variable provides the maintenance costs of this building. It is given in 1000€/user/year.
     */
    private float mainCosts;


    //possible landscapes
    enum Landscape{
        CITY, FOREST, DESERT, COAST, MOUNTAIN
    }

    /**
     * The landscape in which a building is placed in manipulates the lifespan of the building as well as the probability of different
     * catastrophes occurring.
     */
    private Landscape landscape;

    public Szenario(){
        //switch for costs, lifespan, catastrophe coefficients
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


}
