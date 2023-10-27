import java.util.ArrayList;

public class RAM {
    private final int[] content;

    public RAM(int size) {
        content = new int[size];
    }

    public int getSize() { return content.length; }

    public int getValue(int address) { return content[address]; }

    public void setValue(int address, int value) { content[address] = value; }

    public void printData() {
        System.out.println("\nRAM:");
        for (int i = 0; i < getSize(); i++) {
            System.out.println("0x" + Integer.toHexString(i) + ": 0x" + Integer.toHexString(getValue(i)));
        }
    }
}
