import java.util.ArrayList;
import java.util.Scanner;

public class SumofIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers separated by spaces:");
        String inputLine = scanner.nextLine();  
        String[] inputs = inputLine.split("\\s+"); 

        for (String s : inputs) {
            try {
                int num = Integer.parseInt(s); 
                numbers.add(num);             
            } catch (NumberFormatException e) {
                System.out.println("Invalid input skipped: " + s);
            }
        }

        int sum = 0;

        for (Integer n : numbers) {
            sum += n; 
        }

        System.out.println("\nNumbers Entered: " + numbers);
        System.out.println("Sum of Integers: " + sum);

        scanner.close();
    }
}
