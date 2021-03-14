import org.jetbrains.annotations.NotNull;

public class ComputerBuilder {
    private String manufacturerName;
    private String diagonalScreenSize;
    private String screenResolution;
    private String screenSurfaceType;
    private String touchscreenFlag;
    private String cpu;
    private String numberOfCpuCores;
    private String clockFrequency;
    private String ram;
    private String discSize;
    private String discType;
    private String gpu;
    private String gpuMemory;
    private String operatingSystem;
    private String physicalDriveType;

    public ComputerBuilder() {
        manufacturerName = "";
        diagonalScreenSize = "";
        screenResolution = "";
        screenSurfaceType = "";
        touchscreenFlag = "";
        cpu = "";
        numberOfCpuCores = "";
        clockFrequency = "";
        ram = "";
        discSize = "";
        discType = "";
        gpu = "";
        gpuMemory = "";
        operatingSystem = "";
        physicalDriveType = "";
    }

    public ComputerBuilder(@NotNull Computer computer) {
        manufacturerName = computer.getManufacturerName();
        diagonalScreenSize = computer.getDiagonalScreenSize();
        screenResolution = computer.getScreenResolution();
        screenSurfaceType = computer.getScreenSurfaceType();
        touchscreenFlag = computer.getTouchscreenFlag();
        cpu = computer.getCpu();
        numberOfCpuCores = computer.getNumberOfCpuCores();
        clockFrequency = computer.getClockFrequency();
        ram = computer.getRam();
        discSize = computer.getDiscSize();
        discType = computer.getDiscType();
        gpu = computer.getGpu();
        gpuMemory = computer.getGpuMemory();
        operatingSystem = computer.getOperatingSystem();
        physicalDriveType = computer.getPhysicalDriveType();
    }

    public Computer createComputer() {
        return new Computer(
                manufacturerName,
                diagonalScreenSize,
                screenResolution,
                screenSurfaceType,
                touchscreenFlag,
                cpu,
                numberOfCpuCores,
                clockFrequency,
                ram,
                discSize,
                discType,
                gpu,
                gpuMemory,
                operatingSystem,
                physicalDriveType
        );
    }

    public ComputerBuilder setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
        return this;
    }

    public ComputerBuilder setDiagonalScreenSize(String diagonalScreenSize) {
        this.diagonalScreenSize = diagonalScreenSize;
        return this;
    }

    public ComputerBuilder setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
        return this;
    }

    public ComputerBuilder setScreenSurfaceType(String screenSurfaceType) {
        this.screenSurfaceType = screenSurfaceType;
        return this;
    }

    public ComputerBuilder setTouchscreenFlag(String touchscreenFlag) {
        this.touchscreenFlag = touchscreenFlag;
        return this;
    }

    public ComputerBuilder setCpu(String cpu) {
        this.cpu = cpu;
        return this;
    }

    public ComputerBuilder setNumberOfCpuCores(String numberOfCpuCores) {
        this.numberOfCpuCores = numberOfCpuCores;
        return this;
    }

    public ComputerBuilder setClockFrequency(String clockFrequency) {
        this.clockFrequency = clockFrequency;
        return this;
    }

    public ComputerBuilder setRam(String ram) {
        this.ram = ram;
        return this;
    }

    public ComputerBuilder setDiscSize(String discSize) {
        this.discSize = discSize;
        return this;
    }

    public ComputerBuilder setDiscType(String discType) {
        this.discType = discType;
        return this;
    }

    public ComputerBuilder setGpu(String gpu) {
        this.gpu = gpu;
        return this;
    }

    public ComputerBuilder setGpuMemory(String gpuMemory) {
        this.gpuMemory = gpuMemory;
        return this;
    }

    public ComputerBuilder setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public ComputerBuilder setPhysicalDriveType(String physicalDriveType) {
        this.physicalDriveType = physicalDriveType;
        return this;
    }
}