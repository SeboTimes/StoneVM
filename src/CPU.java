public class CPU {
    private int Pointer;
    private boolean Cache;
    private int[] Registers;


    public CPU() {
        Reset();
    }

    public void Reset() {
        Pointer = 0x00;
        Cache = false;
        Registers = new int[0x03];
    }

    public int getPointerPos() { return Pointer; }

    public void printData() {
        System.out.println("\nRegisters:");
        for (int i = 0; i < Registers.length; i++) {
            System.out.println("0x" + Integer.toHexString(i) + ": 0x" + Integer.toHexString(Registers[i]));
        }
        System.out.println("\nCache:\n" + Cache);
    }

    public void Execute(RAM ram, ROM rom) {
        switch (rom.getValue(Pointer)) {
            case 0x01:
                Reset();
                break;
            case 0x02:
                Pointer--;
                break;
            case 0x03:
                Pointer = rom.getValue(Pointer + 1);
                Pointer += 2;
                break;

            case 0x11:
                Registers[rom.getValue(Pointer + 1)] = rom.getValue(Pointer + 2);
                Pointer += 3;
                break;
            case 0x12:
                ram.setValue(rom.getValue(Pointer + 1), rom.getValue(Pointer + 2));
                Pointer += 3;
                break;

            case 0x21:
                Registers[rom.getValue(Pointer + 1)]++;
                Pointer += 2;
                break;
            case 0x22:
                ram.setValue(rom.getValue(Pointer + 1), ram.getValue(rom.getValue(Pointer + 1)) + 1);
                Pointer += 2;
                break;
            case 0x23:
                Registers[rom.getValue(Pointer + 1)]--;
                Pointer += 2;
                break;
            case 0x24:
                ram.setValue(rom.getValue(Pointer + 1), ram.getValue(rom.getValue(Pointer + 1)) - 1);
                Pointer += 2;
                break;

            case 0x31:
                System.out.print((char)rom.getValue(Pointer + 1));
                Pointer += 2;
                break;
            case 0x32:
                System.out.print((char)Registers[rom.getValue(Pointer + 1)]);
                Pointer += 2;
                break;
            case 0x33:
                System.out.print((char)ram.getValue(rom.getValue(Pointer + 1)));
                Pointer += 2;
                break;

            case 0x41:
                if (Cache) {
                    Pointer = rom.getValue(Pointer + 1);
                } else {
                    Pointer++;
                }
                break;
            case 0x42:
                if (!Cache) {
                    Pointer = rom.getValue(Pointer + 1);
                } else {
                    Pointer++;
                }
                break;
            case 0x43:
                Cache = (Registers[rom.getValue(Pointer + 1)] == Registers[rom.getValue(Pointer + 2)]);
                Pointer += 3;
                break;

            default:
                Pointer++;
                break;
        }
    }
}

