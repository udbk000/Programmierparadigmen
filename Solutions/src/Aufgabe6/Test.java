package Aufgabe6;

/**
 * Wer hat was gemacht?
 * Usaid Al-Dabak, 12216291: Room, SideRoom, UsableRoom, WindowRoom, LightRoom, OfficeInt, OfficeGen, BuildingInt, CompanyInt (Implementierung und Dokumentation)
 */
public class Test {
    public static void main(String[] args) {
        MyList<Room> list = new MyList<Room>();
        WindowRoom r = new WindowRoom(11,1);
        System.out.println(r.getName());


    }
}
