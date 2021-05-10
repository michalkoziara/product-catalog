package services;

import data.Computer;
import data.DatabaseComputerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "services.ProductSoapService")
public class ProductSoapServiceImpl implements ProductSoapService {
    private DatabaseComputerRepository databaseComputerRepository;

    @PostConstruct
    @WebMethod(exclude = true)
    public void init() {
        databaseComputerRepository = DatabaseComputerRepository.getInstance();
    }

    @Override
    public String[] getManufacturerNames() {
        List<Computer> computers = databaseComputerRepository.getComputers();

        return computers
                .stream()
                .map(Computer::getManufacturerName)
                .distinct()
                .filter((String manufacturerName) -> !manufacturerName.isBlank())
                .toArray(String[]::new);
    }

    @Override
    public String[] getScreenSurfaceTypes() {
        List<Computer> computers = databaseComputerRepository.getComputers();

        return computers
                .stream()
                .map(Computer::getScreenSurfaceType)
                .distinct()
                .filter((String screenSurfaceType) -> !screenSurfaceType.isBlank())
                .toArray(String[]::new);
    }

    @Override
    public long getNumberOfComputersByManufacturerName(String manufacturerName) {
        return databaseComputerRepository.countComputersByScreenSurfaceType(manufacturerName);
    }

    @Override
    public Computer[] getComputersByScreenSurfaceType(String screenSurfaceType) {
        List<Computer> computers = databaseComputerRepository.getComputersByScreenSurfaceType(screenSurfaceType);

        return computers.toArray(new Computer[0]);
    }

    @Override
    public long getNumberOfComputersByDisplayAspectRatio(String displayAspectRatio) {
        List<Computer> computers = databaseComputerRepository.getComputers();

        return computers
                .stream()
                .map(Computer::getScreenResolution)
                .filter((String screenResolution) -> !screenResolution.isBlank())
                .filter((String screenResolution) -> {
                    String[] screenResolutionValues = screenResolution.split("x");
                    String[] displayAspectRatioValues = displayAspectRatio.split(":");

                    if (screenResolutionValues.length != 2 || displayAspectRatioValues.length != 2) {
                        return false;
                    }

                    double screenResolutionWidth = Double.parseDouble(screenResolutionValues[0]);
                    double screenResolutionHeight = Double.parseDouble(screenResolutionValues[1]);
                    double displayAspectRatioWidth = Double.parseDouble(displayAspectRatioValues[0]);
                    double displayAspectRatioHeight = Double.parseDouble(displayAspectRatioValues[1]);

                    double screenResolutionScaleRatio =
                            Math.round(screenResolutionWidth / screenResolutionHeight * 100.0) / 100.0;
                    double displayScaleRatio =
                            Math.round(displayAspectRatioWidth / displayAspectRatioHeight * 100.0) / 100.0;

                    return screenResolutionScaleRatio == displayScaleRatio;
                })
                .count();
    }
}