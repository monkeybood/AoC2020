import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Seat {

    private String boardingPass;
    private int row;
    private int column;
    private BigInteger idMultiple;

    public Seat (String boardingPass, int planeLength, int planeWidth, int idMultiple) {
        this.boardingPass = boardingPass;
        this.idMultiple = BigInteger.valueOf(idMultiple);

        String rowString = boardingPass.replace("L","").replace("R","");
        String columnString = boardingPass.replace("B","").replace("F","");

        this.row = this.splitter(rowString,planeLength,"F".charAt(0));
        this.column = this.splitter(columnString,planeWidth,"L".charAt(0));
    }

    private int splitter (String codeString, int maxValue, char lowerChar) {

        int min = 0;
        int max = maxValue;

        for (int i = 0; i < codeString.length(); i++) {
            int half = (max - min)/ 2 ;
            if (codeString.charAt(i) == lowerChar) {
                max = min+half;
            } else {
                min = min + half + 1;
            }
        }
        return min;
    }

    public int getRow(){
        return row;
    }

    public int getColumn() {
        return column;
    }

    public BigInteger getID() {
        BigInteger result = BigInteger.valueOf(this.getRow());

        return result.multiply(this.idMultiple).add(BigInteger.valueOf(this.getColumn()));
    }

    public String toString() {
        return "Boarding pass:["+this.boardingPass+"] row:"+this.getRow()+" column:"+getColumn()+" ID:"+this.getID();
    }

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            int planeLength = 127;
            int planeWidth = 7;
            int multiple = 8;

            reader = new BufferedReader(new FileReader(
                    "input.txt"));

            String line = reader.readLine();
            BigInteger highestId = BigInteger.valueOf(0);
            BigInteger lowestId = BigInteger.valueOf(Integer.MAX_VALUE);

            Vector<BigInteger> ids = new <BigInteger>Vector();


            while (line != null){
                Seat seat = new Seat(line, planeLength,planeWidth,multiple);
                BigInteger code = seat.getID();
                ids.add(code);
                if (code.compareTo(highestId) == 1) {
                    highestId = code;
                }
                if (code.compareTo(lowestId) == -1) {
                    lowestId = code;
                }
                line = reader.readLine();
            }
            System.out.println ("Highest code"+highestId);
            System.out.println ("Lowest code"+lowestId);
            System.out.println (ids.size());

            System.out.println ("-------");
            Collections.sort(ids);

            Iterator<BigInteger> iter = ids.iterator();

            BigInteger expectedNext = lowestId;
            boolean found = false;
            while (iter.hasNext() &&!found) {
                if (expectedNext.compareTo(iter.next()) != 0) {
                    found = true;
                } else {
                    expectedNext = BigInteger.valueOf(1).add(expectedNext);
                }
            }

            System.out.println("EXPECTED:"+expectedNext);

            reader.close();
        } catch (IOException exception) {
            System.out.println("Ex:" + exception);
        }

    }
}
