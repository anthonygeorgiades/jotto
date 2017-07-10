package buzzer;

import java.util.Scanner;
import java.util.stream.Stream;

public class Buzzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How high shall I count? ");
        int n = Integer.parseInt(scanner.next());
        for (int i = 1; i <= n; i++) {
            if (i % 7 == 0 || Integer.toString(i).contains("7")) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);

            }
        }

    }

}