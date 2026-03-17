import java.util.*;

class ConcurrentBookingProcessor {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    public ConcurrentBookingProcessor(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    // synchronized method (critical section)
    public synchronized void processBooking() {

        if (queue.isEmpty()) return;

        Reservation request = queue.poll();

        if (request == null) return;

        String roomType = request.roomType;
        String guestName = request.guestName;

        int available = inventory.getAvailability(roomType);

        if (available > 0) {
            inventory.updateAvailability(roomType, available - 1);

            System.out.println(Thread.currentThread().getName() +
                    " → Booking Confirmed: " + guestName +
                    " | Room: " + roomType);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " → Booking Failed: " + guestName +
                    " | Room: " + roomType);
        }
    }
}

class BookingThread extends Thread {

    private ConcurrentBookingProcessor processor;

    public BookingThread(ConcurrentBookingProcessor processor, String name) {
        super(name);
        this.processor = processor;
    }

    public void run() {
        for (int i = 0; i < 2; i++) {
            processor.processBooking();
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room"));
        queue.add(new Reservation("David", "Single Room"));

        ConcurrentBookingProcessor processor =
                new ConcurrentBookingProcessor(queue, inventory);

        Thread t1 = new BookingThread(processor, "Thread-1");
        Thread t2 = new BookingThread(processor, "Thread-2");
        Thread t3 = new BookingThread(processor, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}