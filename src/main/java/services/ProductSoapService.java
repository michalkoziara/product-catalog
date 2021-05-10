package services;

import data.Computer;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, style = SOAPBinding.Style.RPC)
public interface ProductSoapService {
    @WebMethod
    String[] getManufacturerNames();

    @WebMethod
    String[] getScreenSurfaceTypes();

    @WebMethod
    int getNumberOfComputersByManufacturerName(@WebParam(name = "manufacturerName") String manufacturerName);

    @WebMethod
    Computer[] getComputersByScreenSurfaceType(@WebParam(name = "screenSurfaceType") String screenSurfaceType);

    @WebMethod
    int getNumberOfComputersByDiagonalScreenSize(@WebParam(name = "diagonalScreenSize") String diagonalScreenSize);
}