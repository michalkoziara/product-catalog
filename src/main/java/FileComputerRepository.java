import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileComputerRepository implements ComputerRepository {
    private static final String CATALOG_ASSET_DIRECTORY = "./resources/katalog.txt";

    @Override
    public List<Computer> getComputers() {
        List<Computer> computers = new ArrayList<>();

        try (BufferedReader catalogBufferReader = new BufferedReader(new FileReader(CATALOG_ASSET_DIRECTORY))) {
            String catalogLine;
            while ((catalogLine = catalogBufferReader.readLine()) != null) {
                String[] data = catalogLine.split(";", -1);

                if (data.length == 16) {
                    ComputerBuilder computerBuilder = new ComputerBuilder();
                    computerBuilder.setManufacturerName(data[0]);
                    computerBuilder.setDiagonalScreenSize(data[1]);
                    computerBuilder.setScreenResolution(data[2]);
                    computerBuilder.setScreenSurfaceType(data[3]);
                    computerBuilder.setTouchscreenFlag(data[4]);
                    computerBuilder.setCpu(data[5]);
                    computerBuilder.setNumberOfCpuCores(data[6]);
                    computerBuilder.setClockFrequency(data[7]);
                    computerBuilder.setRam(data[8]);
                    computerBuilder.setDiscSize(data[9]);
                    computerBuilder.setDiscType(data[10]);
                    computerBuilder.setGpu(data[11]);
                    computerBuilder.setGpuMemory(data[12]);
                    computerBuilder.setOperatingSystem(data[13]);
                    computerBuilder.setPhysicalDriveType(data[14]);

                    computers.add(computerBuilder.createComputer());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return computers;
    }

    @Override
    public boolean saveComputers(List<Computer> computers) {
        try (BufferedWriter catalogBufferWriter = new BufferedWriter(new FileWriter(CATALOG_ASSET_DIRECTORY))) {
            for (Computer computer : computers) {
                catalogBufferWriter.write(computer.toCsv());
                catalogBufferWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
