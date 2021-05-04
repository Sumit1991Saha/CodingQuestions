package CodingInterviews.Flipkart.roundTwoMCRound;

import CodingInterviews.Flipkart.roundTwoMCRound.gate.EntryGate;
import CodingInterviews.Flipkart.roundTwoMCRound.gate.ExitGate;
import CodingInterviews.Flipkart.roundTwoMCRound.parking.Parking;
import CodingInterviews.Flipkart.roundTwoMCRound.vehicle.BicycleParking;
import CodingInterviews.Flipkart.roundTwoMCRound.vehicle.BikeParking;
import CodingInterviews.Flipkart.roundTwoMCRound.vehicle.CarParking;
import CodingInterviews.Flipkart.roundTwoMCRound.vehicle.VehicleParkingType;


import java.util.HashMap;

public class ParkingLotManagementSystem {


    public static void main(String[] args) {

        ParkingLotManagementSystem system = new ParkingLotManagementSystem();

        Parking parkingLot =
                system.add_parking_lot("PVR Koramangala",
                        2 , 1, 1,
                        4, 2);
        System.out.println(parkingLot.is_available("PVR Koramangala", VehicleParkingType.BIKE));
        System.out.println(parkingLot.is_available("PVR Koramangala", VehicleParkingType.CAR));
        System.out.println(parkingLot.is_available("PVR Koramangala", VehicleParkingType.BIKE));
        System.out.println(parkingLot.park_vehicle("PVR Koramangala", VehicleParkingType.CAR, "Entry Gate 1"));
        System.out.println(parkingLot.park_vehicle("PVR Koramangala", VehicleParkingType.BIKE, "Entry Gate 1"));
        System.out.println(parkingLot.park_vehicle("PVR Koramangala", VehicleParkingType.BIKE, "Entry Gate 1"));
        System.out.println(parkingLot.is_available("PVR Koramangala", VehicleParkingType.BIKE));

        System.out.println();
        parkingLot.print_all_available_slots("PVR Koramangala");
        parkingLot.print_total_in("PVR Koramangala", "Entry Gate 1");
        parkingLot.print_total_out("PVR Koramangala", "Exit Gate 1");
        parkingLot.unpark_vehicle("PVR Koramangala", VehicleParkingType.BIKE, "Exit Gate 1");
        parkingLot.print_total_out("PVR Koramangala", "Exit Gate 1");
        parkingLot.print_all_available_slots("PVR Koramangala");
    }

    private Parking add_parking_lot(String parkingLotName,
                                    int carSlots, int bikeSlots, int bicycleSlots,
                                    int entryGates, int existGates) {
        return new Parking(parkingLotName,
                new CarParking(carSlots),
                new BikeParking(bikeSlots),
                new BicycleParking(bicycleSlots),
                new EntryGate(entryGates),
                new ExitGate(existGates));
    }
}
