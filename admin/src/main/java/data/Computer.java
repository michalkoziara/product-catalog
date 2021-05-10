package data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "computer")
public class Computer {
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

    public Computer() {
    }

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

    @XmlElement
    public String getManufacturerName() {
        return manufacturerName;
    }

    @XmlElement
    public String getDiagonalScreenSize() {
        return diagonalScreenSize;
    }

    @XmlElement
    public String getScreenResolution() {
        return screenResolution;
    }

    @XmlElement
    public String getScreenSurfaceType() {
        return screenSurfaceType;
    }

    @XmlElement
    public String getTouchscreenFlag() {
        return touchscreenFlag;
    }

    @XmlElement
    public String getCpu() {
        return cpu;
    }

    @XmlElement
    public String getNumberOfCpuCores() {
        return numberOfCpuCores;
    }

    @XmlElement
    public String getClockFrequency() {
        return clockFrequency;
    }

    @XmlElement
    public String getRam() {
        return ram;
    }

    @XmlElement
    public String getDiscSize() {
        return discSize;
    }

    @XmlElement
    public String getDiscType() {
        return discType;
    }

    @XmlElement
    public String getGpu() {
        return gpu;
    }

    @XmlElement
    public String getGpuMemory() {
        return gpuMemory;
    }

    @XmlElement
    public String getOperatingSystem() {
        return operatingSystem;
    }

    @XmlElement
    public String getPhysicalDriveType() {
        return physicalDriveType;
    }

    @XmlTransient
    public boolean isChanged() {
        return isChanged;
    }

    @XmlTransient
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
