public class RegExValidator implements Validator{

    private String regex;


    public RegExValidator (String regex) {
        this.regex = regex;
    }

    @Override
    public boolean isValid(String field) {
        return field.matches(regex);
    }


}
