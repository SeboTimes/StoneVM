import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ROM {
    private final RAM ram;

    public ROM(RAM ram) {
        this.ram = ram;
    }

    public void Load(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String Data = scanner.nextLine();
            if (Data != "") if (Data.charAt(0) != (char)0x23) {
                String[] DataArray = Data.split(" ");
                for (String data : DataArray) {
                    switch (data) {
                        case "RESET":
                            ram.addAddress(0x01);
                            break;
                        case "BREAK":
                            ram.addAddress(0x02);
                            break;
                        case "JUMP":
                            ram.addAddress(0x03);
                            break;

                        case "SET0":
                            ram.addAddress(0x11);
                            break;
                        case "SET1":
                            ram.addAddress(0x12);
                            break;
                        case "SET2":
                            ram.addAddress(0x13);
                            break;
                        case "SET_RAM":
                            ram.addAddress(0x14);
                            break;

                        case "INC0":
                            ram.addAddress(0x21);
                            break;
                        case "INC1":
                            ram.addAddress(0x22);
                            break;
                        case "INC2":
                            ram.addAddress(0x23);
                            break;
                        case "INC_RAM":
                            ram.addAddress(0x24);
                            break;

                        case "PUT":
                            ram.addAddress(0x31);
                            break;

                        case "JUMP_TRUE":
                            ram.addAddress(0x41);
                            break;
                        case "JUMP_FALSE":
                            ram.addAddress(0x42);
                            break;
                        case "EQUALS":
                            ram.addAddress(0x43);
                            break;

                        default:
                            ram.addAddress(Integer.decode(data));
                            break;
                    }
                }
            }
        }
    }
}
