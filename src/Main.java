import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        final byte MONTHS_IN_YEAR = 12;
        final  byte PERCENT = 100;

        int principal = 0;
        float monthlyInterestRate = 0;
        double numberOfPayments = 0;

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Principal($1K - $1M): ");
            principal = scanner.nextInt();
            if(principal >= 100_000 && principal <= 1_000_000)
                break;
            System.out.println("Principle should be between 100000 and 1000000");
        }

        while(true){
            System.out.print("Annual Interest Rate(1-30)%: ");
            float annualInterestRate = scanner.nextFloat();
            if(annualInterestRate > 0 && annualInterestRate <= 30){
                monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
                break;
            }
            System.out.println("Annual rate should be between 1 and 30");
        }

        while(true){
            System.out.print("Year(s)(1-20): ");
            byte years = scanner.nextByte();
            if(years > 0 && years <= 30){
                numberOfPayments = years * MONTHS_IN_YEAR;
                break;
            }
            System.out.println("Years should be between 1 and 30");
        }

        double mortgage = principal * (monthlyInterestRate * (Math.pow((1 + monthlyInterestRate),numberOfPayments))
                / (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));
        System.out.println("Mortgage: " + NumberFormat.getCurrencyInstance().format(mortgage));
    }
}
