import java.util.*;

/**
 * Use Case 7: Add-On Service Selection
 *
 * Attach optional services to reservations
 * without modifying booking or inventory.
 */

// Add-On Service class
class AddOnService {
    String serviceName;
    double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public void display() {
        System.out.println(serviceName + " - ₹" + cost);
    }
}

// Service Manager
class AddOnServiceManager {

    // Map: Reservation ID → List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Service added to Reservation ID: " + reservationId);
    }

    // Display services
    public void showServices(String reservationId) {

        System.out.println("\nServices for Reservation ID: " + reservationId);

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            s.display();
        }
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.cost;
            }
        }

        return total;
    }
}

// Main class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay App - v7.0");
        System.out.println("=======================================");

        // Example reservation ID (from UC6)
        String reservationId = "SI1";

        // Create service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1000));
        manager.addService(reservationId, new AddOnService("Extra Bed", 800));

        // Display services
        manager.showServices(reservationId);

        // Total cost
        double total = manager.calculateTotalCost(reservationId);
        System.out.println("\nTotal Add-On Cost: ₹" + total);

        System.out.println("=======================================");
        System.out.println("Add-On Processing Completed");
        System.out.println("=======================================");
    }
}
