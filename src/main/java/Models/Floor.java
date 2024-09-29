package Models;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class Floor {
    private final int floorId;
    private final List<Slot> slots;

    public Floor(int floorId, List<Slot> slots) {
        this.floorId = floorId;
        this.slots = slots;
    }

    public int getFloorId() {
        return floorId;
    }

    public List<Slot> getSlots() {
        return Collections.unmodifiableList(slots);
    }

    public Slot findAvailableSlot(Constants.VehicleType vehicleType) {
        for (Slot slot : slots) {
            if (slot.canFitVehicle(vehicleType)) {
                return slot;
            }
        }
        return null;
    }

    public int getAvailableSpots() {
        int availableSpots = 0;
        for (Slot slot : slots) {
            if (slot.isAvailable()) {
                availableSpots++;
            }
        }
        return availableSpots;
    }
}
