package Aufgabe7;

@Verantwortlich(name = "Stefan Slanar", mnr = 12327076)
public class Test {
    public static void main(String[] args) {
        // Klassen mit Annotation inspizieren
        printAnnotationDetails(HeatPump.class);
        printAnnotationDetails(LowTempHeatPump.class);
    }

    public static void printAnnotationDetails(Class<?> clazz) {
        // Prüfen, ob die Klasse die Annotation @Verantwortlich besitzt
        if (clazz.isAnnotationPresent(Verantwortlich.class)) {
            Verantwortlich annotation = clazz.getAnnotation(Verantwortlich.class);
            System.out.println("Class: " + clazz.getSimpleName());
            System.out.println("  Author: " + annotation.name());
            System.out.println("  Matrikelnummer: " + annotation.mnr());
        } else {
            System.out.println("Class: " + clazz.getSimpleName() + " has no @Verantwortlich annotation.");
        }
    }
}
