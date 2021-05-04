package CodingInterviews.Flipkart.roundTwoMCRound.parking;

import CodingInterviews.Flipkart.roundTwoMCRound.gate.*;
import CodingInterviews.Flipkart.roundTwoMCRound.vehicle.BicycleParking;
import CodingInterviews.Flipkart.roundTwoMCRound.vehicle.BikeParking;
import CodingInterviews.Flipkart.roundTwoMCRound.vehicle.CarParking;
import CodingInterviews.Flipkart.roundTwoMCRound.vehicle.VehicleParkingType;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

public class Parking {
    private String parkingName;
    private CarParking carParking;
    private BikeParking bikeParking;
    private BicycleParking bicycleParking;

    private EntryGate entryGate;
    private ExitGate exitGate;
    private Map<String, Map<VehicleParkingType, Integer>> gateIdentifierVsVehicleStats;

    public Parking(String parkingName,
                   CarParking carParking,
                   BikeParking bikeParking,
                   BicycleParking bicycleParking,
                   EntryGate entryGate,
                   ExitGate exitGate) {
        this.parkingName = parkingName;
        this.carParking = carParking;
        this.bicycleParking = bicycleParking;
        this.bikeParking = bikeParking;
        this.entryGate = entryGate;
        this.exitGate = exitGate;
        gateIdentifierVsVehicleStats = new HashMap<>();
    }

    public boolean is_available(String parkingName, VehicleParkingType vehicle) {
        if (!this.parkingName.equals(parkingName)) {
            throw new UnsupportedOperationException();
        }
        
        switch (vehicle) {
            case CAR:
                return carParking.isAvailable();
            case BIKE:
                return bikeParking.isAvailable();
            case BICYCLE:
                return bicycleParking.isAvailable();
            default:
                throw new UnsupportedOperationException();
        }
    }

    public synchronized boolean park_vehicle(String parkingName, VehicleParkingType vehicle, String gateIdentifier) {
        if (!this.parkingName.equals(parkingName)) {
            throw new UnsupportedOperationException();
        }
        switch (vehicle) {
            case CAR:
                if (carParking.isAvailable()) {
                    carParking.decrementAvailableSlots();
                    updateVehicleInformation(gateIdentifier, VehicleParkingType.CAR, GateType.ENTRY);
                    return true;
                }
                break;
            case BIKE:
                if (bikeParking.isAvailable()) {
                    bikeParking.decrementAvailableSlots();
                    updateVehicleInformation(gateIdentifier, VehicleParkingType.BIKE, GateType.ENTRY);
                    return true;
                }
                break;
            case BICYCLE:
                if (bicycleParking.isAvailable()) {
                    bicycleParking.decrementAvailableSlots();
                    updateVehicleInformation(gateIdentifier, VehicleParkingType.BICYCLE, GateType.ENTRY);
                    return true;
                }
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return false;
    }

    public synchronized void unpark_vehicle(String parkingName, VehicleParkingType vehicle, String gateIdentifier) {
        if (!this.parkingName.equals(parkingName)) {
            throw new UnsupportedOperationException();
        }

        switch (vehicle) {
            case CAR:
                if (!carParking.allSlotsAvailable()) {
                    carParking.incrementAvailableSlots();
                    updateVehicleInformation(gateIdentifier, VehicleParkingType.CAR, GateType.EXIT);
                }
                break;
            case BIKE:
                if (!bikeParking.allSlotsAvailable()) {
                    bikeParking.incrementAvailableSlots();
                    updateVehicleInformation(gateIdentifier, VehicleParkingType.BIKE, GateType.EXIT);
                }
                break;
            case BICYCLE:
                if (!bicycleParking.allSlotsAvailable()) {
                    bicycleParking.incrementAvailableSlots();
                    updateVehicleInformation(gateIdentifier, VehicleParkingType.BICYCLE, GateType.EXIT);
                }
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void updateVehicleInformation(String gateIdentifier, VehicleParkingType vehicleParkingType, GateType gateType) {
        Map<VehicleParkingType, Integer> vehicleStats =
                gateIdentifierVsVehicleStats.computeIfAbsent(gateIdentifier, k -> new HashMap<>());
        if (gateIdentifierVsVehicleStats.containsKey(gateIdentifier)) {
            if (vehicleStats.containsKey(vehicleParkingType)) {
                int vehicleCount = vehicleStats.get(vehicleParkingType);
                vehicleStats.put(vehicleParkingType, vehicleCount + 1);
            } else {
                vehicleStats.put(vehicleParkingType, 1);
            }

        } else {
            vehicleStats.put(vehicleParkingType, 1);
        }
    }

    public void print_all_available_slots(String parkingName) {
        if (!this.parkingName.equals(parkingName)) {
            throw new UnsupportedOperationException();
        }
        System.out.println("Current Available slots in " + parkingName);
        System.out.println(VehicleParkingType.CAR.toString() + " :- " + carParking.getAvailableSlots());
        System.out.println(VehicleParkingType.BIKE.toString() + " :- " + bikeParking.getAvailableSlots());
        System.out.println(VehicleParkingType.BICYCLE.toString() + " :- " + bicycleParking.getAvailableSlots());
    }

    public void print_total_in(String parkingName, String gateIdentifier) {
        printVehicleStats(parkingName, gateIdentifier);
    }

    public void print_total_out(String parkingName, String gateIdentifier) {
        printVehicleStats(parkingName, gateIdentifier);
    }

    private void printVehicleStats(String parkingName, String gateIdentifier) {
        if (!this.parkingName.equals(parkingName)) {
            throw new UnsupportedOperationException();
        }
        System.out.println("Current Vehicle status in " + parkingName + ", from " + gateIdentifier);
        Map<VehicleParkingType, Integer> vehicleStats = gateIdentifierVsVehicleStats.get(gateIdentifier);
        if (vehicleStats == null) {
            for (VehicleParkingType type : VehicleParkingType.values()) {
                System.out.println(type.toString() + " :- " + 0);
            }
        } else {
            for (VehicleParkingType type : VehicleParkingType.values()) {
                System.out.println(type.toString() + " :- " + vehicleStats.getOrDefault(type, 0));
            }
        }
    }
}
