import java.util.LinkedList;
import java.util.Queue;
import java.io.Serializable;

/**
 * Use Case 5: Booking Request (First-Come-First-Served)
 */

// Reservation class
class Reservation implements Serializable {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

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

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Booking request added.");
    }

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

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        bookingQueue.showRequests();

        System.out.println("=======================================");
        System.out.println("Requests stored (No allocation yet)");
        System.out.println("=======================================");
    }
}