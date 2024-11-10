
/**
 * This class contains the methods and algorithms needed for each building. It combines the values of the variables in the class "Szenario" with the algorithms
 * implemented in this class in order to return and print all the necessary numbers, statistics and facts.
 */

public class Construction {

    /**
     * STYLE:
     * Diese Klasse ist objektorientiert implementiert. Variablen sind private. Es wird nominal abstrahiert.
     * Kommentare sind sinnvoll und unterstützen die Abstraktion. Dort, wo es notwendig ist, sind Invarianten gelistet.
     * Es wurde insofern abstrahiert, als so gut wie die ganze Implementierung lediglich abstrahierte Methoden und Variablen nutzt, ohne viel Code.
     */

    /**
     * The variable scenario specifies the building that is being constructed. Scenario contains all values needed for the calculation of
     * important occurrences, statistics and the correct simulation of the lifespan of the building.
     * Precondition: Cannot be null.
     */
    private Szenario scenario;

    /**
     * This variable is used to specify the size of the building and to further calculate stats per inhabitant.
     * Inhabitants must not be negative.
     */
    private int inhabitants;

    /**
     * This variable indicates the area of the building in m2. To keep it simple, only integer values are allowed.
     * Area must not be negative.
     */
    private int area;

    /**
     * This variable indicates the life expectancy of the building. Being final, once the building reaches this age it will be deconstructed.
     * LifeExpect must not be negative.
     */
    private int lifeExpect;

    /**
     * This variable can be seen as a percentage of the remaining condition. Each building starts with 100, reflecting the state of the building immediately after the construction.
     * Many dependencies lead to the decline of the condition.
     * Condition is initially 100, decreases gradually until it reaches the value 0. Cannot be negative. Can increase again, must stay in the range 0 <= condition <= 100.
     */
    private int condition = 100;

    /**
     * This boolean variable indicates whether a building has been deconstructed, facilitating method calls in case the building no longer exists.
     * When the constructor is called, this variable is set false. Once deconstruct() is called, the variable is set true.
     * The variable cannot change to false once it is set true. Can only be changed to true once.
     */
    private boolean isDeconstructed;

    /**
     * This variable keeps track of the age of the building. Cannot be higher than lifeExpect.
     * This variable is always positive. The value this variable holds can only rise.
     */
    private int age;

    /**
     * This variable keeps track of additional renovation costs.
     * Cannot be negative.
     */
    private int renovationCosts;

    /**
     * This constructor initializes "this", needs three parameters
     * @param scenario not NULL, holds important information about the building
     * @param inhabitants >= 0
     * @param area >= 0
     */
    public Construction(Szenario scenario, int inhabitants, int area) {
        this.scenario = scenario;
        this.inhabitants = inhabitants;
        this.area = area;
        this.lifeExpect = scenario.calculateLifeExpectancy();
        isDeconstructed = false;
        age = 1;
        renovationCosts = 0;
    }

    /**
     * This method ages the building by one year.
     * Postcondition age = age+1
     */
    public void ageOneYear() {
        /**
         * STYLE:
         * Der Prozess der Alterung um ein Jahr kann als eine Prozedur angesehen werden. Diese Methode ist an sich prozedural implementiert,
         * da diese Prozedur eine bestimme Aufgabe erfüllt und hier nicht in andere Methoden oder Klassen ausgeweicht wird. Beispielsweise könnten Methoden
         * überprüfen, ob Renovierung oder Abbau notwendig sind. Aufgrund des Prozeduralen Stils wird darauf verzichtet.
         */
        if (isDeconstructed) {
            ;
        } else {
            age++;
            condition = condition - 100 / lifeExpect;
            if (condition <= 0) {
                deconstruct();
            } else if (condition < 20) {
                int x = (int) (Math.random() * 100);
                if (x % 4 == 0) {
                    this.deconstruct();
                } else {
                    this.renovate();
                }
            }
            if(age>lifeExpect){
                deconstruct();
            }

        }

    }

    /**
     * This method is called when the building needs renovation. It calculates random costs of the renovation.
     * Costs of the renovation are added to renovationCosts.
     */
    public void renovate() {
        if (!isDeconstructed) {
            renovationCosts += Math.random() * inhabitants * 100;
            condition = Math.min(condition + 60, 100);
            System.out.println("Renovating the " + scenario.getName());
        } else {
            ;
        }
    }

    /**
     * This method results in the deconstructions of the building. Once called, all values cannot be changed anymore. Values cannot be
     * accessed from outside anymore once the building is deconstructed.
     */
    public void deconstruct() {
        if (!isDeconstructed) {
            isDeconstructed = true;
        }
    }

    /**
     * This method calculates the current average costs of this construction. This average is calculated by using the
     * maintenance costs and the renovation costs of this construction.
     * @return the current average of costs per year of this construction
     */
    public float costsPerYear() {
        if (!isDeconstructed) {
            return scenario.getMainCosts() * inhabitants + (renovationCosts / age);
        }
        return 0;
    }

    /**
     * This method calculates the construction costs of this building. This value cannot change during the lifespan of the building.
     * @return a float value larger than 0
     */
    public float constructionCosts() {
        if (!isDeconstructed) {
            return scenario.getConstructionCosts() * area;
        }
        return 0;
    }

    /**
     * This method calculates the co2 emission of this building per year. It depends on the current amount of inhabitants.
     * @return float larger than 0
     */
    public float co2PerYear() {
        if (!isDeconstructed) {
            return scenario.getCo2Emission() * inhabitants;
        }
        return 0;
    }

    /**
     * This method calculates the waste produced per year by this construction. It depends on the current amount of inhabitants in this building.
     * @return float value >= 0
     */
    public float wastePerYear() {
        if (!isDeconstructed) {
            return scenario.getWaste() * inhabitants;
        }
        return 0;
    }

    /**
     * This method is called to have a catastrophe affect the building. Each call decreases the condition of the building.
     */
    public void catastrophe() {
        if (!isDeconstructed) {
            scenario.triggerCatastrophe();
            condition -= Math.min((int) (Math.random()*100),90);
        }

    }

    public int getLifeExpect(){
        return lifeExpect;
    }

    public String getBuildingType(){
        return scenario.getName();
    }

    public Szenario getScenario(){
        return scenario;
    }

    public boolean isDeconstructed(){
        return isDeconstructed;
    }

    /**
     * This method is called to print a brief overview on all relevant aspects of this construction.
     */
    public void printStats() {
        System.out.println();
        System.out.println("*********");
        System.out.println("Building Name: " + scenario.getName());
        System.out.println("Building Material: " + scenario.getMaterial());
        System.out.println("Building Location: " + scenario.getLandscape());
        System.out.println("Condition: " + condition + "%");
        System.out.println("Life Expectancy: " + lifeExpect + " years");
        System.out.println("Waste production per Year: " + wastePerYear() + " tons");
        System.out.println("CO2 Emission per Year: " + co2PerYear() + " tons");
        System.out.println("Construction Costs: " + constructionCosts() + " euros");
        System.out.println("Maintenance Costs per Year: " + costsPerYear() + " euros");
        System.out.println("*********");
        System.out.println();
    }

}
