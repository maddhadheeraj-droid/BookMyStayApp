import java.util.ArrayList;
import java.util.List;

/**
 * Use Case 4: Room Search & Availability Check
 * Read-only access without modifying inventory
 */

class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory, List<Room> rooms) {

        System.out.println("\n--- Available Rooms ---");

        for (Room room : rooms) {

            // 🔥 Use room.roomType directly (no getter needed)
            int available = inventory.getAvailability(room.roomType);

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println();
            }
        }
    }
}

// Main class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay App - v4.0");
        System.out.println("=======================================");

        // Inventory (UC3)
        RoomInventory inventory = new RoomInventory();

        // Room objects (UC2)
        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());

        // Search
        RoomSearchService service = new RoomSearchService();
        service.searchAvailableRooms(inventory, rooms);

        System.out.println("=======================================");
        System.out.println("Search Completed Successfully");
        System.out.println("=======================================");
    }
}