import java.io.*;
import java.util.*;

class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    public void save(RoomInventory inventory, List<Reservation> history) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            oos.writeObject(history);

            System.out.println("Data saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public Object[] load() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            RoomInventory inventory = (RoomInventory) ois.readObject();
            List<Reservation> history = (List<Reservation>) ois.readObject();

            System.out.println("Data loaded successfully.");
            return new Object[]{inventory, history};

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        RoomInventory inventory;
        List<Reservation> history;

        PersistenceService service = new PersistenceService();

        Object[] data = service.load();

        if (data != null) {
            inventory = (RoomInventory) data[0];
            history = (List<Reservation>) data[1];
        } else {
            inventory = new RoomInventory();
            history = new ArrayList<>();
        }

        // Simulate booking
        Reservation r = new Reservation("Alice", "Single Room");
        history.add(r);

        int available = inventory.getAvailability("Single Room");
        inventory.updateAvailability("Single Room", available - 1);

        // Display
        inventory.displayInventory();

        for (Reservation res : history) {
            res.display();
        }

        // Save state
        service.save(inventory, history);
    }
}