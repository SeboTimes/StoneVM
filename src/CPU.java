public class CPU {
    private int Pointer;
    private boolean Cache;
    private int[] Registers;


    public CPU() {
        Reset();
    }

    public void Reset() {
        Pointer = 0x04;
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

    public void Execute(RAM ram) {
        switch (ram.getValue(Pointer)) {
            case 0x01:
                Reset();
                break;
            case 0x02:
                Pointer--;
                break;
            case 0x03:
                Pointer++;
                Pointer = ram.getValue(Pointer);
                break;

            case 0x11:
                Pointer++;
                Registers[0] = ram.getValue(Pointer);
                Pointer++;
                break;
            case 0x12:
                Pointer++;
                Registers[1] = ram.getValue(Pointer);
                Pointer++;
                break;
            case 0x13:
                Pointer++;
                Registers[2] = ram.getValue(Pointer);
                Pointer++;
                break;
            case 0x14:
                Pointer++;
                ram.setValue(ram.getValue(Pointer), ram.getValue(Pointer + 1));
                Pointer++;
                Pointer++;
                break;

            case 0x21:
                Registers[0]++;
                Pointer++;
                break;
            case 0x22:
                Registers[1]++;
                Pointer++;
                break;
            case 0x23:
                Registers[2]++;
                Pointer++;
                break;
            case 0x24:
                Pointer++;
                ram.setValue(Pointer++, ram.getValue(Pointer) + 1);
                Pointer++;
                break;

            case 0x31:
                Pointer++;
                System.out.print((char)ram.getValue(Pointer));
                Pointer++;
                break;

            case 0x41:
                if (Cache) {
                    Pointer++;
                    Pointer = ram.getValue(Pointer);
                } else {
                    Pointer++;
                }
                break;
            case 0x42:
                if (!Cache) {
                    Pointer++;
                    Pointer = ram.getValue(Pointer);
                } else {
                    Pointer++;
                }
                break;
            case 0x43:
                Cache = (Registers[ram.getValue(Pointer + 1)] == Registers[ram.getValue(Pointer + 2)]);
                Pointer += 3;
                break;

            default:
                Pointer++;
                break;
        }
    }
}

