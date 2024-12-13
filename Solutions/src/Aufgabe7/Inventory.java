package Aufgabe7;

import java.util.LinkedList;
import java.util.List;

public class Inventory {
    List<HeatPump> heatPumps = new LinkedList<>();

    public void addHeatPump(HeatPump toAdd){
        heatPumps.add(toAdd);
    }

    public void deleteHeatPump(HeatPump toDelete){
        heatPumps.remove(toDelete);
    }

    public HeatPump assignHeatPump(Office toOffice){
        HeatPump pump;
        int officeSize = toOffice.getSize();
        String type = toOffice.getHeatingType();
        for (HeatPump p : heatPumps) {
            if(p.performance() == toOffice.getSize()){
                if(p.type().equals(type)){
                    toOffice.receive(p);
                    return p;
                }

            }
        }
        for (HeatPump p : heatPumps) {
            if(p.performance() +1 == toOffice.getSize()){
                if(p.type().equals(type)) {
                    toOffice.receive(p);
                    return p;
                }
            }
        }
        System.out.println("Cannot assign heat pump to this office because there is no suiting heat pump in the inventory");
        return null;
    }

    public void returnHeatPump(HeatPump toReturn, Office fromOffice) {
        toReturn.setAvailability(0);
        fromOffice.setPumpState(0);
    }

    public void priceAvailable(){
        int prices = 0;
        for (HeatPump pump : heatPumpsAvailable) {
            if(pump.state() == 0){
                prices = pump.price() + prices;
            }
        }
        System.out.println("All available heat pumps cost " + prices + "€ altogether.");

    }

    public void priceInstalled(){
        int prices = 0;
        for (HeatPump pump : heatPumps) {
            if(pump.state() == 1){
                prices = pump.price() + prices;
            }


        }
        System.out.println("All assigned heat pumps cost " + prices + "€ altogether.");
    }

    public void showHeatPumps(){
        System.out.println("--- Information about all heat pumps ---");
        System.out.println("1 - low performance, 2 - medium performance, 3 - high performance");
        System.out.println();
        System.out.println("1. Information about available heat pumps that are in the inventory:");
        int counter = 0;
        for (HeatPump pump: heatPumps) {
            if(pump.state() == 0){
                counter++;
                System.out.println("Available heat pump No." + counter + ": ");
                System.out.println("Price: " + pump.price() + "€");
                System.out.println("Performance level: " + pump.performance());
                System.out.println("Type of pump: " + pump.type());
                System.out.println();
            }


        }
        System.out.println();
        System.out.println("2. Information about installed heat pumps");
        counter = 0;
        for (HeatPump pump : heatPumps) {
            if(pump.state() == 1){
                counter++;
                System.out.println("Assigned heat pump No." + counter + ": ");
                System.out.println("Price: " + pump.price() + "€");
                System.out.println("Performance level: " + pump.performance());
                System.out.println("Heat pump is installed in: " + pump.installedIn());
                System.out.println("Type of pump" + pump.type());
                System.out.println();
            }

        }
        System.out.println("--- End of information list. ---");
    }

}
