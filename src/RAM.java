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

    public void addAddress(int value) {
        content.add(value);
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
        System.out.println("\nRAM:");
        for (int i = 0; i < getSize(); i++) {
            System.out.println("0x" + Integer.toHexString(i) + ": 0x" + Integer.toHexString(getValue(i)));
        }
    }
}
