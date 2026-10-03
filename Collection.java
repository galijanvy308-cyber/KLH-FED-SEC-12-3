import java.util.Scanner;

class Collection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Waste Collected:");
        int waste_collected = sc.nextInt();

        if (waste_collected > 100) {
            System.out.println(" Collection Target Achieved");
        } else {
            System.out.println("More Waste Collection Required");
        }
        sc.close();

    }

}
