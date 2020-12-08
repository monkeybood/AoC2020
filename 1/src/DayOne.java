
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class DayOne {

	public static void main(String[] args) {
		BufferedReader reader;
		try {


			reader = new BufferedReader(new FileReader(
					"x.txt"));
			String line = reader.readLine();
			Vector all = new Vector<Integer>();



			while (line != null) {

				all.add(Integer.parseInt(line));
				line = reader.readLine();
			}


			Iterator<Integer> numberOneIter = all.iterator();


			Vector<NumberWang> numberWangs = new Vector<NumberWang> ();
			// initial number wang set up



			Iterator outerLoop = all.iterator();
			while (outerLoop.hasNext()) {
				Integer outer = (Integer) outerLoop.next();
				Iterator innerLoop = all.iterator();
				while (innerLoop.hasNext()) {
					Integer inner = (Integer) innerLoop.next();
					Iterator mostIn = all.iterator();
					while (mostIn.hasNext()) {
						Integer mostInInt = (Integer)mostIn.next();
						Vector numbers = new Vector();
						numbers.add(outer);
						numbers.add(inner);
						numbers.add(mostInInt);
						NumberWang wang = new NumberWang(numbers);
						numberWangs.add(wang);
					}
				}
			}



			boolean found = false;

			int target = 2020;

			Iterator<NumberWang> targetIter = numberWangs.iterator();

			while (targetIter.hasNext() && !found) {
				NumberWang wang = targetIter.next();
				if (target == wang.sum()) {
					found = true;
					System.out.println ("Product:"+wang.product());
				}
			}


			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
