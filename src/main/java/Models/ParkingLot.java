package Models;

import lombok.Data;

import java.util.*;

@Data
public class ParkingLot {
    private final List<Floor> floors;
    private final Map<String, Ticket> activeTickets;

    public ParkingLot(List<Floor> floors) {
        this.floors = floors;
        this.activeTickets = new HashMap<>();
    }

    public Optional<String> parkVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            Slot slot = floor.findAvailableSlot(vehicle.getType());
            if (slot != null) {
                slot.assignVehicle();
                Ticket ticket = new Ticket(floor.getFloorId(), slot, vehicle);
                activeTickets.put(ticket.getTicketId(), ticket);
                return Optional.of(ticket.getTicketId());
            }
        }
        return Optional.empty();
    }

    public double calculateFees(String ticketId, Date exitTime) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid Ticket ID");
        }
        return ticket.calculateFees(exitTime);
    }

    public void unParkVehicle(String ticketId, Date exitTime) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid Ticket ID");
        }
        double fee = ticket.calculateFees(exitTime);
        ticket.getSlot().freeSlot();
        activeTickets.remove(ticketId);
        System.out.println("Parking Fee for Ticket " + ticketId + ": " + fee);
    }

    public int getAvailableSpots() {
        return floors.stream()
                .mapToInt(Floor::getAvailableSpots)
                .sum();
    }
}
