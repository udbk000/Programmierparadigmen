package Aufgabe5;

/**
 * Wer hat was gemacht?
 * Usaid Al-Dabak, 12216291: Admin, Approvable, ApprovableSet, ApSet, AdminSet, MyIterator, MyList, MyListIterator, Node implementiert (und entsprechend die Kommentare in diesen Klassen verfasst)
 * Stefan Slanar, 12327076: Path-Klasse, mitgearbeitet an Listenimplementierung und Debugging
 * Andrii Makarenko, 12229205: Counter, RCounter, Space, Exterior, Interior
 */
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Schritt 1: Erstellen von Objekten der geforderten Typen
        ApSet<Counter<String>, Counter<String>, String> apSet1 = new ApSet<>();
        ApSet<Counter<Integer>, Counter<Integer>, Integer> apSet2 = new ApSet<>();
        ApSet<Counter<Path<String>>, Counter<Path<String>>, Path<String>> apSet3 = new ApSet<>();
        ApSet<RCounter, RCounter, Path<RCounter>> apSet4 = new ApSet<>();
        ApSet<Space<String>, String, Path<Space<String>>> apSet5 = new ApSet<>();
        ApSet<Interior<String>, String, Path<Space<String>>> apSet6 = new ApSet<>();
        ApSet<Exterior<String>, String, Path<Space<String>>> apSet7 = new ApSet<>();

        AdminSet<RCounter, RCounter, Path<RCounter>> adminSet1 = new AdminSet<>();
        AdminSet<Space<String>, String, Path<Space<String>>> adminSet2 = new AdminSet<>();
        AdminSet<Interior<String>, String, Path<Space<String>>> adminSet3 = new AdminSet<>();
        AdminSet<Exterior<String>, String, Path<Space<String>>> adminSet4 = new AdminSet<>();

        // Befüllen der Container
        fillContainer(apSet5, new Space<>("Raum A"), new Space<>("Raum B"));
        fillContainer(apSet6, new Interior<>("Innenraum A", 50), new Interior<>("Innenraum B", 100));
        fillContainer(apSet7, new Exterior<>("Außenbereich A", true), new Exterior<>("Außenbereich B", false));

        fillContainer(adminSet2, new Space<>("Verwaltungsraum A"), new Space<>("Verwaltungsraum B"));
        fillContainer(adminSet3, new Interior<>("Verwaltungsinnenraum A", 60), new Interior<>("Verwaltungsinnenraum B", 120));
        fillContainer(adminSet4, new Exterior<>("Verwaltungsaußenbereich A", true), new Exterior<>("Verwaltungsaußenbereich B", false));

        // Schritt 2: Lesen von b und c und Einfügen in a
        AdminSet<Space<String>, String, Path<Space<String>>> a = adminSet2;
        AdminSet<Interior<String>, String, Path<Space<String>>> b = adminSet3;
        AdminSet<Exterior<String>, String, Path<Space<String>>> c = adminSet4;

        // Iteration über b und c, Aufruf von area() oder isPublic(), Einfügen in a
        for (Interior<String> interior : b) {
            System.out.println("Interior area: " + interior.area());
            a.add(interior);
        }

        //for (Exterior<String> exterior : c) {
        //    System.out.println("Exterior isPublic: " + exterior.isPublic());
        //    a.add(exterior);
        //}

        // Schritt 3: Testen von Untertypbeziehungen
        if (adminSet1 instanceof ApSet) {
            System.out.println("AdminSet<RCounter, ...> ist ein Untertyp von ApSet<RCounter, ...>");
        } else {
            System.out.println("AdminSet<RCounter, ...> ist kein Untertyp von ApSet<RCounter, ...>");
        }

        if (RCounter.class.isAssignableFrom(Counter.class)) {
            System.out.println("RCounter ist ein Untertyp von Counter");
        } else {
            System.out.println("RCounter ist kein Untertyp von Counter");
        }

        // Iteration über b und c, Aufruf von area() oder isPublic(), Einfügen in a
        for (Interior<String> interior : b) {
            System.out.println("Interior area: " + interior.area());
            a.add(interior);
        }

        for (Exterior<String> exterior : c) {
            System.out.println("Exterior isPublic: " + exterior.isPublic());
            a.add(exterior);
        }

        // Schritt 3: Testen von Untertypbeziehungen
        if (adminSet1 instanceof ApSet) {
            System.out.println("AdminSet<RCounter, ...> ist ein Untertyp von ApSet<RCounter, ...>");
        } else {
            System.out.println("AdminSet<RCounter, ...> ist kein Untertyp von ApSet<RCounter, ...>");
        }

        if (RCounter.class.isAssignableFrom(Counter.class)) {
            System.out.println("RCounter ist ein Untertyp von Counter");
        } else {
            System.out.println("RCounter ist kein Untertyp von Counter");
        }

        // Schritt 4: Überprüfung der Funktionalität
        System.out.println("\n--- Löschen und erneutes Einfügen ---");
        Space<String> spaceToDelete = new Space<>("Testbereich");
        adminSet2.add(spaceToDelete);

        // Iterieren, um ein Element zu löschen
        MyIterator<Space<String>> iterator = adminSet2.iteratorAll();
        while (iterator.hasNext()) {
            Space<String> space = iterator.next();
            System.out.println("Vor dem Löschen: " + space);
            if (space.equals(spaceToDelete)) {
                iterator.remove(); // Löscht das aktuelle Element
                System.out.println("Element gelöscht: " + space);
            }
        }

        // Prüfen des Inhalts nach dem Löschen
        System.out.println("Nach dem Löschen:");
        iterator = adminSet2.iteratorAll();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Schritt 5: Optionale weitere Tests
        System.out.println("\n--- Optionale Tests ---");
        System.out.println("Inhalt von AdminSet<Interior>:");
        for (Interior<String> interior : adminSet3) {
            System.out.println(interior);
        }
    }

    // Generische Methode zum Befüllen von Containern
    @SafeVarargs
    private static <T extends Approvable<?, ?>> void fillContainer(ApprovableSet<T, ?, ?> container, T... items) {
        for (T item : items) {
            container.add(item);
        }
    }
}
