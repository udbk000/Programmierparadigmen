package Aufgabe7;

@Verantwortlich(name = "Andrii Makarenko", mnr = 12229205)
public class LowTempHeatPump implements HeatPump {
    private final int performance;
    private int availability = 0;
    private final int price;

    public LowTempHeatPump(int performance, int price) {
        this.performance = performance;
        this.price = price;
    }

    @Override
    public int performance() {
        return performance;
    }

    @Override
    public int state() {
        return availability;
    }

    public void setAvailability(int state) {
        this.availability = state;
    }

    @Override
    public String type() {
        return "LowTemp";
    }

    @Override
    public int price(){
        return price;
    }
}
