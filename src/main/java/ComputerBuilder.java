import org.jetbrains.annotations.NotNull;

import java.util.Map;

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

    private boolean isChanged;
    private boolean isDuplicate;

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

        isChanged = false;
        isDuplicate = false;
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

        isChanged = computer.isChanged();
        isDuplicate = computer.isDuplicate();
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
                physicalDriveType,
                isChanged,
                isDuplicate
        );
    }

    public ComputerBuilder fromMap(Map<String, String> parameters) {
        if (parameters.containsKey("[manufacturer]")) {
            manufacturerName = parameters.get("[manufacturer]");
        }

        if (parameters.containsKey("[screen, size]")) {
            diagonalScreenSize = parameters.get("[screen, size]");
        }

        if (parameters.containsKey("[screen, resolution]")) {
            screenResolution = parameters.get("[screen, resolution]");
        }

        if (parameters.containsKey("[screen, type]")) {
            screenSurfaceType = parameters.get("[screen, type]");
        }

        if (parameters.containsKey("[screen]")) {
            touchscreenFlag = parameters.get("[screen]");
        }

        if (parameters.containsKey("[processor, name]")) {
            cpu = parameters.get("[processor, name]");
        }

        if (parameters.containsKey("[processor, physical_cores]")) {
            numberOfCpuCores = parameters.get("[processor, physical_cores]");
        }

        if (parameters.containsKey("[processor, clock_speed]")) {
            clockFrequency = parameters.get("[processor, clock_speed]");
        }

        if (parameters.containsKey("[ram]")) {
            ram = parameters.get("[ram]");
        }

        if (parameters.containsKey("[disc, storage]")) {
            discSize = parameters.get("[disc, storage]");
        }

        if (parameters.containsKey("[disc]")) {
            discType = parameters.get("[disc]");
        }

        if (parameters.containsKey("[graphic_card, name]")) {
            gpu = parameters.get("[graphic_card, name]");
        }

        if (parameters.containsKey("[graphic_card, memory]")) {
            gpuMemory = parameters.get("[graphic_card, memory]");
        }

        if (parameters.containsKey("[os]")) {
            operatingSystem = parameters.get("[os]");
        }

        if (parameters.containsKey("[disc_reader]")) {
            physicalDriveType = parameters.get("[disc_reader]");
        }

        return this;
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

    public ComputerBuilder setChanged(boolean changed) {
        isChanged = changed;
        return this;
    }

    public ComputerBuilder setDuplicate(boolean duplicate) {
        isDuplicate = duplicate;
        return this;
    }
}