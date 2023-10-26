import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CPU cpu = new CPU();
        RAM ram = new RAM();
        ROM rom = new ROM(ram);
        rom.Load("ramdisk.stone");

        while (cpu.getPointerPos() < ram.getSize()) {
            cpu.Execute(ram);
        }

        cpu.printData();
        ram.printData();
    }
}
