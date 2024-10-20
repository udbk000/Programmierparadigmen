import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the interface "Building" as a high-quality Building. Materials used for the construction
 * of this building are premium and durable, leading to higher construction costs. However, the satisfaction
 * of the inhabitants is higher, and the building's lifespan is longer due to better materials and construction techniques.
 */
public class hiQBuilding implements Building {

    private double co2Emissions;
    private double waste;
    private double cost;
    private double satisfaction;
    private List<Double> yearlySatisfaction;
    private int age;
    private int inhabitants;
    private double condition;
    private boolean isDeconstructed;

    public hiQBuilding(int inhabitants) {
        this.inhabitants = inhabitants;
        this.cost = 300000 + 3500 * inhabitants; // Higher construction costs due to premium materials
        this.waste = 3.5 + 0.2 * inhabitants; // Premium materials lead to slightly less waste
        this.age = 1;
        this.yearlySatisfaction = new ArrayList<>();
        this.satisfaction = 75 + (Math.random() * (99 - 85) + 1); // Higher initial satisfaction for high-quality buildings
        this.co2Emissions = 40 + inhabitants * 2.5; // Higher emissions due to premium materials
        this.condition = 100.0;
        this.isDeconstructed = false;
    }

    @Override
    public void ageOneYear() {
        if (isDeconstructed) {
            System.out.println("The high-quality building has been deconstructed.");
        } else {
            cost += 3500 * inhabitants;
            waste += 0.2 * inhabitants;
            age++;
            int AGE_EXPECTATION = 100; // Longer lifespan for high-quality buildings
            if (satisfaction > 0) {
                satisfaction -= 0.5; // Satisfaction declines slower due to better construction
            }
            yearlySatisfaction.add(satisfaction);
            co2Emissions += inhabitants * 2.5;
            condition -= 3; // Condition declines slower
            if (condition < 40) {
                int x = (int) (Math.random() * 100);
                if (x % 4 == 0) {
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
            System.out.println("The high-quality building has already been deconstructed.");
            return false;
        }
        System.out.println("Deconstructing the high-quality building...");
        waste += 15 + 0.7 * inhabitants;
        co2Emissions += 70 + inhabitants * 3.5;
        satisfaction = 0;
        condition = 0;
        isDeconstructed = true;
        return true;
    }

    @Override
    public void renovate() {
        if (isDeconstructed) {
            System.out.println("The high-quality building has been deconstructed and cannot be renovated.");
        } else {
            cost += 15000 + 2000 * inhabitants; // Higher renovation costs
            waste += 1.5 + 0.1 * inhabitants; // Less waste during renovations
            co2Emissions += 30 + inhabitants * 1.8;
            satisfaction = Math.min(satisfaction + 25, 100);
            condition = Math.min(condition + 60, 100);
            System.out.println("Renovating the high-quality building... Satisfaction improved to: " + satisfaction);
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
            System.out.println("The high-quality building has been deconstructed. No fire can occur.");
        } else {
            System.out.println("A fire has broken out in the high-quality building.");
            cost += 17000 + 2100 * inhabitants;
            co2Emissions += 51 + 5.2 * inhabitants;
            waste += 10 + 0.52 * inhabitants;
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
            System.out.println("The high-quality building has been deconstructed. No earthquake can occur.");
        } else {
            System.out.println("An earthquake has struck the high-quality building.");
            cost += 30000 + 3200 * inhabitants;
            co2Emissions += 100 + 11 * inhabitants;
            waste += 15 + 1.1 * inhabitants;
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
            System.out.println("The high-quality building has been deconstructed. No flooding can occur.");
        } else {
            System.out.println("The high-quality building has been flooded.");
            cost += 11000 + 1600 * inhabitants;
            co2Emissions += 33 + 3.1 * inhabitants;
            waste += 8 + 0.39 * inhabitants;
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
            System.out.println("This high-quality building has been deconstructed. No stats available.");
        } else {
            System.out.println("Average stats per inhabitant and year for the high-quality building:");

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
            System.out.println("This high-quality building has been deconstructed. No stats available.");
        } else {
            System.out.println("Costs per decade for the high-quality building:");
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
            System.out.println("This high-quality building has been deconstructed. No stats available.");
        } else {
            System.out.println("Satisfaction per decade for the high-quality building:");

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
