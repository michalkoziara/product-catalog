import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    private boolean isChanged;
    private boolean isDuplicate;

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
            String physicalDriveType,
            boolean isChanged,
            boolean isDuplicate) {

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

        this.isChanged = isChanged;
        this.isDuplicate = isDuplicate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Computer) {
            Computer otherComputer = (Computer) obj;

            return Objects.equals(this.manufacturerName, otherComputer.manufacturerName)
                    && Objects.equals(this.diagonalScreenSize, otherComputer.diagonalScreenSize)
                    && Objects.equals(this.screenResolution, otherComputer.screenResolution)
                    && Objects.equals(this.screenSurfaceType, otherComputer.screenSurfaceType)
                    && Objects.equals(this.touchscreenFlag, otherComputer.touchscreenFlag)
                    && Objects.equals(this.cpu, otherComputer.cpu)
                    && Objects.equals(this.numberOfCpuCores, otherComputer.numberOfCpuCores)
                    && Objects.equals(this.clockFrequency, otherComputer.clockFrequency)
                    && Objects.equals(this.ram, otherComputer.ram)
                    && Objects.equals(this.discSize, otherComputer.discSize)
                    && Objects.equals(this.discType, otherComputer.discType)
                    && Objects.equals(this.gpu, otherComputer.gpu)
                    && Objects.equals(this.gpuMemory, otherComputer.gpuMemory)
                    && Objects.equals(this.operatingSystem, otherComputer.operatingSystem)
                    && Objects.equals(this.physicalDriveType, otherComputer.physicalDriveType);
        }

        return false;
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

    public boolean isChanged() {
        return isChanged;
    }

    public boolean isDuplicate() {
        return isDuplicate;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    public void setDuplicate(boolean duplicate) {
        isDuplicate = duplicate;
    }
}
