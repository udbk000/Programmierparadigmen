package Aufgabe6;

/**
 * Wer hat was gemacht?
 * Usaid Al-Dabak, 12216291: Room, SideRoom, UsableRoom, WindowRoom, LightRoom, OfficeInt, OfficeGen, BuildingInt, CompanyInt (Implementierung und Dokumentation)
 * Stefan Slanar, 12327076: RoomList, RoomNode, BuildingList, BuildingNode, OfficeList, OfficeNode, IterableList, LinkedListIterator
 */
public class Test {
    public static void main(String[] args) {
        MyList<Room, Room, Room> list = new MyList<Room, Room, Room>();
        WindowRoom r = new WindowRoom(11,1);
        System.out.println(r.getName());


    }
}
