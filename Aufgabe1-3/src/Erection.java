
/**
 * This class contains the methods and algorithms needed for each building. It combines the values of the variables in the class "Szenario" with the algorithms
 * implemented in this class in order to return and print all the necessary numbers, statistics and facts.
 */

public class Erection {

    /**
     * STYLE:
     * Diese Klasse ist objektorientiert implementiert. Variablen sind private. Es wird nominal abstrahiert.
     * Kommentare sind sinnvoll und unterstützen die Abstraktion. Dort, wo es notwendig ist, sind Invarianten gelistet.
     * Es wurde insofern abstrahiert, als so gut wie die ganze Implementierung lediglich abstrahierte Methoden und Variablen nutzt, ohne viel Code.
     */

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

    /**
     * This variable keeps track of the age of the building. Cannot be higher than lifeExpect.
     */
    private int age;

    /**
     * This variable keeps track of additional renovation costs.
     */
    private int renovationCosts;

    public Erection(Szenario scenario, int inhabitants, int area) {
        this.scenario = scenario;
        this.inhabitants = inhabitants;
        this.area = area;
        this.lifeExpect = scenario.calculateLifeExpectancy();
        isDeconstructed = false;
        age = 1;
        renovationCosts = 0;
    }

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

    public void renovate() {
        if (!isDeconstructed) {
            renovationCosts += Math.random() * inhabitants * 100;
            condition = Math.min(condition + 60, 100);
            System.out.println("Renovating the " + scenario.getName());
        } else {
            ;
        }
    }

    public void deconstruct() {
        if (!isDeconstructed) {
            isDeconstructed = true;
        }
    }

    public float soilSealing() {

        return 0;
    }

    public float costsPerYear() {
        if (!isDeconstructed) {
            return scenario.getMainCosts() * inhabitants + (renovationCosts / age);
        }
        return 0;
    }

    public float constructionCosts() {
        if (!isDeconstructed) {
            return scenario.getConstructionCosts() * area;
        }
        return 0;
    }

    public float co2PerYear() {
        if (!isDeconstructed) {
            return scenario.getCo2Emission() * inhabitants;
        }
        return 0;
    }

    public float wastePerYear() {
        if (!isDeconstructed) {
            return scenario.getWaste() * inhabitants;
        }
        return 0;
    }

    public void catastrophe() {
        if (!isDeconstructed) {
            scenario.triggerCatastrophe();
            condition -= Math.min((int) (Math.random()*100),90);
        }

    }

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
        System.out.println("Soil Sealing: " + soilSealing() + " m2");
        System.out.println("*********");
        System.out.println();
    }

}
