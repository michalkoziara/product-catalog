package com.michalkoziara;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ComputerRepository computerRepository = new FileComputerRepository();
        List<Computer> computers = computerRepository.getComputers();

        Printer printer = new Printer();
        printer.printComputers(computers);
    }
}
