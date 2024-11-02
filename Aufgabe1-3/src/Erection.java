
/**
 * This class contains the methods and algorithms needed for each building. It combines the values of the variables in the class "Szenario" with the algorithms
 * implemented in this class in order to return and print all the necessary numbers, statistics and facts.
 */

public class Erection {

    /**
     * The variable scenario specifies the building that is being constructed. Scenario contains all values needed for the calculation of
     * important occurrences, statistics and the correct simulation of the lifespan of the building.
     */
    private Szenario scenario;

    /**
     * This variable is used to specify the size of the building and to further calculate stats per inhabitant.
     */
    private int inhabitants;

    /**
     * This variable indicates the area of the building in m2. To keep it simple, only integer values are allowed.
     */
    private int area;

    /**
     * This variable indicates the life expectancy of the building. Being final, once the building reaches this age it will be deconstructed.
     */
    private int lifeExpect;

    /**
     * This variable can be seen as a percentage of the remaining condition. Each building starts with 100, reflecting the state of the building immediately after the construction.
     * Many dependencies lead to the decline of the condition.
     */
    private int condition = 100;

    private boolean isDeconstructed;

    private int age;

    public Erection(Szenario scenario, int inhabitants, int area){
        this.scenario = scenario;
        this.inhabitants = inhabitants;
        this.area = area;
        this.lifeExpect = scenario.calculateLifeExpectancy();
        isDeconstructed = false;
        age = 1;
    }

    public void ageOneYear(){
        if(isDeconstructed){
            ;
        } else {
            age++;

        }

    }

    public void renovate(){

    }
    public void deconstruct(){

    }

    public float soilSealing(){
        return 0;
    }

    public float costsPerYear(){
        if(!isDeconstructed){
            return scenario.getMainCosts()*inhabitants;
        }
        return 0;
    }

    public float constructionCosts(){
        if(!isDeconstructed){
            return scenario.getConstructionCosts() * area;
        }
        return 0;
    }

    public float co2PerYear(){
        if(!isDeconstructed){
            return scenario.getCo2Emission() * inhabitants;
        }
        return 0;
    }

    public float wastePerYear(){
        if(!isDeconstructed){
            return scenario.getWaste() * inhabitants;
        }
        return 0;
    }

    public void catastrophe(){
        if(!isDeconstructed){
            scenario.triggerCatastrophe();
        }

    }

    public void printStats(){
        System.out.println();
        System.out.println("*********");
        System.out.println("Building Name: " + scenario.getName());
        System.out.println("Condition: " + condition + "%");
        System.out.println("Life Expectancy: " + lifeExpect + " years");
        System.out.println("Waste production per Year: " + wastePerYear() + " tons");
        System.out.println("CO2 Emission per Year: " + co2PerYear() + " tons");
        System.out.println("Construction Costs: " + constructionCosts() + " euros");
        System.out.println("Maintenance Costs per Year: " + costsPerYear() + " euros");
        System.out.println("Soil Sealing: " + soilSealing() + " m2");
        System.out.println("*********");
        System.out.println();
    }

}
