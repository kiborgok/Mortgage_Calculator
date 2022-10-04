import java.text.NumberFormat;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        int principal = (int) readValue("Principal: ", 100_000, 1_000_000);
        float annualInterestRate = (float) readValue("Annual Interest Rate: ", 1, 30);
        byte years = (byte) readValue("Period (years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterestRate, years);
        System.out.println("Mortgage: " + NumberFormat.getCurrencyInstance().format(mortgage));
    }

    public static double readValue(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while(true){
            System.out.print(prompt);
            value = scanner.nextFloat();
            if(value >= min && value <= max)
                break;
            System.out.println("Should be between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateMortgage(
            int principal,
            float annualInterestRate,
            byte years){

        final byte MONTHS_IN_YEAR = 12;
        final  byte PERCENT = 100;

        float numberOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        return principal * (monthlyInterestRate * (Math.pow((1 + monthlyInterestRate),numberOfPayments))
                / (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));
    }
}
