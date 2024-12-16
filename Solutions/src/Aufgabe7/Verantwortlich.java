package Aufgabe7;
import java.lang.annotation.*;

@Verantwortlich(name = "Stefan Slanar", mnr = 12327076)
// Verantwortlichkeit
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Verantwortlich {
    String name(); // Name des Hauptverantwortlichen
    int mnr();
}
