package CodingInterviews.Flipkart.roundTwoMCRound.vehicle;

import java.util.concurrent.atomic.AtomicInteger;

public class VehicleParking {
    private final int maxSlots;
    private AtomicInteger availableSlots;
    private VehicleParkingType type;

    public VehicleParking(int maxSlots, VehicleParkingType type) {
        this.maxSlots = maxSlots;
        this.availableSlots = new AtomicInteger(maxSlots);
        this.type = type;
    }

    public int getAvailableSlots() {
        return availableSlots.get();
    }

    public void incrementAvailableSlots() {
        this.availableSlots.addAndGet(1);
    }

    public void decrementAvailableSlots() {
        this.availableSlots.decrementAndGet();
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public boolean isAvailable() {
        return availableSlots.get() > 0;
    }

    public boolean allSlotsAvailable() {
        return availableSlots.get() == maxSlots;
    }
}
