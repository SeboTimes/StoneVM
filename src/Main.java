import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        CPU cpu = new CPU();
        RAM ram = new RAM(8);
        ROM rom = new ROM("ramdisk.stone");

        rom.printData();

        while (cpu.getPointerPos() < rom.getSize()) {
            cpu.Execute(ram, rom);
            TimeUnit.MILLISECONDS.sleep(10);
        }

        cpu.printData();
        ram.printData();
    }
}
