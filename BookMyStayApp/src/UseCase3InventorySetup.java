import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates how to manage room availability using a centralized HashMap.
 * This ensures consistency, scalability, and efficient access.
 *
 * @author Maddha
 * @version 3.1
 */

// Inventory class
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Main class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay App - v3.1");
        System.out.println("=======================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        // Example: Update availability
        System.out.println("\nUpdating Single Room availability...");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("=======================================");
        System.out.println("Application Ended");
        System.out.println("=======================================");
    }
}
