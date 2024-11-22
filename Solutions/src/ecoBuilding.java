import java.util.List;
import java.util.ArrayList;
/**
 * This class implements the interface "Building" as an ecologically sustainable Building. Materials used for the construction
 * of this building produce less emissions. As a downside, this type of building is rather costly. Approximately half
 * of the used materials in this building are reusable. Inhabitants of this building are more satisfied than those living
 * in a minimalist building. Lifespan and cycles of renovation are similar to minimalistic buildings. This type of buidling
 * requires less energy.
 */
public class ecoBuilding implements Building {

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
    private double satisfaction;

    /**
     * This list stores the yearly satisfaction values in order to calculate correct averages of satisfaction.
     */
    private List<Double> yearlySatisfaction;

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
     * This variable reflects the condition of the building, ranging from 0.0 to 100.0, 100.0 being the ideal condition
     * immediately after the construction. Conditions below 50.0 require either renovation or deconstruction.
     */
    private double condition;

    /**
     * This variable is set true when the building is deconstructed. If true, methods act accodringly.
     */
    private boolean isDeconstructed;

    /**
     * This constructor is called to construct an ecological building. The variables being set define the minimal cost of
     * every aspect (i.e. variable) of this building. These aspects are made dependent on the size of the building, therefore
     * the amount of people living in this building. The initial values are considered the values of each variable at the moment
     * of construction.
     *
     * @param inhabitants: defines the size of the building; is immediately written into the variable inhabitants.
     */
    public ecoBuilding(int inhabitants) {
        this.inhabitants = inhabitants;
        cost = 200000 + 2500 * inhabitants; //200000 = construction cost; 2500 variable costs per inhabitant
        waste = 2.5 + 0.3 * inhabitants; //2.5 = waste through construction, 0.3 = waste per inhabitant
        age = 1;
        yearlySatisfaction = new ArrayList<>();
        satisfaction = 55 + (Math.random() * (85 - 55) + 1); //55= min. initial satisfaction, 85= max. initial satisfaction
        co2Emissions = 30 + inhabitants * 2; //30 = emissions through construction, 2= emission per inhabitant
        condition = 100.0;
        isDeconstructed = false;
    }

    @Override
    public void ageOneYear() {
        if (isDeconstructed) {
            System.out.println("The ecological building has been deconstructed.");
        } else {
            cost += 2500 * inhabitants;
            waste += 0.3 * inhabitants;
            age++;
            int AGE_EXPECTATION = 50;
            if (satisfaction > 0) {
                satisfaction -= 1;
            }
            yearlySatisfaction.add(satisfaction);
            co2Emissions += inhabitants * 2;
            condition -= 5;
            if (condition < 50) {
                int x = (int) (Math.random() * 100);
                if (x % 5 == 0) {
                    this.deconstruct();
                } else {
                    this.renovate();
                }
            }
            if (age >= AGE_EXPECTATION) {
                deconstruct();
            }
        }
    }

    @Override
    public boolean deconstruct() {
        if (isDeconstructed) {
            System.out.println("The ecological building has already been deconstructed.");
            return false;
        }
        System.out.println("Deconstructing the ecological building...");
        waste += 10 + 0.5 * inhabitants;
        co2Emissions += 50 + inhabitants * 3;
        satisfaction = 0;
        condition = 0;
        isDeconstructed = true;
        return true;
    }

    @Override
    public void renovate() {
        if (isDeconstructed) {
            System.out.println("The ecological building has been deconstructed and cannot be renovated.");
        } else {
            cost += 10000 + 1500 * inhabitants;
            waste += 2 + 0.2 * inhabitants;
            co2Emissions += 20 + inhabitants * 1.5;
            satisfaction = Math.min(satisfaction + 20, 100);
            condition = Math.min(condition + 50, 100);
            System.out.println("Renovating the ecological building... Satisfaction improved to: " + satisfaction);
        }

    }

    @Override
    public boolean isDeconstructed(){
        return isDeconstructed;
    }

    @Override
    public int susIndex() {
        double index = (satisfaction / cost) * (1 / co2Emissions) * (1 / waste);
        return (int) (index * 1000);
    }

    @Override
    public void fire() {
        if (isDeconstructed) {
            System.out.println("The ecological building has been deconstructed. No fire can occur.");
        } else {
            System.out.println("A fire has broken out in the ecological building.");
            cost += 15000 + 2000 * inhabitants;
            co2Emissions += 50 + 5 * inhabitants;
            waste += 10 + 0.5 * inhabitants;
            satisfaction = Math.max(satisfaction - 30, 0);
            condition = Math.max(condition - 40, 0);

            if (condition <= 15) {
                deconstruct();
            }
        }
    }

