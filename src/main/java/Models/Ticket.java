package Models;

import java.util.Date;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final int floorId;
    private final Slot slot;
    private final Date entryTime;
    private final Vehicle vehicle;

    public Ticket(int floorId, Slot slot, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID().toString();
        this.floorId = floorId;
        this.slot = slot;
        this.entryTime = new Date();
        this.vehicle = vehicle;
    }

    public String getTicketId() {
        return ticketId;
    }

    public int getFloorId() {
        return floorId;
    }

    public Slot getSlot() {
        return slot;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double calculateFees(Date exitTime) {
        long duration = exitTime.getTime() - this.entryTime.getTime();
        return (duration / (1000 * 60)) * this.slot.getSlotFeesPerMinute();
    }
}
