import java.util.*;

/**
 * Use Case 8: Booking History & Reporting
 *
 * Stores confirmed bookings and generates reports.
 */

// Booking History (stores confirmed reservations)
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add booking to history
    public void addBooking(Reservation reservation) {
        history.add(reservation);
        System.out.println("Booking added to history.");
    }

    // Get all bookings
    public List<Reservation> getAllBookings() {
        return history;
    }
}

// Report Service
class BookingReportService {

    public void generateReport(List<Reservation> bookings) {

        System.out.println("\n--- Booking Report ---");

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Reservation r : bookings) {
            r.display();  // uses display() from UC5
        }

        System.out.println("\nTotal Bookings: " + bookings.size());
    }
}

// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay App - v8.0");
        System.out.println("=======================================");

        // Booking history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addBooking(new Reservation("Alice", "Single Room"));
        history.addBooking(new Reservation("Bob", "Double Room"));
        history.addBooking(new Reservation("Charlie", "Suite Room"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getAllBookings());

        System.out.println("=======================================");
        System.out.println("Report Generated Successfully");
        System.out.println("=======================================");
    }
}
