package Aufgabe7;

@Verantwortlich(name = "Andrii Makarenko", mnr = 12229205)
public class LargeOffice implements Office{
    private final String name;
    private final String heatingType;
    private final int size = 3;
    private int pumpState = 0;
    private HeatPump assignedPump = null;

    public LargeOffice(String name, String heatingType) {
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

    @Override
    public void setPumpState(int state) {
        this.pumpState = state;
    }
}
