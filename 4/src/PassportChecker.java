import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PassportChecker {

    private List requiredFields;
    private List optionalFields;
    private Validators validators;

    public PassportChecker (List<String> requiredFields, List<String> optionalFields) {
        this.requiredFields = requiredFields;
        this.optionalFields = optionalFields;
        this.validators = new Validators();
    }

    public boolean isFieldRequired (String fieldName) {
        return this.requiredFields.contains(fieldName);
    }

    public boolean checkPassport(Map<String,String> passportFields) {
        System.out.println ("--------");
        int requiredCount = 0;
        Iterator<String> fieldsToCheck = requiredFields.iterator();
        while (fieldsToCheck.hasNext()) {
            String field = fieldsToCheck.next();
            String value = passportFields.get(field);


            if (value != null &&  this.validators.validate(field, value)) {
                requiredCount++;
            } else {
                System.out.println("NOT VALID Field:"+field+" Value:["+value+"]");
            }
        }
        return (requiredCount == requiredFields.size());
    }

    public static void main(String[] args) {
        BufferedReader reader;

        Validators validator = new Validators();

        Vector<String> requiredFields = new Vector<String>();
        requiredFields.add("byr");
        requiredFields.add("iyr");
        requiredFields.add("eyr");
        requiredFields.add("hgt");
        requiredFields.add("hcl");
        requiredFields.add("ecl");
        requiredFields.add("pid");

        Vector<String> optionalFields = new Vector<String>();
        optionalFields.add("cid");

        PassportChecker checker = new PassportChecker(requiredFields, optionalFields);

        try {
            reader = new BufferedReader(new FileReader(
                    "input.txt"));

            String line = reader.readLine();



            Vector<Map> passports = new Vector<Map>();

            HashMap<String,String> passport = new HashMap<String,String>();

            while (line != null) {
                if (!line.equals("")) {


                    String[] tokens = line.split(" ");

                    for (int i = 0; i < tokens.length; i++) {
                        String[] pair = tokens[i].split(":");
                        passport.put(pair[0],pair[1]);
                    }

                } else {
                    passports.add(passport);
                    passport = new HashMap<String,String>();
                }
                line = reader.readLine();
            }
            passports.add(passport);

            reader.close();

            Iterator<Map> passportIter = passports.iterator();
            int validPassports = 0;
            while (passportIter.hasNext()) {

                if (checker.checkPassport(passportIter.next())) {
                    validPassports++;
                }
            }
            System.out.println("Valid passports:"+validPassports);


        } catch (IOException exception) {
            System.out.println("Ex:" + exception);
        }

    }
}
