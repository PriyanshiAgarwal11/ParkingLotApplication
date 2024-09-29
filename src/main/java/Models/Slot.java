package Models;

import Constants.VehicleType;

public class Slot {
    private final int slotId;
    private final VehicleType vehicleType;
    private boolean isAvailable;
    private final double slotFeesPerMinute;

    public Slot(int slotId, VehicleType vehicleType, double slotFeesPerMinute) {
        this.slotId = slotId;
        this.vehicleType = vehicleType;
        this.slotFeesPerMinute = slotFeesPerMinute;
        this.isAvailable = true;
    }

    public int getSlotId() {
        return slotId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getSlotFeesPerMinute() {
        return slotFeesPerMinute;
    }

    public boolean canFitVehicle(VehicleType vehicleType) {
        return this.vehicleType == vehicleType && isAvailable;
    }

    public void assignVehicle() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            throw new IllegalStateException("Slot is already occupied");
        }
    }

    public void freeSlot() {
        isAvailable = true;
    }
}

