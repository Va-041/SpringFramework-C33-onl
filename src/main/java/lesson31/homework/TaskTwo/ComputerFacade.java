package lesson31.homework.TaskTwo;

public class ComputerFacade {
    private CPU cpu;
    private RAM ram;
    private GPU gpu;
    private HDD hdd;
    private PowerUnit powerUnit;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.ram = new RAM();
        this.gpu = new GPU();
        this.hdd = new HDD();
        this.powerUnit = new PowerUnit();
    }

    public void startComputer() {
        System.out.println("\nЗапуск компьютера...");
        powerUnit.startPowerUnit();
        cpu.initialize();
        ram.loadMemory();
        hdd.decodeHddSectors();
        hdd.loadHddData();
        cpu.execute();
        gpu.renderGraphics();
        System.out.println("\nКомпьютер успешно запущен!");
    }
}

