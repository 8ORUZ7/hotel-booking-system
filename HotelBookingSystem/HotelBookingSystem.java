import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.PrinterName;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

// Main Class
public class HotelBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Room> rooms = initializeRooms();
        List<Customer> customers = new ArrayList<>();

        System.out.println("=== Welcome to the Hotel Booking System ===");

        // Admin authentication
        System.out.print("Enter Admin Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (Admin.authenticate(email, password)) {
            System.out.println("Login successful.");
            boolean running = true;

            while (running) {
                BookingManager.displayAvailability(rooms);
                BookingManager.displayMenu();

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> BookingManager.bookRoom(rooms, customers, scanner);
                    case 2 -> BookingManager.updateCustomerDetails(customers, scanner);
                    case 3 -> BookingManager.cancelBooking(rooms, customers, scanner);
                    case 4 -> {
                        System.out.println("Thank you for using the Hotel Booking System!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
        scanner.close();
    }

    // Initializes room data
    private static List<Room> initializeRooms() {
        return List.of(
                new Room(1, "Single", true),
                new Room(2, "Double", true),
                new Room(3, "Suite", true));
    }
}

// Admin Class
class Admin {
    private static final String ADMIN_EMAIL = "admin@example.com";
    private static final String ADMIN_PASSWORD = "password123";

    public static boolean authenticate(String email, String password) {
        return ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password);
    }
}

// Room Class
class Room {
    private final int id;
    private final String type;
    private boolean available;

    public Room(int id, String type, boolean available) {
        this.id = id;
        this.type = type;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// Customer Class
class Customer {
    private static int counter = 1;
    private final int id;
    private String name;
    private String contact;
    private final int roomId;

    public Customer(String name, String contact, int roomId) {
        this.id = counter++;
        this.name = name;
        this.contact = contact;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

// Booking Manager Class
class BookingManager {
    public static void displayAvailability(List<Room> rooms) {
        System.out.println("\n--- Room Availability ---");
        System.out.printf("%-10s %-15s %-10s\n", "Room ID", "Type", "Available");
        for (Room room : rooms) {
            System.out.printf("%-10d %-15s %-10s\n", room.getId(), room.getType(), room.isAvailable() ? "Yes" : "No");
        }
    }

    public static void displayMenu() {
        System.out.println("\nOptions:");
        System.out.println("1. Room Booking");
        System.out.println("2. Update Customer Details");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Exit");
    }

    public static void bookRoom(List<Room> rooms, List<Customer> customers, Scanner scanner) {
        System.out.print("Enter Room ID to book: ");
        int roomId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room room = findRoomById(rooms, roomId);
        if (room != null && room.isAvailable()) {
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Customer Contact: ");
            String contact = scanner.nextLine();

            Customer customer = new Customer(name, contact, roomId);
            customers.add(customer);
            room.setAvailable(false);

            System.out.println("Booking successful!");
            System.out.print("Print receipt? (yes/no): ");
            String printReceipt = scanner.nextLine();
            if (printReceipt.equalsIgnoreCase("yes")) {
                ReceiptPrinter.printReceipt(customer, room);
            }
        } else {
            System.out.println("Room not available or invalid Room ID.");
        }
    }

    public static void updateCustomerDetails(List<Customer> customers, Scanner scanner) {
        System.out.print("Enter Customer ID to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Customer customer = findCustomerById(customers, customerId);
        if (customer != null) {
            System.out.print("Enter new Customer Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Customer Contact: ");
            String contact = scanner.nextLine();

            customer.setName(name);
            customer.setContact(contact);
            System.out.println("Customer details updated.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public static void cancelBooking(List<Room> rooms, List<Customer> customers, Scanner scanner) {
        System.out.print("Enter Customer ID to cancel booking: ");
        int customerId = scanner.nextInt();

        Customer customer = findCustomerById(customers, customerId);
        if (customer != null) {
            Room room = findRoomById(rooms, customer.getRoomId());
            if (room != null) {
                room.setAvailable(true);
            }
            customers.remove(customer);
            System.out.println("Booking cancelled.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static Room findRoomById(List<Room> rooms, int roomId) {
        return rooms.stream()
                .filter(room -> room.getId() == roomId)
                .findFirst()
                .orElse(null);
    }

    private static Customer findCustomerById(List<Customer> customers, int customerId) {
        return customers.stream()
                .filter(customer -> customer.getId() == customerId)
                .findFirst()
                .orElse(null);
    }
}

class ReceiptPrinter {
    public static void printReceipt(Customer customer, Room room) {
        String receiptContent = generateReceiptContent(customer, room);
        System.out.println("\n--- Receipt ---");
        System.out.println(receiptContent);
        System.out.println("--------------------");

        // Sending the receipt to a printer
        try {
            printToPhysicalPrinter(receiptContent);
        } catch (Exception e) {
            System.out.println("Failed to print receipt: " + e.getMessage());
        }
    }

    private static String generateReceiptContent(Customer customer, Room room) {
        return "Customer ID: " + customer.getId() + "\n"
                + "Customer Name: " + customer.getName() + "\n"
                + "Contact: " + customer.getContact() + "\n"
                + "Room ID: " + room.getId() + "\n"
                + "Room Type: " + room.getType() + "\n";
    }

    private static void printToPhysicalPrinter(String content) throws Exception {
        // Convert the content into an InputStream
        InputStream textStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));

        // Define a DocFlavor for the data type (plain text)
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        // Locate the default printer or specify a printer by name
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultService == null) {
            throw new Exception("No printer found.");
        }

        // Create a DocPrintJob
        DocPrintJob printJob = defaultService.createPrintJob();

        // Create a Doc object for printing
        Doc doc = new SimpleDoc(textStream, flavor, null);

        // Print the document
        printJob.print(doc, null);

        System.out.println("Receipt sent to printer: " + defaultService.getName());
    }
}
