import java.util.*;

class RoomAllocationService {

    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private int roomCounter = 1;

    public void processBookings(Queue<Reservation> queue, RoomInventory inventory) {

        while (!queue.isEmpty()) {

            Reservation request = queue.poll();

            String roomType = request.getRoomType();
            String guestName = request.getGuestName();

            int available = inventory.getAvailability(roomType);

            if (available > 0) {

                String roomId = roomType.substring(0, 2).toUpperCase() + roomCounter++;

                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                allocatedRooms.get(roomType).add(roomId);

                inventory.updateAvailability(roomType, available - 1);

                System.out.println("Confirmed: " + guestName + " | " + roomType + " | ID: " + roomId);

            } else {
                System.out.println("Failed: " + guestName + " | " + roomType);
            }
        }
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Double Room"));
        queue.add(new Reservation("Charlie", "Suite Room"));

        new RoomAllocationService().processBookings(queue, inventory);
    }
}