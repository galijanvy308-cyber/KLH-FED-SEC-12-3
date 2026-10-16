import java.io.*;
import java.util.*;
import java.util.function.Predicate;

// ==========================================
// MODULE 4: Abstraction & Interface
// ==========================================
interface Storable {
    void writeToFile(PrintWriter writer);
}

// ==========================================
// MODULE 4: Encapsulation & Inheritance Base Class
// ==========================================
abstract class LibraryItem implements Storable, Comparable<LibraryItem> {
    // Encapsulated private fields
    private String title;
    private String itemId;
    private double price;

    // Constructor using 'this' keyword
    public LibraryItem(String title, String itemId, double price) {
        this.title = title;
        this.itemId = itemId;
        this.price = price;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }

    // Abstract method for polymorphism
    public abstract void displayDetails();

    // Method overriding toString
    @Override
    public String toString() {
        return "ID: " + itemId + " | Title: " + title + " | Price: $" + price;
    }

    // Comparable implementation for sorting (Library Algorithms)
    @Override
    public int compareTo(LibraryItem other) {
        return this.title.compareTo(other.title);
    }
}

// ==========================================
// MODULE 4: Inheritance, 'super', & Method Overriding
// ==========================================
class Book extends LibraryItem {
    private String author;
    private int pageCount;

    public Book(String title, String itemId, double price, String author, int pageCount) {
        super(title, itemId, price); // 'super' keyword to invoke parent constructor
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // Method Overriding
    @Override
    public void displayDetails() {
        System.out.println("[Book] " + super.toString() + " | Author: " + author + " | Pages: " + pageCount);
    }

    @Override
    public void writeToFile(PrintWriter writer) {
        writer.println("BOOK," + getItemId() + "," + getTitle() + "," + getPrice() + "," + author + "," + pageCount);
    }
}

// ==========================================
// MODULE 6: Generic Repository Class
// ==========================================
class Repository<T extends LibraryItem> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(String itemId) {
        // MODULE 6: Iterating Collections with Iterator
        Iterator<T> iterator = items.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item.getItemId().equalsIgnoreCase(itemId)) {
                iterator.remove();
                System.out.println("Successfully removed item with ID: " + itemId);
                return;
            }
        }
        System.out.println("Item ID not found: " + itemId);
    }

    public List<T> getItems() {
        return items;
    }

    // MODULE 5: Library Algorithms (Sorting)
    public void sortItems() {
        Collections.sort(items);
    }

    // MODULE 6: Lambda Expressions with Predicate
    public List<T> filter(Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }
}

// ==========================================
// MAIN DRIVER CLASS WITH SCANNER & FILE I/O
// ==========================================
public class LibraryManagementSystem {
    private static final String FILE_NAME = "library_data.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Standard Scanner for Console I/O
        Repository<LibraryItem> repository = new Repository<>();

        // MODULE 6: Autoboxing & Unboxing demonstration
        List<Integer> inventoryStats = new ArrayList<>();
        inventoryStats.add(101); // Autoboxing int -> Integer
        int firstStat = inventoryStats.get(0); // Unboxing Integer -> int
        System.out.println("[System Init] Initializing Inventory Engine. Stat Code: " + firstStat);

        boolean running = true;
        while (running) {
            System.out.println("\n--- LIBRARY MANAGEMENT SYSTEM (Modules 4, 5, 6) ---");
            System.out.println("1. Add New Book");
            System.out.println("2. Display All Items (Polymorphic)");
            System.out.println("3. Sort Items Alphabetically");
            System.out.println("4. Filter Items by Max Price (Lambda)");
            System.out.println("5. Remove Item by ID (Iterator)");
            System.out.println("6. Save to File (File I/O)");
            System.out.println("7. Load from File (File I/O)");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            String choiceStr = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Item ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Page Count: ");
                    int pages = Integer.parseInt(scanner.nextLine());

                    Book newBook = new Book(title, id, price, author, pages);
                    repository.addItem(newBook);
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    System.out.println("\n--- Inventory Listing ---");
                    if (repository.getItems().isEmpty()) {
                        System.out.println("Repository is empty.");
                    } else {
                        // Polymorphic dispatch
                        for (LibraryItem item : repository.getItems()) {
                            item.displayDetails();
                        }
                    }
                    break;

                case 3:
                    repository.sortItems();
                    System.out.println("Items sorted alphabetically by title.");
                    break;

                case 4:
                    System.out.print("Enter maximum price threshold: ");
                    double maxPrice = Double.parseDouble(scanner.nextLine());
                    // MODULE 6: Lambda expression filtering
                    List<LibraryItem> affordable = repository.filter(item -> item.getPrice() <= maxPrice);
                    System.out.println("\n--- Affordable Items (<= $" + maxPrice + ") ---");
                    for (LibraryItem item : affordable) {
                        item.displayDetails();
                    }
                    break;

                case 5:
                    System.out.print("Enter Item ID to remove: ");
                    String removeId = scanner.nextLine();
                    repository.removeItem(removeId);
                    break;

                case 6:
                    // MODULE 5: File I/O Writing
                    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                        for (LibraryItem item : repository.getItems()) {
                            item.writeToFile(writer);
                        }
                        System.out.println("Data successfully saved to " + FILE_NAME);
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case 7:
                    // MODULE 5: File I/O Reading
                    File file = new File(FILE_NAME);
                    if (!file.exists()) {
                        System.out.println("No saved file found.");
                        break;
                    }
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");
                            if (parts[0].equals("BOOK")) {
                                Book b = new Book(parts[2], parts[1], Double.parseDouble(parts[3]), parts[4],
                                        Integer.parseInt(parts[5]));
                                repository.addItem(b);
                            }
                        }
                        System.out.println("Data successfully loaded from " + FILE_NAME);
                    } catch (IOException e) {
                        System.out.println("Error reading from file: " + e.getMessage());
                    }
                    break;

                case 8:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select between 1 and 8.");
            }
        }
        scanner.close();
    }
}