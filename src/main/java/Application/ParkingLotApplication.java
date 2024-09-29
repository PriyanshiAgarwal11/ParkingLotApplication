package Application;

import Constants.VehicleType;
import Models.*;
import Utils.ParkingLotUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class ParkingLotApplication {
    public static void main(String[] args) {
        int numberOfFloors = 3;
        ParkingLot parkingLot = new ParkingLot(ParkingLotUtils.createParkingFloors(numberOfFloors));
        System.out.println("Initial Available Spots: " + parkingLot.getAvailableSpots());
        Vehicle bike = new Vehicle(VehicleType.BIKE, "BIKE_123");
        Optional<String> bikeTicketIdOpt = parkingLot.parkVehicle(bike);
        bikeTicketIdOpt.ifPresent(ticketId -> {
            Ticket bikeTicket = parkingLot.getActiveTickets().get(ticketId);
            System.out.println("After Bike Parking: " + parkingLot.getAvailableSpots() +
                    " spots left. TicketId: " + ticketId +
                    ". Parked at Floor " + bikeTicket.getFloorId() +
                    ", Slot " + bikeTicket.getSlot().getSlotId());
        });
        Vehicle car = new Vehicle(VehicleType.CAR, "CAR_123");
        Optional<String> carTicketIdOpt = parkingLot.parkVehicle(car);
        carTicketIdOpt.ifPresent(ticketId -> {
            Ticket carTicket = parkingLot.getActiveTickets().get(ticketId);
            System.out.println("After Car Parking: " + parkingLot.getAvailableSpots() +
                    " spots left. TicketId: " + ticketId +
                    ". Parked at Floor " + carTicket.getFloorId() +
                    ", Slot " + carTicket.getSlot().getSlotId());
        });
        Vehicle bike1 = new Vehicle(VehicleType.BIKE, "BIKE_121");
        Optional<String> bikeTicketIdOpt1 = parkingLot.parkVehicle(bike1);
        bikeTicketIdOpt1.ifPresent(ticketId -> {
            Ticket bikeTicket = parkingLot.getActiveTickets().get(ticketId);
            System.out.println("After Bike Parking: " + parkingLot.getAvailableSpots() +
                    " spots left. TicketId: " + ticketId +
                    ". Parked at Floor " + bikeTicket.getFloorId() +
                    ", Slot " + bikeTicket.getSlot().getSlotId());
        });
        // Simulate parking duration
        simulateParking(parkingLot, carTicketIdOpt, 3);
        simulateParking(parkingLot, bikeTicketIdOpt, 1);
        simulateParking(parkingLot, bikeTicketIdOpt1, 4);
    }

    private static void simulateParking(ParkingLot parkingLot, Optional<String> ticketIdOpt, int exitTimeInMinutes) {
        if (ticketIdOpt.isPresent()) {
            String ticketId = ticketIdOpt.get();
            Ticket ticket = parkingLot.getActiveTickets().get(ticketId);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ticket.getEntryTime());
            calendar.add(Calendar.MINUTE, exitTimeInMinutes);
            Date exitTime = calendar.getTime();
            double fee = parkingLot.calculateFees(ticketId, exitTime);
            parkingLot.unParkVehicle(ticketId, exitTime);
            System.out.println("After Unparking: " + parkingLot.getAvailableSpots() + " spots left. Fee: " + fee);
        }
    }
}
