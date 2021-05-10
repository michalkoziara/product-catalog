package data;

import database.ComputersQueries;
import database.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseComputerRepository implements ComputerRepository {
    ProductDatabase productDatabase;
    ComputersQueries computersQueries;

    private static DatabaseComputerRepository instance;

    private DatabaseComputerRepository() {
        if (!ProductDatabaseSingleton.getReady()) {
            ProductDatabaseSingleton.dbSetup();
        }
        productDatabase = ProductDatabaseSingleton.getInstance();
        computersQueries = productDatabase.getComputersQueries();
    }

    public static DatabaseComputerRepository getInstance() {
        if (instance == null) {
            instance = new DatabaseComputerRepository();
        }

        return instance;
    }

    @Override
    public List<Computer> getComputers() {
        List<Computer> computers = new ArrayList<>();

        computersQueries.selectComputers().executeAsList().forEach(
                result -> {
                    String numberOfCpuCores = result.getNumberOfCpuCores() != null
                            ? String.valueOf(result.getNumberOfCpuCores())
                            : "";
                    String clockFrequency = result.getClockFrequency() != null
                            ? String.valueOf(result.getClockFrequency())
                            : "";

                    computers.add(
                            new ComputerBuilder()
                                    .setManufacturerName(result.getManufacturerName())
                                    .setDiagonalScreenSize(result.getDiagonalScreenSize())
                                    .setScreenResolution(result.getScreenResolution())
                                    .setScreenSurfaceType(result.getScreenSurfaceType())
                                    .setTouchscreenFlag(result.getTouchscreenFlag())
                                    .setCpu(result.getCpu())
                                    .setNumberOfCpuCores(numberOfCpuCores)
                                    .setClockFrequency(clockFrequency)
                                    .setRam(result.getRam())
                                    .setDiscSize(result.getDiscSize())
                                    .setDiscType(result.getDiscType())
                                    .setGpu(result.getGpu())
                                    .setGpuMemory(result.getGpuMemory())
                                    .setOperatingSystem(result.getOperatingSystem())
                                    .setPhysicalDriveType(result.getPhysicalDriveType())
                                    .createComputer()
                    );
                }
        );

        return computers;
    }

    @Override
    public boolean saveComputers(List<Computer> computers) {
        computersQueries.deleteComputers();

        for (Computer computer : computers) {
            Long numberOfCpuCores = !computer.getNumberOfCpuCores().isBlank()
                    ? Long.valueOf(computer.getNumberOfCpuCores())
                    : null;
            Long clockFrequency = !computer.getClockFrequency().isBlank()
                    ? Long.valueOf(computer.getClockFrequency())
                    : null;

            computersQueries.insertComputers(
                    computer.getManufacturerName(),
                    computer.getDiagonalScreenSize(),
                    computer.getScreenResolution(),
                    computer.getScreenSurfaceType(),
                    computer.getTouchscreenFlag(),
                    computer.getCpu(),
                    numberOfCpuCores,
                    clockFrequency,
                    computer.getRam(),
                    computer.getDiscSize(),
                    computer.getDiscType(),
                    computer.getGpu(),
                    computer.getGpuMemory(),
                    computer.getOperatingSystem(),
                    computer.getPhysicalDriveType()
            );
        }

        return true;
    }

    public List<Computer> getComputersByScreenSurfaceType(String screenSurfaceType) {
        List<Computer> computers = new ArrayList<>();

        computersQueries.selectComputersByScreenSurfaceType(screenSurfaceType).executeAsList().forEach(
                result -> {
                    String numberOfCpuCores = result.getNumberOfCpuCores() != null
                            ? String.valueOf(result.getNumberOfCpuCores())
                            : "";
                    String clockFrequency = result.getClockFrequency() != null
                            ? String.valueOf(result.getClockFrequency())
                            : "";

                    computers.add(
                            new ComputerBuilder()
                                    .setManufacturerName(result.getManufacturerName())
                                    .setDiagonalScreenSize(result.getDiagonalScreenSize())
                                    .setScreenResolution(result.getScreenResolution())
                                    .setScreenSurfaceType(result.getScreenSurfaceType())
                                    .setTouchscreenFlag(result.getTouchscreenFlag())
                                    .setCpu(result.getCpu())
                                    .setNumberOfCpuCores(numberOfCpuCores)
                                    .setClockFrequency(clockFrequency)
                                    .setRam(result.getRam())
                                    .setDiscSize(result.getDiscSize())
                                    .setDiscType(result.getDiscType())
                                    .setGpu(result.getGpu())
                                    .setGpuMemory(result.getGpuMemory())
                                    .setOperatingSystem(result.getOperatingSystem())
                                    .setPhysicalDriveType(result.getPhysicalDriveType())
                                    .createComputer()
                    );
                }
        );

        return computers;
    }
}