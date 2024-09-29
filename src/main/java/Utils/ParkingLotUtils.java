package Utils;

import Constants.VehicleType;
import Models.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotUtils {
    public static List<Floor> createParkingFloors(int numberOfFloors) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i++) {
            List<Slot> slots = new ArrayList<>();

            for (int j = 0; j < 10; j++) {
                slots.add(new Slot(j, VehicleType.BIKE, 1.0));
            }

            for (int j = 10; j < 30; j++) {
                slots.add(new Slot(j, VehicleType.CAR, 2.0));
            }

            for (int j = 30; j < 40; j++) {
                slots.add(new Slot(j, VehicleType.TRUCK, 3.0));
            }
            floors.add(new Floor(i, slots));
        }
        return floors;
    }
}

