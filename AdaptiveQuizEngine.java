import java.util.Scanner;

public class AdaptiveQuizEngine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // [MODULE 1: Console I/O Scanner]

        // [MODULE 3: 1D Arrays - Questions, Options, and Correct Answer Keys]
        String[] questions = {
                "Q1: What is the size of an int data type in Java?",
                "Q2: Which operator is used for bitwise AND in Java?",
                "Q3: What is the default value of an uninitialized local variable?"
        };

        String[] optionsQ1 = { "1. 2 Bytes", "2. 4 Bytes", "3. 8 Bytes", "4. Depends on OS" };
        String[] optionsQ2 = { "1. &&", "2. &", "3. |", "4. ^" };
        String[] optionsQ3 = { "1. 0", "2. null", "3. Garbage value", "4. No default value (Compile Error)" };

        int[] correctAnswers = { 2, 2, 4 }; // [MODULE 3: 1D Array of Correct Option Keys]
        int totalQuestions = questions.length;
        int score = 0; // [MODULE 1: Primitive Variables]

        // [MODULE 3: Method Calling]
        printHeader("JAVA FOUNDATIONS ADAPTIVE QUIZ ENGINE");

        // [MODULE 2: Iteration - For Loop through Questions]
        for (int i = 0; i < totalQuestions; i++) {
            System.out.printf("%n[Question %d of %d]%n", (i + 1), totalQuestions); // [MODULE 1: Formatted Output]

            // Select corresponding 1D options array
            String[] currentOpts = (i == 0) ? optionsQ1 : (i == 1) ? optionsQ2 : optionsQ3;

            // [MODULE 3: Method Calling]
            displayQuestion(questions[i], currentOpts);

            System.out.print("Enter your answer (1-4): ");

            // [MODULE 3: Method Calling & MODULE 2: Input Validation Control Loop]
            int userChoice = readValidInput(scanner);

            // [MODULE 3: Method Calling & MODULE 1: Relational Operator Evaluation]
            boolean isCorrect = evaluateAnswer(userChoice, correctAnswers[i]);

            // [MODULE 2: Selection Control Flow - if-else]
            if (isCorrect) {
                System.out.println("Result: Correct!");
                score++; // [MODULE 1: Unary Increment Operator]
            } else {
                System.out.printf("Result: Incorrect! Correct answer was option %d.%n", correctAnswers[i]);
            }
        }

        // [MODULE 3: Method Calling for Final Performance Calculation]
        displayPerformanceSummary(score, totalQuestions);

        scanner.close(); // Clean resource release
    }

    // [MODULE 3: Creating Methods - Display Question & Options]
    public static void displayQuestion(String question, String[] opts) {
        System.out.println(question);
        for (String option : opts) { // [MODULE 2: For-each Loop]
            System.out.println("  " + option);
        }
    }

    // [MODULE 3: Creating Methods & MODULE 2: While Loop with Input Validation]
    public static int readValidInput(Scanner scanner) {
        int input = -1;
        while (true) {
            if (scanner.hasNextInt()) { // [MODULE 2: Conditional Check]
                input = scanner.nextInt();
                if (input >= 1 && input <= 4) { // [MODULE 1: Logical AND Operator]
                    break; // [MODULE 2: Break Statement]
                }
            } else {
                scanner.next(); // Clear invalid token
            }
            System.out.print("Invalid choice. Please enter a number between 1 and 4: ");
        }
        return input;
    }

    // [MODULE 3: Creating Methods & MODULE 1: Relational Operator Evaluation]
    public static boolean evaluateAnswer(int userChoice, int correctAnswer) {
        return userChoice == correctAnswer; // Returns boolean calculation
    }

    // [MODULE 3: Creating Methods & MODULE 1: Formatted Console Output]
    public static void printHeader(String title) {
        System.out.println("==================================================");
        System.out.printf("   %s%n", title); // [MODULE 1: Formatted String Printing]
        System.out.println("==================================================");
    }

    // [MODULE 3: Creating Methods, MODULE 1: Explicit Typecasting, MODULE 2:
    // If-Else-If Ladder]
    public static void displayPerformanceSummary(int score, int total) {
        // [MODULE 1: Explicit Double Typecasting & Expression Evaluation]
        double percentage = ((double) score / total) * 100.0;

        System.out.println("\n--------------------------------------------------");
        System.out.println("                 EXAM RESULTS                     ");
        System.out.println("--------------------------------------------------");
        System.out.printf("Total Questions : %d%n", total);
        System.out.printf("Correct Answers : %d%n", score);
        System.out.printf("Final Score     : %.2f%%%n", percentage); // [MODULE 1: Formatted Float Output]

        // [MODULE 2: Nested Conditional Ladder for Grade Determination]
        System.out.print("Grade Status    : ");
        if (percentage >= 85.0) {
            System.out.println("EXCELLENT (Grade A)");
        } else if (percentage >= 60.0) {
            System.out.println("GOOD (Grade B)");
        } else if (percentage >= 40.0) {
            System.out.println("PASS (Grade C)");
        } else {
            System.out.println("NEEDS IMPROVEMENT (Fail)");
        }
        System.out.println("--------------------------------------------------");

    }
}