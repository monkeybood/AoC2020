import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Baggage {

    String bagName;
    Map<String, Integer> contentsMap;

    public Baggage (String configString) {
        this.bagName = configString.substring(0, configString.indexOf(" bags"));
        this.contentsMap = new HashMap<String, Integer>();

        if (configString.contains("no other")) return;
        String[] contents = configString.substring(configString.indexOf("contain ") + "contain ".length(), configString.length()-1).replaceAll("bags","bag").replaceAll("bag","").split(",");

        for (int i = 0; i < contents.length; i++) {
            String bagString = contents[i].stripLeading().stripTrailing();
            int number = Integer.parseInt(bagString.substring(0,bagString.indexOf(" ")));
            String containedBagName = bagString.substring(bagString.indexOf(" ")+1, bagString.length());
            this.contentsMap.put(containedBagName,number);
        }
    }

    public String getBagName() {
        return this.bagName;
    }

    public boolean contains (String bag, HashMap<String, Baggage> allBags) {
        if (this.containsDirectly(bag)) return true;
        if (!this.containsOtherBags()) return false;
        boolean found = false;

        Iterator<String> bagsToCheck = this.contentsMap.keySet().iterator();
        while (!found && bagsToCheck.hasNext()) {
            Baggage currentBag = allBags.get(bagsToCheck.next());
            found = currentBag.contains(bag,allBags);
        }
        return found;
    }

    public boolean containsDirectly(String bag) {
        return contentsMap.containsKey(bag);
    }

    public boolean containsOtherBags() {
        return (this.contentsMap.size() > 0);
    }

    public int countContents (HashMap<String, Baggage> allBags) {
        int count = 0;
        Iterator<String> contentsIter = this.contentsMap.keySet().iterator();
        while (contentsIter.hasNext()) {
            String nextBag = contentsIter.next();
            count += this.contentsMap.get(nextBag);
            Baggage baggage = allBags.get(nextBag);
            count += (baggage.countContents(allBags) * this.contentsMap.get(nextBag));
        }
        return count;
    }

    public String toString(){
        return "["+bagName+"]";
    }


    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "input.txt"));

            String line = reader.readLine();


            HashMap <String, Baggage> allBags = new HashMap<String, Baggage>();

            while (line != null) {
                Baggage baggage = new Baggage(line);
                line = reader.readLine();
                allBags.put(baggage.getBagName(), baggage);
            }


            Iterator<Baggage> baggageIterator = allBags.values().iterator();

            String targetBag = "shiny gold";
            int bagCount = 0;
            while (baggageIterator.hasNext()) {
                Baggage baggage = baggageIterator.next();
                if (baggage.contains(targetBag,allBags)) bagCount++;
            }

            System.out.println(targetBag+" can go in:"+bagCount);

            System.out.println(targetBag+" holds:"+allBags.get(targetBag).countContents(allBags));


        } catch (IOException exception) {
            System.out.println("Ex:" + exception);
        }

    }
}