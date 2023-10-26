import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RAM {
    private final ArrayList<Integer> content;

    public RAM() {
        content = new ArrayList<Integer>();
        for (int i = 0; i < 0x04; i++) content.add(0x00);
    }

    public void Load(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String Data = scanner.nextLine();
            if (Data != "") if (Data.charAt(0) != (char)0x23) {
                content.add(Integer.decode(Data));
            }
        }
    }

    public int getSize() {
        return content.size();
    }

    public int getValue(int address) {
        return content.get(address);
    }

    public void setValue(int address, int value) {
        content.set(address, value);
    }

    public void printData() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println("0x" + Integer.toHexString(i) + ": 0x" + Integer.toHexString(getValue(i)));
        }
    }
}
