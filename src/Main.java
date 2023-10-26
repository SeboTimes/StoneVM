import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CPU cpu = new CPU();
        RAM ram = new RAM();
        ram.Load("RAM.TXT");

        System.out.println("\nRegisters:");
        cpu.printRegisters();
        System.out.println("\nRAM:");
        ram.printData();

        while (cpu.getPointerPos() < ram.getSize()) {
            cpu.Execute(ram);
        }
    }
}
