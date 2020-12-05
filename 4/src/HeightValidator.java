public class HeightValidator implements Validator{

    private NumberValidator cmValidator;
    private NumberValidator inchValidator;
    public HeightValidator (int minCM, int maxCM, int minInch, int maxInch) {
        this.cmValidator = new NumberValidator(minCM,maxCM);
        this.inchValidator = new NumberValidator(minInch, maxInch);
    }

    @Override
    public boolean isValid(String field) {
        if (field.contains("cm")) {
            return this.cmValidator.isValid(field.replace("cm",""));
        }
        if (field.contains("in")) {
            return this.inchValidator.isValid(field.replace("in",""));
        }
        return false;
    }
}
