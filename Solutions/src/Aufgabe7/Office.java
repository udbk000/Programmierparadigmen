package Aufgabe7;

@Verantwortlich(name = "Andrii Makarenko", mnr = 12229205)
public interface Office {
    String getName();
    String getHeatingType();
    int getSize();
    void receive(HeatPump pump);
    int hasPump();
    int getPumpPerformance();
    void setPumpState(int state);
}
