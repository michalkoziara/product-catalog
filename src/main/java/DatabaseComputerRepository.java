import java.util.ArrayList;
import java.util.List;

public class DatabaseComputerRepository implements ComputerRepository {

    @Override
    public List<Computer> getComputers() {
        List<Computer> computers = new ArrayList<>();

        return computers;
    }

    @Override
    public boolean saveComputers(List<Computer> computers) {
        return true;
    }
}