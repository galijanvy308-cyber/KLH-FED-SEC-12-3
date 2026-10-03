import java.util.Scanner;

public class Total {
    static double calculateTotalWaste(double point1Waste, double point2Waste) {
        return point1Waste + point2Waste;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter waste collected from point1Waste:");
        double point_1_Waste = sc.nextDouble();
        System.out.println("Enter waste collected from point2Waste:");
        double point_2_Waste = sc.nextDouble();

        double total_waste = calculateTotalWaste(point_1_Waste, point_2_Waste);

        System.out.println("Total Waste:" + total_waste);

        sc.close();
    }
}
