import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PasswordValidator {

    private final int min;
    private final int max;
    private final String substring;

    public PasswordValidator (int min, int max, String substring) {
        this.min = min;
        this.max = max;
        this.substring = substring;
    }

    public boolean isValidPassword (String password) {
        String empty = "";
        int originalLength = password.length();
        int shortLength = password.replace(substring, empty).length();
        int numberOfReplacements = originalLength - shortLength;
        return (min <= numberOfReplacements && numberOfReplacements <= max);
    }

    public boolean isValidPasswordPolicy2 (String password) {
        char char1 = password.charAt(min-1);
        char char2 = password.charAt(max-1);
        char checkChar = substring.charAt(0);
        if (char1 == char2) return false;
        if (char1 == checkChar || char2 == checkChar) return true;
        return false;
    }

    public static void main(String[] args) {
        BufferedReader reader;


        try {
            reader = new BufferedReader(new FileReader(
                    "input.txt"));
            String line = reader.readLine();
            int validCount = 0;
            int validCountPolicy2 = 0;
            while (line != null) {
                // example line: 5-6 k: dpxbkk

                String password = line.substring(line.indexOf(": ")+2);
                System.out.println("["+password+"]");

                int min = Integer.parseInt(line.substring(0, line.indexOf("-")));
                int max = Integer.parseInt(line.substring(line.indexOf("-")+1, line.indexOf(" ")));
                String validateString = line.substring(line.indexOf(" ")+1, line.indexOf(":"));
                PasswordValidator validator = new PasswordValidator(min,max,validateString);
                if (validator.isValidPassword(password)) {
                    validCount++;
                }

                if (validator.isValidPasswordPolicy2(password)) {
                    validCountPolicy2++;
                }

                line = reader.readLine();
            }
            reader.close();

            System.out.println ("Valid passwords for policy 1:"+validCount);
            System.out.println ("Valid passwords for policy 2:"+validCountPolicy2);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
