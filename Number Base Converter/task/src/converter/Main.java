package converter;

import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            String input = sc.next();
            if (input.equals("/exit")) {
                break;
            }
            int sourceBase = Integer.parseInt(input);
            int targetBase = sc.nextInt();
            while (true) {
                System.out.println("Enter number in base " + sourceBase + " to convert to base " + targetBase + " (To go back type /back) ");
                String number = sc.next();
                if (number.equals("/back")) {
                    break;
                }
                String integerPart = number.split("\\.")[0];
                if (number.split("\\.").length > 1) {
                    String decimalPart = number.split("\\.")[1];
                    var intPart = convertInteger(integerPart, sourceBase, targetBase);
                    var fractionalPart = convertDecimal(decimalPart, sourceBase, targetBase);
                    System.out.println("Conversion result: " + intPart + "." + fractionalPart);
                } else {
                    var intPart = convertInteger(integerPart, sourceBase, targetBase);
                    System.out.println("Conversion result: " + intPart);
                }
            }
        }
    }

    public static String convertInteger(String theValue, int initialBase, int finalBase) {
        BigInteger bigInteger = new BigInteger(theValue, initialBase);
        String value = bigInteger.toString(finalBase);
        value = value.toLowerCase();
        return value;
    }

    public static String convertDecimal(String theValue, int initialBase, int finalBase) {
        if (theValue.length() > 5) {
            theValue = theValue.substring(0, 5);
        } else {
            theValue = theValue + "00000".substring(0, 5 - theValue.length());
        }
        double result = 0;
        for (int i = 0; i < 5; i++) {
            char c = theValue.charAt(i);
            int digit = 0;
            if (48 <= c && c <= 57) {
                digit = c - '0';
            } else {
                digit = c - 'a' + 10;
            }
            result = result + (digit / (Math.pow(initialBase, i + 1)));
        }
        String finalres = "";
        int k = 0;
        for (int i = 1; result > 0; i++) {
            result = result * finalBase;
            if ((int) result > 9) {
                finalres = finalres + (char) ((int) result + 'a' - 10);
            } else {
                finalres = finalres + (char) ((int) result + '0');
            }
            result = result - (int) result;
            k = i;
            if (k > 5) {
                break;
            }
        }
        if (finalres.length() > 5) {
            finalres = finalres.substring(0, 5);
        } else {
            finalres = finalres + "00000".substring(0, 5 - finalres.length());
        }
        return finalres;
    }
}