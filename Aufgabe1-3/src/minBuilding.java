import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the interface "Building" as a minimalist Building. Materials used for the construction
 * are cheap, leading to low construction costs and less durability. The satisfaction of the inhabitants
 * is generally lower, and the building's lifespan is shorter.
 */
public class minBuilding implements Building {

    private double co2Emissions;
    private double waste;
    private double cost;
    private double satisfaction;
    private List<Double> yearlySatisfaction;
    private int age;
    private int inhabitants;
    private double condition;
    private boolean isDeconstructed;

    public minBuilding(int inhabitants) {
        this.inhabitants = inhabitants;
        this.cost = 100000 + 1500 * inhabitants; // Lower construction costs due to cheap materials
        this.waste = 2.0 + 0.5 * inhabitants; // Cheap materials produce more waste
        this.age = 1;
        this.yearlySatisfaction = new ArrayList<>();
        this.satisfaction = 45 + (Math.random() * (65 - 45) + 1); // Lower initial satisfaction for minimalist buildings
        this.co2Emissions = 20 + inhabitants * 1.5; // Less emissions due to lower material quality
        this.condition = 100.0;
        this.isDeconstructed = false;
    }

    @Override
    public void ageOneYear() {
        if (isDeconstructed) {
            System.out.println("The minimalist building has been deconstructed.");
        } else {
            cost += 1500 * inhabitants;
            waste += 0.5 * inhabitants;
            age++;
            int AGE_EXPECTATION = 50;
            if (satisfaction > 0) {
                satisfaction -= 2.0; // Satisfaction declines faster
            }
            yearlySatisfaction.add(satisfaction);
            co2Emissions += inhabitants * 1.5;
            condition -= 6; // Condition declines faster
            if (condition < 30) {
                int x = (int) (Math.random() * 100);
                if (x % 3 == 0) {
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
            System.out.println("The minimalist building has already been deconstructed.");
            return false;
        }
        System.out.println("Deconstructing the minimalist building...");
        waste += 8 + 0.6 * inhabitants;
        co2Emissions += 40 + inhabitants * 2.0;
        satisfaction = 0;
        condition = 0;
        isDeconstructed = true;
        return true;
    }

    @Override
    public void renovate() {
        if (isDeconstructed) {
            System.out.println("The minimalist building has been deconstructed and cannot be renovated.");
        } else {
            cost += 8000 + 1000 * inhabitants; // Cheaper renovation costs
            waste += 1.0 + 0.3 * inhabitants; // More waste during renovations
            co2Emissions += 10 + inhabitants * 1.2;
            satisfaction = Math.min(satisfaction + 15, 100);
            condition = Math.min(condition + 40, 100);
            System.out.println("Renovating the minimalist building... Satisfaction improved to: " + satisfaction);
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
            System.out.println("The minimalist building has been deconstructed. No fire can occur.");
        } else {
            System.out.println("A fire has broken out in the minimalist building.");
            cost += 10000 + 1500 * inhabitants;
            co2Emissions += 100 + 7 * inhabitants;
            waste += 14 + 0.7 * inhabitants;
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
            System.out.println("The minimalist building has been deconstructed. No earthquake can occur.");
        } else {
            System.out.println("An earthquake has struck the minimalist building.");
            cost += 17000 + 2500 * inhabitants;
            co2Emissions += 180 + 12 * inhabitants;
            waste += 18 + 1 * inhabitants;
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
            System.out.println("The minimalist building has been deconstructed. No flooding can occur.");
        } else {
            System.out.println("The minimalist building has been flooded.");
            cost += 7000 + 900 * inhabitants;
            co2Emissions += 34 + 3.5 * inhabitants;
            waste += 12 + 0.5 * inhabitants;
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
            System.out.println("This minimalist building has been deconstructed. No stats available.");
        } else {
            System.out.println("Average stats per inhabitant and year for the minimalist building:");

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
            System.out.println("This minimalist building has been deconstructed. No stats available.");
        } else {
            System.out.println("Costs per decade for the minimalist building:");
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
            System.out.println("This minimalist building has been deconstructed. No stats available.");
        } else {
            System.out.println("Satisfaction per decade for the minimalist building:");

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

