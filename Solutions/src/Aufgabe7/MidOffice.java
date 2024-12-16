package Aufgabe7;

@Verantwortlich(name = "Andrii Makarenko", mnr = 12229205)
public class MidOffice implements Office {
    private final String name;
    private final String heatingType; // "LowTemp" oder "HighTemp"
    private final int size = 2; // Mittelgroßes Büro = 2
    private int pumpState = 0; // 0 = keine Pumpe, 1 = Pumpe installiert
    private HeatPump assignedPump = null;

    public MidOffice(String name, String heatingType) {
        this.name = name;
        this.heatingType = heatingType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getHeatingType() {
        return heatingType;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getPumpPerformance() {
        return assignedPump != null ? assignedPump.performance() : 0;
    }

    @Override
    public void receive(HeatPump pump) {
        this.assignedPump = pump;
        this.pumpState = 1;
    }

    @Override
    public int hasPump() {
        return pumpState;
    }

    public void setPumpState(int state) {
        this.pumpState = state;
    }
}
