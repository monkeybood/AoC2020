import java.util.Iterator;
import java.util.Vector;

public class NumberWang {

    Vector<Integer> numbers;

    public NumberWang (Integer initialNumber) {
        super();
        this.numbers = new Vector<Integer>();
        this.numbers.add(initialNumber);
    }

    public NumberWang (Vector<Integer> initialNumbers) {
        super();
        this.numbers = (Vector<Integer>)initialNumbers.clone();
    }


    public int sum() {
        int result = 0;
        Iterator a = numbers.iterator();
        while (a.hasNext()) {
            Integer numberToAdd = (Integer) a.next();
            result =  result + numberToAdd.intValue();
        }
        return result;
    }

    public int product() {
        int result = 0;
        Iterator a = numbers.iterator();
        if (a.hasNext()) {
            result = ((Integer)a.next()).intValue();
        }

        while (a.hasNext()) {
            result *=  ((Integer)a.next()).intValue();
        }
        return result;
    }

    public void addANumber (Integer newNumber) {
        numbers.add(newNumber);
    }

    public void setNumbers (Vector<Integer> newNumbers) {
        this.numbers = newNumbers;
    }

    public int numberOfNumbers() {
        return this.numbers.size();
    }
}
