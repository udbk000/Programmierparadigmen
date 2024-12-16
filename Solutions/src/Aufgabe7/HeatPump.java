package Aufgabe7;

@Verantwortlich(name = "Andrii Makarenko", mnr = 12229205)
public interface HeatPump {
    int performance();
    int state();
    String type();
    void setAvailability(int state);
    int price();
}
