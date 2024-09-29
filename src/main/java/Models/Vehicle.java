package Models;

public class Vehicle {
    private final Constants.VehicleType type;
    private final String registrationNumber;

    public Vehicle(Constants.VehicleType type, String registrationNumber) {
        this.type = type;
        this.registrationNumber = registrationNumber;
    }

    public Constants.VehicleType getType() {
        return type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