    @Override
    public void earthquake() {
        if (isDeconstructed) {
            System.out.println("The ecological building has been deconstructed. No earthquake can occur.");
        } else {
            System.out.println("An earthquake has struck the ecological building.");
            cost += 25000 + 3000 * inhabitants;
            co2Emissions += 100 + 10 * inhabitants;
            waste += 15 + 1 * inhabitants;
            satisfaction = Math.max(satisfaction - 40, 0);
            condition = Math.max(condition - 50, 0);
            if (condition <= 15) {
                deconstruct();
            }
        }

    }

    @Override
    public void flooding() {
        if (isDeconstructed) {
            System.out.println("The ecological building has been deconstructed. No flooding can occur.");
        } else {
            System.out.println("The ecological building has been flooded.");
            cost += 10000 + 1500 * inhabitants;
            co2Emissions += 30 + 3 * inhabitants;
            waste += 8 + 0.4 * inhabitants;
            satisfaction = Math.max(satisfaction - 20, 0);
            condition = Math.max(condition - 30, 0);
            if (condition <= 15) {
                deconstruct();
            }
        }
    }

    @Override
    public void printAvgStats() {
        if (isDeconstructed) {
            System.out.println("This building has been deconstructed. No stats available.");
        } else {
            System.out.println("Average stats per inhabitant and year for the ecological building:");

            double avgCostPerInhabitantPerYear = cost / inhabitants /age;
            double avgCO2PerInhabitantPerYear = co2Emissions / inhabitants / age ;
            double avgWastePerInhabitantPerYear = waste / inhabitants / age;

            System.out.printf("Average cost per inhabitant per year: %.2f EUR%n", avgCostPerInhabitantPerYear);
            System.out.printf("Average CO2 emissions per inhabitant per year: %.2f tons%n", avgCO2PerInhabitantPerYear);
            System.out.printf("Average waste per inhabitant per year: %.2f tons%n", avgWastePerInhabitantPerYear);
            System.out.printf("Current average satisfaction: %.2f%%%n", satisfaction);
        }
    }

    @Override
    public void printCostsByDecade() {
        if(isDeconstructed){
            System.out.println("This ecological building has been deconstructed. No stats available.");
        } else {
            System.out.println("Costs per decade for the ecological building:");
            double totalCostPerDecade;
            int completedDecades = age / 10;
            int currentDecade = (age / 10) + 1;

            // costs for past decades
            for (int decade = 1; decade <= completedDecades; decade++) {
                totalCostPerDecade = 25000 * inhabitants * 10;
                System.out.printf("Costs for decade %d: %.2f EUR%n", decade, totalCostPerDecade);
            }

            // costs for current decade
            int yearsInCurrentDecade = age % 10;
            double currentDecadeCost = 2500 * inhabitants * yearsInCurrentDecade;
            System.out.printf("Costs for current decade %d (partial): %.2f EUR%n", currentDecade, currentDecadeCost);
        }
    }

    @Override
    public void printSatisfactionByDecade() {
        if(isDeconstructed){
            System.out.println("This ecological building has been deconstructed. No stats available.");
        } else {
            System.out.println("Satisfaction per decade for the ecological building:");

            int completedDecades = age / 10; // Anzahl der abgeschlossenen Jahrzehnte
            int yearsInCurrentDecade = age % 10; // Anzahl der Jahre im laufenden Jahrzehnt

            // Zufriedenheit für abgeschlossene Jahrzehnte berechnen
            for (int decade = 1; decade <= completedDecades; decade++) {
                double avgSatisfaction = calculateAvgSatisfactionForDecade((decade - 1) * 10, decade * 10);
                System.out.printf("Satisfaction for decade %d: %.2f%%%n", decade, avgSatisfaction);
            }

            // Zufriedenheit für das laufende Jahrzehnt berechnen
            if (yearsInCurrentDecade > 0) {
                double avgSatisfactionCurrentDecade = calculateAvgSatisfactionForDecade(completedDecades * 10, age);
                System.out.printf("Satisfaction for current decade (partial): %.2f%%%n", avgSatisfactionCurrentDecade);
            }
        }
    }

    @Override
    public double calculateAvgSatisfactionForDecade(int startYear, int endYear) {
        double totalSatisfaction = 0.0;

        // Berechne die Summe der Zufriedenheit für die angegebenen Jahre
        for (int i = startYear; i < endYear && i < yearlySatisfaction.size(); i++) {
            totalSatisfaction += yearlySatisfaction.get(i);
        }

        int numberOfYears = Math.min(endYear, yearlySatisfaction.size()) - startYear;
        return totalSatisfaction / numberOfYears; // Durchschnitt berechnen
    }

}
