package data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Printer {

    void printManufacturerStatistics(List<Computer> computers) {
        final String manufacturerLabel = "Nazwa producenta";
        final String numberOfComputersLabel = "Liczba komputerow";

        int manufacturerNameSize = manufacturerLabel.length();
        int numberOfComputersSize = numberOfComputersLabel.length();

        Map<String, Integer> numberOfComputersByManufacturer = new TreeMap<>();
        for (Computer computer : computers) {
            String manufacturerName = computer.getManufacturerName();
            if (!numberOfComputersByManufacturer.containsKey(manufacturerName)) {
                numberOfComputersByManufacturer.put(manufacturerName, 1);
            } else {
                numberOfComputersByManufacturer.replace(
                        manufacturerName,
                        numberOfComputersByManufacturer.get(manufacturerName) + 1
                );
            }

            manufacturerNameSize = Math.max(manufacturerNameSize, manufacturerName.length());
            numberOfComputersSize = Math.max(
                    numberOfComputersSize,
                    numberOfComputersByManufacturer.get(manufacturerName).toString().length()
            );
        }

        final int rowSize = manufacturerNameSize + numberOfComputersSize + 7;
        final int calculatedManufacturerNameSize = manufacturerNameSize;

        System.out.println("Liczba komputerow poszczegolnych producentow:");

        printRowLine(rowSize);
        System.out.print("| ");
        System.out.print(addPadding(manufacturerLabel, calculatedManufacturerNameSize));
        System.out.print(" | ");
        System.out.print(addPadding(numberOfComputersLabel, numberOfComputersSize));
        System.out.print(" |");
        System.out.println();
        printRowLine(rowSize);

        for (Map.Entry<String, Integer> entry : numberOfComputersByManufacturer.entrySet()) {
            System.out.print("| ");
            System.out.print(addPadding(entry.getKey(), calculatedManufacturerNameSize));
            System.out.print(" | ");
            System.out.print(addPadding(entry.getValue().toString(), numberOfComputersSize));
            System.out.print(" |");
            System.out.println();
            printRowLine(rowSize);
        }
    }

    void printComputers(List<Computer> computers) {
        String[] columnLabelsFirstRow = new String[]{
                "",
                "Nazwa",
                "Przekatna",
                "Rozdzielczosc",
                "Rodzaj",
                "Czy ekran",
                "Nazwa",
                "Liczba",
                "Predkosc",
                "Wielkosc",
                "Pojemnosc",
                "Rodzaj",
                "Nazwa",
                "Pamiec",
                "Nazwa",
                "Rodzaj"
        };

        String[] columnLabelsSecondRow = new String[]{
                "LP.",
                "producenta",
                "ekranu",
                "ekranu",
                "powierzchni",
                "jest",
                "procesora",
                "rdzeni",
                "taktowania",
                "pamieci",
                "dysku",
                "dysku",
                "ukladu",
                "ukladu",
                "systemu",
                "napedu"
        };

        String[] columnLabelsThirdRow = new String[]{
                "",
                "",
                "",
                "",
                "ekranu",
                "dotykowy",
                "",
                "fizycznych",
                "MHZ",
                "RAM",
                "",
                "",
                "graficznego",
                "graficznego",
                "operacyjnego",
                "fizycznego w komputerze"
        };

        int numberOfColumns = columnLabelsFirstRow.length;

        String[][] table = new String[computers.size() + 3][numberOfColumns];
        table[0] = columnLabelsFirstRow;
        table[1] = columnLabelsSecondRow;
        table[2] = columnLabelsThirdRow;

        int numberOfRows = table.length;

        for (int i = 0; i < computers.size(); i++) {
            List<String> computerParameters = computers.get(i).getParameters();
            computerParameters.add(0, String.valueOf(i + 1));
            table[i + 3] = computerParameters.toArray(new String[0]);
        }

        int[] columnSize = calculateColumnSize(table);

        int rowSize = Arrays.stream(columnSize).reduce(Integer::sum).orElse(0);
        rowSize += numberOfColumns + 1;

        printRowLine(rowSize);

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print("|");
                System.out.print(addPadding(table[i][j], columnSize[j]));
            }
            System.out.print("|");
            System.out.println();

            if (i > 1) {
                printRowLine(rowSize);
            }
        }

        printRowLine(rowSize);
    }

    private void printRowLine(int rowSize) {
        System.out.println("-".repeat(rowSize));
    }

    private String addPadding(String text, int padding) {
        int numberOfSpaces = padding - text.length();
        return " ".repeat(numberOfSpaces / 2 + numberOfSpaces % 2) + text + " ".repeat(numberOfSpaces / 2);
    }

    private int[] calculateColumnSize(String[][] table) {
        int numberOfColumns = Arrays.stream(table).mapToInt(value -> value.length).reduce(Math::max).orElse(0);

        int[] columnSize = new int[numberOfColumns];
        Arrays.fill(columnSize, 0);

        for (String[] row : table) {
            for (int j = 0; j < row.length; j++) {
                columnSize[j] = Math.max(columnSize[j], row[j].length());
            }
        }

        return columnSize;
    }
}
