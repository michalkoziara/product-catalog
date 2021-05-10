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
        return new String[]{};
    }

    @Override
    public String[] getScreenSurfaceTypes() {
        return new String[]{};
    }

    @Override
    public int getNumberOfComputersByManufacturerName(String manufacturerName) {
        return 2;
    }

    @Override
    public Computer[] getComputersByScreenSurfaceType(String screenSurfaceType) {
        List<Computer> computers = databaseComputerRepository.getComputersByScreenSurfaceType(screenSurfaceType);

        return computers.toArray(new Computer[0]);
    }

    @Override
    public int getNumberOfComputersByDiagonalScreenSize(String diagonalScreenSize) {
        return 3;
    }
}