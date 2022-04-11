package util;
import java.util.Scanner;

public class Input {

    private Scanner sc = new Scanner(System.in);

    public String getString() {
        return sc.nextLine();
    }

    public String getStringCapitalized() {
        String input = sc.nextLine();
        return capitalizeString(input);
    }

    public String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public boolean yesNo() {
//        System.out.println("yes or no?");
        String userInput = sc.next();
        return userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes");
    }

    public int getInt(int min, int max) {
//        System.out.println("Enter a number between " + min + " - " + max + ": ");
        int userInput = getInt();
        if (userInput > max || userInput < min) {
            return 0;
        } else {
            return userInput;
        }
    }


    public int getInt() {
//        System.out.println("Enter a number: ");
        try {
            return sc.nextInt();
        } catch (Exception e) {
            System.out.println("Please enter a valid number");
            sc.nextLine();
            return getInt();
        }
    }


    public long getLong() {
//        System.out.println("Enter a number: ");
        try {
            return sc.nextLong();
        } catch (Exception e) {
            System.out.println("Please enter a valid number");
            sc.nextLine();
            return getLong();
        }
    }


    public int getIntEX() {
        System.out.println("Enter a number: ");
        try {
            return Integer.valueOf(getString());
        } catch (NumberFormatException ru) {
            System.out.println("This is not a integer fool!!" );
        }
        return getIntEX();
    }



    public double getDouble(double min, double max) {
        System.out.println("Enter a decimal number between " + min + " - " + max + ": ");
        double userInput = sc.nextDouble();
        if (userInput > max || userInput < min) {
            return getDouble(min, max);
        } else {
            return userInput;
        }
    }

    public double getDouble() {
//        System.out.println("Enter a decimal number: ");
        return sc.nextDouble();
    }

    public double getDoubleEX() {
        System.out.println("Enter a decimal number: ");
        try {
            return Double.valueOf(getString());
        } catch (NumberFormatException ru) {
            System.out.println("This is not a decimal fool!!" );
        }
        return getDoubleEX();
    }


    public int getBinary() {
        System.out.println("Enter a binary number: ");
        try {
            return Integer.valueOf(getString(), 2);
        } catch (NumberFormatException ru) {
            System.out.println("This is not a integer fool!!" );
        }
        return getBinary();
    }


    public int getHex() {
        System.out.println("Enter a hexidecimal number: ");
        return Integer.valueOf(getString(), 16);
    }

//    public long getHex() {
//        System.out.println("Enter a hexidecimal number: ");
//        return Long.parseLong(getString(), 16);
//    }


}
