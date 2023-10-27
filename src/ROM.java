import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ROM {
    private final ArrayList<Integer> content;

    public ROM(String fileName) throws FileNotFoundException {
        content = new ArrayList<Integer>();

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String Data = scanner.nextLine();
            if (Data != "") if (Data.charAt(0) != (char)0x23) {
                String[] DataArray = Data.split(" ");
                for (String data : DataArray) {
                    switch (data) {
                        case "NULL":
                            content.add(0x00);
                            break;

                        case "RESET":
                            content.add(0x01);
                            break;
                        case "BREAK":
                            content.add(0x02);
                            break;
                        case "JUMP":
                            content.add(0x03);
                            break;

                        case "SET_REG":
                            content.add(0x11);
                            break;
                        case "SET_RAM":
                            content.add(0x12);
                            break;

                        case "INC_REG":
                            content.add(0x21);
                            break;
                        case "INC_RAM":
                            content.add(0x22);
                            break;
                        case "DEC_REG":
                            content.add(0x23);
                            break;
                        case "DEC_RAM":
                            content.add(0x24);
                            break;

                        case "PUT":
                            content.add(0x31);
                            break;
                        case "PUT_REG":
                            content.add(0x32);
                            break;
                        case "PUT_RAM":
                            content.add(0x33);
                            break;

                        case "JUMP_TRUE":
                            content.add(0x41);
                            break;
                        case "JUMP_FALSE":
                            content.add(0x42);
                            break;
                        case "EQUALS":
                            content.add(0x43);
                            break;

                        default:
                            content.add(Integer.decode(data));
                            break;
                    }
                }
            }
        }
    }

    public int getSize() { return content.size(); }

    public int getValue(int address) { return content.get(address); }

    public void printData() {
        System.out.println("\nROM:");
        for (int i = 0; i < getSize(); i++) {
            System.out.println("0x" + Integer.toHexString(i) + ": 0x" + Integer.toHexString(getValue(i)));
        }
    }
}
