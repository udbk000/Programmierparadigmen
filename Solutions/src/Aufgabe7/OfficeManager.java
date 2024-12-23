package Aufgabe7;

import java.util.LinkedList;
import java.util.List;

public class OfficeManager {
    List<Office> offices = new LinkedList<>();

    public void addOffice(Office toAdd){
        offices.add(toAdd);
    }

    public void showOffices(){
        System.out.println("--- Information about all offices ---");
        System.out.println("1 - small office, 2 - medium large office, 3 - large office");
        System.out.println();
        for (Office o : offices) {
            System.out.println("Office name: " + o.getName());
            System.out.println("Office size: " + o.getSize());
            if(o.hasPump() == 0){
                System.out.println("This office has no heat pump installed. ");
            } else if(o.hasPump()==1){
                System.out.println("This office has this type of heating: " + o.getHeatingType());
                System.out.println("The pump in this office has the following performance level: " + o.getPumpPerformance());
                System.out.println();
            }
        }
        System.out.println("--- End of office information ---");
    }
}
