import java.util.*;

/**
 * Use Case 9: Error Handling & Validation
 */

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator Class
class BookingValidator {

    private static final Set<String> validRoomTypes =
            new HashSet<>(Arrays.asList("Single Room", "Double Room", "Suite Room"));

    public static void validate(String roomType, RoomInventory inventory) throws InvalidBookingException {

        // Check valid room type
        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        // Check availability
        int available = inventory.getAvailability(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }
}

// Main class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay App - v9.0");
        System.out.println("=======================================");

        RoomInventory inventory = new RoomInventory();

        // Test cases
        String[] testRooms = {
                "Single Room",     // valid
                "Luxury Room",     // invalid type
                "Suite Room"       // may become unavailable
        };

        for (String roomType : testRooms) {

            try {
                System.out.println("\nRequesting: " + roomType);

                // Validate input
                BookingValidator.validate(roomType, inventory);

                // If valid → simulate booking
                int available = inventory.getAvailability(roomType);
                inventory.updateAvailability(roomType, available - 1);

                System.out.println("Booking Successful for: " + roomType);

            } catch (InvalidBookingException e) {
                // Handle error gracefully
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }

        System.out.println("=======================================");
        System.out.println("Validation Completed");
        System.out.println("=======================================");
    }
}
