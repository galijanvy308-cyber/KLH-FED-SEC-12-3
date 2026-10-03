import java.util.Scanner;

class Waste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vehicle number:");
        int vehicle_number = sc.nextInt();
        System.out.println("Enter Waste collected:");
        float waste_collected = sc.nextFloat();
        System.out.println("Number of collection points:");
        int collection_points = sc.nextInt();
        System.out.println("Vehicle status:");
        char vehicle_status = sc.next().charAt(0);

        System.out.println("Vehicle number:" + vehicle_number);
        System.out.println("Waste Collected:" + waste_collected);
        System.out.println("Collection Points:" + collection_points);
        System.out.println("Vehicle Status:" + vehicle_status);

        sc.close();

    }

}