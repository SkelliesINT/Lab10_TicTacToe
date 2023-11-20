import java.util.Scanner;
public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        /*
         *
         * @param pipe a Scanner opened to read from System.in
         * @param prompt for the user
         * @return a String response that is not zero length
         */
        String retString;  // Set this to zero length. Loop runs until it isn’t
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }
        while (retString.isEmpty());
        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int result = 0;
        String trash;
        boolean validInput = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                validInput = true;
                result = pipe.nextInt();
            } else {
                trash = pipe.next();
                System.out.print("You entered: " + trash);
                pipe.nextLine();
            }
        }
        while (!validInput);
        return result;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double result = 0.0;
        boolean validInput = false;
        String trash;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                validInput = true;
                result = pipe.nextDouble();
            } else {
                trash = pipe.next();
                System.out.print("You entered: " + trash);
                pipe.nextLine();
            }
        }
        while (!validInput);
        return result;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int result = 0;
        String trash;
        boolean validInput = false;
        do
        {
            System.out.print(prompt + "[" + low + "-" + high + "]: ");
            if (pipe.hasNextInt())
            {
                result = pipe.nextInt();
                if(result >= low && result <= high){
                    validInput = true;
                }
                else{
                    System.out.print(result + " Is not within range. Please enter again!");
                }

            }
            else
            {
                trash = pipe.next();
                System.out.print("You entered: " + trash);
                pipe.nextLine();
            }
        }
        while (!validInput);
        return result;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double result = 0;
        String trash;
        boolean validInput = false;
        do
        {
            System.out.print(prompt + "[" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble())
            {
                validInput = true;
                result = pipe.nextDouble();
            }
            else
            {
                trash = pipe.next();
                System.out.print("You entered: " + trash);
                pipe.nextLine();
            }
        }
        while (!validInput);
        return result;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input;

        do {
            System.out.print("\n" + prompt + " [Y/N] ");
            input = pipe.next().toUpperCase();

            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }

        } while (true);
    }
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern)
    {
        String result;
        boolean validInput = false;
        do {
            System.out.print(prompt + ": ");
            result = pipe.nextLine();
            if(result.matches(regExPattern)) {
                validInput = true;
            }
            else
            {
                System.out.println("\n" + "Invalid input: " + result);
            }
        }while(!validInput);

        return result;
    }
    public static void prettyHeader(String msg)
    {
        for (int i = 0; i <= 60; i++)
        {
            System.out.print("*");
        }
        System.out.println();
        int textLen = msg.length();
        int fullLen = 54 - textLen;
        int spaceLen = fullLen / 2;
        System.out.print("***");
        for (int i = 0; i <= spaceLen; i++)
        {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i <= spaceLen; i++)
        {
            System.out.print(" ");
        }
        System.out.print("***");
        System.out.println();
        for (int i = 0; i <= 60; i++)
        {
            System.out.print("*");
        }

    }

}