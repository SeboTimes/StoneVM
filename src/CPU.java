public class CPU {
    private int Pointer;
    private int Cache;
    private int[] Registers;


    public CPU() {
        Reset();
    }

    public void Reset() {
        Pointer = 0x04;
        Cache = 0x00;
        Registers = new int[0x03];
    }

    public int getPointerPos() { return Pointer; }

    public void printRegisters() {
        for (int i = 0; i < Registers.length; i++) {
            System.out.println("0x" + Integer.toHexString(i) + ": 0x" + Integer.toHexString(Registers[i]));
        }
    }

    public void Execute(RAM ram) {
        switch (ram.getValue(Pointer)) {
            // Reset
            case 0x01:
                Reset();
                break;
            // Break
            case 0x02:
                Pointer--;
                break;
            // Jump
            case 0x03:
                Pointer++;
                Pointer = ram.getValue(Pointer);
                break;

            // Set value of register 1
            case 0x11:
                Pointer++;
                Registers[0] = ram.getValue(Pointer);
                Pointer++;
                break;
            // Set value of register 2
            case 0x12:
                Pointer++;
                Registers[1] = ram.getValue(Pointer);
                Pointer++;
                break;
            // Set value of register 3
            case 0x13:
                Pointer++;
                Registers[2] = ram.getValue(Pointer);
                Pointer++;
                break;
            // Set value of a memory address
            case 0x15:
                Pointer++;
                ram.setValue(ram.getValue(Pointer), ram.getValue(Pointer + 1));
                Pointer++;
                Pointer++;
                break;

            // Increment value of register 1
            case 0x21:
                Registers[0]++;
                Pointer++;
                break;
            // Increment value of register 2
            case 0x22:
                Registers[1]++;
                Pointer++;
                break;
            // Increment value of register 3
            case 0x23:
                Registers[2]++;
                Pointer++;
                break;

            // Prints value of specified register as decimal
            case 0x31:
                Pointer++;
                System.out.print(Registers[ram.getValue(Pointer)]);
                Pointer++;
                break;
            // Prints value of next memory address as decimal
            case 0x32:
                Pointer++;
                System.out.print(ram.getValue(Pointer));
                Pointer++;
                break;
            // Prints value of specified register as hexadecimal
            case 0x33:
                Pointer++;
                System.out.print("0x" + Integer.toHexString(Registers[ram.getValue(Pointer)]));
                Pointer++;
                break;
            // Prints value of next memory address as hexadecimal
            case 0x34:
                Pointer++;
                System.out.print("0x" + Integer.toHexString(ram.getValue(Pointer)));
                Pointer++;
                break;
            // Prints value of specified register as binary
            case 0x35:
                Pointer++;
                System.out.print("0b" + Integer.toBinaryString(Registers[ram.getValue(Pointer)]));
                Pointer++;
                break;
            // Prints value of next memory address as binary
            case 0x36:
                Pointer++;
                System.out.print("0b" + Integer.toBinaryString(ram.getValue(Pointer)));
                Pointer++;
                break;
            // Prints value of specified register as ascii character
            case 0x37:
                Pointer++;
                System.out.print((char)Registers[ram.getValue(Pointer)]);
                Pointer++;
                break;
            // Prints value of next memory address as ascii character
            case 0x38:
                Pointer++;
                System.out.print((char)ram.getValue(Pointer));
                Pointer++;
                break;

            default:
                Pointer++;
                break;
        }
    }
}

