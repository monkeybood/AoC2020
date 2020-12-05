import java.util.HashMap;

public class Validators {


    private String hairRegex = "^#[0-9a-f]{6,6}";
    private String eyesRegex = "^(amb|blu|brn|gry|grn|hzl|oth)";
    private String pidRegex = "^[0-9]{9,9}";

    private HashMap<String,Validator> validators;

    public Validators () {
        this.validators = new HashMap<String,Validator>();


        this.validators.put("byr", new NumberValidator(1920,2002));
        this.validators.put("iyr", new NumberValidator(2010,2020));
        this.validators.put("eyr", new NumberValidator(2020,2030));
        this.validators.put("hgt", new HeightValidator(150,193,59,76));
        this.validators.put("hcl", new RegExValidator(hairRegex));
        this.validators.put("ecl", new RegExValidator(eyesRegex));
        this.validators.put("pid", new RegExValidator(pidRegex));
    }

    public boolean validate(String name, String value) {
        Validator validator = this.validators.get(name);
        if (validator != null) {
            return validator.isValid(value);
        }
        return true;
    }


}

