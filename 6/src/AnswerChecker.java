import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class AnswerChecker {

    private String answers;
    private int guests;

    public AnswerChecker () {
        super();
        this.answers="";
        this.guests = 0;
    }


    public void addAnswers(String newAnswers) {
        this.answers = this.answers + newAnswers;
        this.guests++;
    }

    public void clearAnswers() {
        this.answers = "";
        this.guests = 0;
    }

    private HashMap<String, Integer> buildMap () {
        HashMap<String,Integer> countMap = new HashMap<String, Integer>();
        for (int i = 0; i < answers.length(); i++) {
            String key = answers.substring(i,i+1);
            if (countMap.containsKey(key)) {
                Integer count = countMap.get(key);
                countMap.put(key,count + 1);
            } else {
                countMap.put(key, 1);
            }
        }
        return countMap;
    }

    public int countGroupAnswers () {
        Iterator<Integer> groupCount = this.buildMap().values().iterator();
        int count = 0;
        while (groupCount.hasNext()) {
            if (groupCount.next().intValue() == this.guests) count++;
        }
        return count;
    }

    public int countAnswers () {
        HashMap<String,Integer> countMap = new HashMap<String, Integer>();
        for (int i = 0; i < answers.length(); i++) {
            String key = answers.substring(i,i+1);
            if (countMap.containsKey(key)) {
                Integer count = countMap.get(key);
                countMap.put(key,count + 1);
            } else {
                countMap.put(key, 1);
            }
        }
        return this.buildMap().size();
    }


    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "input.txt"));

            String line = reader.readLine();
            AnswerChecker checker = new AnswerChecker();
            int uniqueCount = 0;
            int part2Count = 0;

            while (line != null) {
                if (!line.equals("")) {
                    checker.addAnswers(line);
                } else {
                    uniqueCount = uniqueCount + checker.countAnswers();
                    part2Count = part2Count + checker.countGroupAnswers();
                    checker.clearAnswers();
                }
                line = reader.readLine();
            }

            uniqueCount = uniqueCount + checker.countAnswers();
            part2Count = part2Count + checker.countGroupAnswers();
            System.out.println("Answer count:"+uniqueCount);
            System.out.println("Part 2 count:"+part2Count);

        } catch (IOException exception) {
            System.out.println("Ex:" + exception);
        }


    }
}
