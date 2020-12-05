public class NumberValidator implements  Validator{
    int min;
    int max;

    public NumberValidator (int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isValid(String field) {
        try {
            int number = Integer.parseInt(field);
            if (this.min <= number && this.max >= number)
                return true;
            else
                return false;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }
}
