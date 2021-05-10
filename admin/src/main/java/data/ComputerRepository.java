package data;

import java.util.List;

public interface ComputerRepository {
    List<Computer> getComputers();
    boolean saveComputers(List<Computer> computers);
}
