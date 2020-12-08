import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Computer {

    public int accumulator;
    int nextInstruction;
    private Vector<String> instructions;
    private Vector<Integer> numbers;

    private HashMap<Integer,Integer> executedInstructions;

    public Computer (Vector<String> instructions, Vector<Integer> numbers) {
        this.instructions = instructions;
        this.numbers = numbers;
        this.accumulator = 0;
        this.nextInstruction = 0;
        this.executedInstructions = new HashMap<Integer,Integer>();
    }

    public void reset () {
        this.executedInstructions.clear();
        this.nextInstruction = 0;
        this.accumulator = 0;
    }

    public void toggleInstruction (int position) {
        if (instructions.get(position).contains("nop"))  this.instructions.set(position, "jmp");
        else {
            if (instructions.get(position).contains("jmp")) this.instructions.set(position, "nop");
        }
    }

    public int peekNextInstructionExecutionCount() {
        if (this.executedInstructions.containsKey(this.nextInstruction)) return this.executedInstructions.get(this.nextInstruction); else return 0;
    }

    public void incrementInstructionCount (int instructionNumber) {
        if (this.executedInstructions.containsKey(instructionNumber))
            this.executedInstructions.put(instructionNumber, this.executedInstructions.get(instructionNumber)+1);
        else
            this.executedInstructions.put(instructionNumber,1);
    }

    // returns false if no more instructions
    public boolean executeNextInstruction() {
        if (this.nextInstruction >= this.instructions.size()) return false;
        String instruction = instructions.get(this.nextInstruction);
        int number = this.numbers.get(this.nextInstruction);
        this.incrementInstructionCount(this.nextInstruction);

        if ("nop".equals(instruction)) {
            this.nextInstruction++;
        }

        if ("acc".equals(instruction)) {
            this.accumulator += number;
            this.nextInstruction++;
        }

        if ("jmp".equals(instruction)) {
            this.nextInstruction += number;
        }

        return true;
    }


    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "input.txt"));

            String line = reader.readLine();
            Vector<String> instructions = new Vector<String>();
            Vector<Integer> numbers = new Vector<Integer>();



            Vector<Integer> toggleTracker = new Vector<Integer>();

            while (line != null) {

                String[] array = line.split(" ");
                instructions.add(array[0]);
                numbers.add(Integer.valueOf(array[1]));
                if (line.contains("nop") || line.contains("jmp")) toggleTracker.add(instructions.size()-1);
                line = reader.readLine();
            }
            reader.close();



            Computer computer = new Computer(instructions, numbers);

            boolean executedSuccess = true;
            boolean loopDetected = false;
            while (executedSuccess && !loopDetected) {
                if (computer.peekNextInstructionExecutionCount() > 0) {
                    loopDetected = true;
                } else {
                    executedSuccess = computer.executeNextInstruction();
                }
            }

            System.out.println("Part one acc:"+computer.accumulator);

            computer.reset();
            int toggleCount = 0;

            Iterator <Integer> toggleIter = toggleTracker.iterator();
            while (executedSuccess) {

                Integer togglePosition = toggleIter.next();

                computer.toggleInstruction(togglePosition);

                loopDetected = false;
                while (executedSuccess && !loopDetected) {
                    if (computer.peekNextInstructionExecutionCount() > 0) {
                        loopDetected = true;
                    } else {
                        executedSuccess = computer.executeNextInstruction();
                    }
                }

                computer.toggleInstruction(togglePosition);

                if (!executedSuccess) System.out.println("Part 2:" + computer.accumulator);
                computer.reset();
                toggleCount++;
            }



        } catch (IOException exception) {
            System.out.println("Ex:" + exception);
        }

    }
}
