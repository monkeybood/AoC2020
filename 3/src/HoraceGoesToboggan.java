import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

public class HoraceGoesToboggan {

    private List<String> hill;
    private final char tree = '#';

    public HoraceGoesToboggan(List<String> hill) {
        this.hill = hill;
    }

    private int shiftLeft (int x, int rowLength) {
        while (x >= rowLength) {
            x = x - rowLength;
        }
        return x;
    }

    public int treeCounter (int right, int down) {
        int x = right,y = down, trees = 0;
            while (y < hill.size()) {
                String row = ((String)hill.get(y));
                x = this.shiftLeft(x, row.length());
                if (row.charAt(x) == tree) {
                    trees++;
                }
                x += right;
                y += down;
            }
        return trees;
    }

    public static void main(String[] args) {
        BufferedReader reader;


        try {
            reader = new BufferedReader(new FileReader(
                    "input.txt"));

            String line = reader.readLine();
            int rowLength = line.length();


            Vector lines = new Vector<String>();
            while (line !=null) {
                lines.add(line);
                line = reader.readLine();
            }

            HoraceGoesToboggan horace = new HoraceGoesToboggan(lines);


            BigInteger slope1 = BigInteger.valueOf(horace.treeCounter(1,1));
            BigInteger slope2 = BigInteger.valueOf(horace.treeCounter(3,1));
            BigInteger slope3 = BigInteger.valueOf(horace.treeCounter(5,1));
            BigInteger slope4 = BigInteger.valueOf(horace.treeCounter(7,1));
            BigInteger slope5 = BigInteger.valueOf(horace.treeCounter(1,2));

            System.out.println ("result:"+slope1.multiply(slope2).multiply(slope3).multiply(slope4).multiply(slope5));


            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
