// Malika Taverdieva mata6399

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class InputReader {

    private static ArrayList<InputStream> usedStreams = new ArrayList<>();
    private Scanner input;

    public InputReader(InputStream inputStream) {
        if (usedStreams.contains(inputStream)) {
            throw new IllegalStateException("Error: Stream is already used");
        }
        input = new Scanner(inputStream);
        usedStreams.add(inputStream);
    }

    public InputReader() {
        this(System.in);
    }

    public String stringInput(String text) {
        System.out.print(text + "?> ");
        String s = input.nextLine();
        s = s.trim();
        return s;
    }

    public int intInput(String text) {
        System.out.print(text + "?> ");
        int i = input.nextInt();
        input.nextLine();
        return i;
    }

    public double doubleInput(String text) {
        System.out.print(text + "?> ");
        double d = input.nextDouble();
        input.nextLine();
        return d;
    }
}