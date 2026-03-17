import java.util.*;

/**
 * Use Case 10: Booking Cancellation & Inventory Rollback
 */

// Cancellation Service
class CancellationService {

    // Stack to track released room IDs (LIFO)
    private Stack<String> rollbackStack = new Stack<>();

    // Map to track active reservations
    private Map<String, String> activeBookings = new HashMap<>();

    // Add booking (simulate confirmed booking)
    public void addBooking(String reservationId, String roomType) {
        activeBookings.put(reservationId, roomType);
    }

    // Cancel booking
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        System.out.println("\nCancelling Reservation ID: " + reservationId);

        // Validate booking exists
        if (!activeBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Invalid or already cancelled reservation.");
            return;
        }

        String roomType = activeBookings.get(reservationId);

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        int available = inventory.getAvailability(roomType);
        inventory.updateAvailability(roomType, available + 1);

        // Remove booking
        activeBookings.remove(reservationId);

        System.out.println("Cancellation Successful for " + reservationId +
                " | Room: " + roomType);
    }

    // Show rollback history
    public void showRollbackStack() {
        System.out.println("\nRollback Stack (Recent First): " + rollbackStack);
    }
}

// Main class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay App - v10.0");
        System.out.println("=======================================");

        RoomInventory inventory = new RoomInventory();

        // Cancellation service
        CancellationService service = new CancellationService();

        // Simulate bookings (from UC6)
        service.addBooking("SI1", "Single Room");
        service.addBooking("DO2", "Double Room");
        service.addBooking("SU3", "Suite Room");

        // Cancel bookings
        service.cancelBooking("DO2", inventory);   // valid
        service.cancelBooking("XX9", inventory);   // invalid
        service.cancelBooking("SI1", inventory);   // valid

        // Show rollback history
        service.showRollbackStack();

        System.out.println("=======================================");
        System.out.println("Cancellation Process Completed");
        System.out.println("=======================================");
    }
}
