import java.util.Scanner;

class MovieTicket {
    String movieName;
    double ticketPrice;
    int numberOfTickets;

    MovieTicket(String movieName, double ticketPrice, int numberOfTickets) {
        this.movieName = movieName;
        this.ticketPrice = ticketPrice;
        this.numberOfTickets = numberOfTickets;
    }

    public double calculateTotalPrice() {
        return ticketPrice * numberOfTickets;
    }

    public double calculateDiscountAmount(double discountPercentage) {
        double totalPrice = calculateTotalPrice();

        if (numberOfTickets >= 5) {
            return totalPrice * discountPercentage / 100;
        }

        return 0;
    }

    public double calculateFinalAmount(double discountPercentage) {
        double discountAmount = calculateDiscountAmount(discountPercentage);
        return calculateTotalPrice() - discountAmount;
    }

    // Display bill
    void displayBill() {
        double discountPercentage = 10;

        System.out.println("Movie Name: " + movieName);
        System.out.println("Ticket Price: $" + ticketPrice);
        System.out.println("Number of Tickets: " + numberOfTickets);
        System.out.println("Total Price: $" + calculateTotalPrice());
        System.out.println("Discount Amount: $"
                + calculateDiscountAmount(discountPercentage));
        System.out.println("Final Amount to Pay: $"
                + calculateFinalAmount(discountPercentage));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the movie name: ");
        String movieName = sc.nextLine();

        System.out.print("Enter the ticket price: ");
        double ticketPrice = sc.nextDouble();

        System.out.print("Enter the number of tickets: ");
        int numberOfTickets = sc.nextInt();

        MovieTicket T = new MovieTicket(movieName, ticketPrice, numberOfTickets);

        T.displayBill();

        sc.close();
    }
}