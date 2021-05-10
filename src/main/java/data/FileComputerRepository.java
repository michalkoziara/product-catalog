package data;

import util.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileComputerRepository implements ComputerRepository {
    private static FileComputerRepository instance;

    private FileComputerRepository() {
    }

    public static FileComputerRepository getInstance() {
        if (instance == null) {
            instance = new FileComputerRepository();
        }

        return instance;
    }

    @Override
    public List<Computer> getComputers() {
        List<Computer> computers = new ArrayList<>();

        try (BufferedReader catalogBufferReader = new BufferedReader(new FileReader(Constants.CATALOG_TXT_ASSET_PATH))) {
            String catalogLine;
            while ((catalogLine = catalogBufferReader.readLine()) != null) {
                String[] data = catalogLine.split(";", -1);

                if (data.length == 16) {
                    ComputerBuilder computerBuilder =
                            new ComputerBuilder()
                                    .setManufacturerName(data[0])
                                    .setDiagonalScreenSize(data[1])
                                    .setScreenResolution(data[2])
                                    .setScreenSurfaceType(data[3])
                                    .setTouchscreenFlag(data[4])
                                    .setCpu(data[5])
                                    .setNumberOfCpuCores(data[6])
                                    .setClockFrequency(data[7])
                                    .setRam(data[8])
                                    .setDiscSize(data[9])
                                    .setDiscType(data[10])
                                    .setGpu(data[11])
                                    .setGpuMemory(data[12])
                                    .setOperatingSystem(data[13])
                                    .setPhysicalDriveType(data[14]);

                    computers.add(computerBuilder.createComputer());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return computers;
    }

    @Override
    public boolean saveComputers(List<Computer> computers) {
        try (BufferedWriter catalogBufferWriter = new BufferedWriter(new FileWriter(Constants.CATALOG_TXT_ASSET_PATH))) {
            for (Computer computer : computers) {
                catalogBufferWriter.write(computer.toCsv());
                catalogBufferWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
