import java.util.Arrays;
import java.util.List;

public class Computer {
    private final String manufacturerName;
    private final String diagonalScreenSize;
    private final String screenResolution;
    private final String screenSurfaceType;
    private final String touchscreenFlag;
    private final String cpu;
    private final String numberOfCpuCores;
    private final String clockFrequency;
    private final String ram;
    private final String discSize;
    private final String discType;
    private final String gpu;
    private final String gpuMemory;
    private final String operatingSystem;
    private final String physicalDriveType;

    public Computer(
            String manufacturerName,
            String diagonalScreenSize,
            String screenResolution,
            String screenSurfaceType,
            String touchscreenFlag,
            String cpu,
            String numberOfCpuCores,
            String clockFrequency,
            String ram,
            String discSize,
            String discType,
            String gpu,
            String gpuMemory,
            String operatingSystem,
            String physicalDriveType) {

        this.manufacturerName = manufacturerName;
        this.diagonalScreenSize = diagonalScreenSize;
        this.screenResolution = screenResolution;
        this.screenSurfaceType = screenSurfaceType;
        this.touchscreenFlag = touchscreenFlag;
        this.cpu = cpu;
        this.numberOfCpuCores = numberOfCpuCores;
        this.clockFrequency = clockFrequency;
        this.ram = ram;
        this.discSize = discSize;
        this.discType = discType;
        this.gpu = gpu;
        this.gpuMemory = gpuMemory;
        this.operatingSystem = operatingSystem;
        this.physicalDriveType = physicalDriveType;
    }

    public List<String> getParameters() {
        return Arrays.asList(
                getManufacturerName(),
                getDiagonalScreenSize(),
                getScreenResolution(),
                getScreenSurfaceType(),
                getTouchscreenFlag(),
                getCpu(),
                getNumberOfCpuCores(),
                getClockFrequency(),
                getRam(),
                getDiscSize(),
                getDiscType(),
                getGpu(),
                getGpuMemory(),
                getOperatingSystem(),
                getPhysicalDriveType()
        );
    }

    public String toCsv() {
        return String.join(";", getParameters()) + ";";
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getDiagonalScreenSize() {
        return diagonalScreenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public String getScreenSurfaceType() {
        return screenSurfaceType;
    }

    public String getTouchscreenFlag() {
        return touchscreenFlag;
    }

    public String getCpu() {
        return cpu;
    }

    public String getNumberOfCpuCores() {
        return numberOfCpuCores;
    }

    public String getClockFrequency() {
        return clockFrequency;
    }

    public String getRam() {
        return ram;
    }

    public String getDiscSize() {
        return discSize;
    }

    public String getDiscType() {
        return discType;
    }

    public String getGpu() {
        return gpu;
    }

    public String getGpuMemory() {
        return gpuMemory;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getPhysicalDriveType() {
        return physicalDriveType;
    }
}
