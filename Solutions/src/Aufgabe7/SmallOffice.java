package Aufgabe7;

@Verantwortlich(name = "Andrii Makarenko", mnr = 12229205)
public class SmallOffice implements Office {
    private final String name;
    private final String heatingType;
    private final int size = 1;
    private int pumpState = 0;
    private HeatPump assignedPump = null;

    /**
     * Konstruktor für die Initialisierung eines kleinen Büros.
     *
     * @param name        der eindeutige Name des Büros
     * @param heatingType der Typ der Heizung ("LowTemp" oder "HighTemp")
     */
    public SmallOffice(String name, String heatingType) {
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
        this.pumpState = 1; // Pumpe wurde zugewiesen
    }

    @Override
    public int hasPump() {
        return pumpState;
    }

    public void setPumpState(int state) {
        this.pumpState = state;
    }
}
