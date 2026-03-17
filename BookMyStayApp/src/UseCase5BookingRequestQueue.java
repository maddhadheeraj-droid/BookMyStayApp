import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates handling booking requests using Queue (FIFO)
 * without modifying inventory.
 *
 * @author Maddha
 * @version 5.0
 */

// Reservation class (represents booking request)
class Reservation {
     String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    // ✅ Getter methods
    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    // ✅ ADD THIS METHOD (IMPORTANT)
    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}
// Booking Queue Manager
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request (enqueue)
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Booking request added.");
    }

    // Display all requests
    public void showRequests() {
        System.out.println("\n--- Booking Requests (FIFO Order) ---");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay App - v5.0");
        System.out.println("=======================================");

        // Create queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display queue
        bookingQueue.showRequests();

        System.out.println("=======================================");
        System.out.println("Requests stored (No allocation yet)");
        System.out.println("=======================================");
    }
}
