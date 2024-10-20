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
        age = 0;
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
            if (age >= AGE_EXPECTATION) {
                int x = (int) (Math.random() * 100);
                if (x % 3 == 0) {
                    this.deconstruct();
                } else if (x % 3 == 1) {
                    this.renovate();
                } else {
                    if (satisfaction > 0) {
                        satisfaction = satisfaction - satisfaction * 0.2;
                    }
                }
            }
            if (satisfaction > 0) {
                satisfaction -= 1;
            }
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
            System.out.println("Renovating the ecological building...");
            cost += 10000 + 1500 * inhabitants;
            waste += 2 + 0.2 * inhabitants;
            co2Emissions += 20 + inhabitants * 1.5;
            satisfaction = Math.min(satisfaction + 20, 100);
            condition = Math.min(condition + 50, 100);
        }

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

            // Berechnung der Durchschnittswerte pro Bewohner und Jahr
            double avgCostPerInhabitantPerYear = cost / inhabitants /age +1;
            double avgCO2PerInhabitantPerYear = co2Emissions / inhabitants / age+1 ;
            double avgWastePerInhabitantPerYear = waste / inhabitants / age+1;

            // Ausgabe der Statistiken
            System.out.printf("Average cost per inhabitant per year: %.2f EUR%n", avgCostPerInhabitantPerYear);
            System.out.printf("Average CO2 emissions per inhabitant per year: %.2f tons%n", avgCO2PerInhabitantPerYear);
            System.out.printf("Average waste per inhabitant per year: %.2f tons%n", avgWastePerInhabitantPerYear);
            System.out.printf("Current average satisfaction: %.2f%%%n", satisfaction);
        }


    }




}
