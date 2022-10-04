import java.text.NumberFormat;
import java.util.Scanner;
public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;
    public static void main(String[] args){
        int principal = (int) readValue("Principal: ", 100_000, 1_000_000);
        float annualInterestRate = (float) readValue("Annual Interest Rate: ", 1, 30);
        byte years = (byte) readValue("Period (years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterestRate, years);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for(short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal, annualInterestRate, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
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

    public static double calculateBalance(
            int principal,
            float annualInterestRate,
            byte years,
            short numberOfPaymentsMade
    ){
        float numberOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        return principal * (Math.pow(1 + monthlyInterestRate, numberOfPayments) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
    }

    public static double calculateMortgage(
        int principal,
        float annualInterestRate,
        byte years
    ){
        float numberOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;

        return principal * (monthlyInterestRate * (Math.pow((1 + monthlyInterestRate),numberOfPayments))
                / (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));
    }
}
