package com.checkr.interviews.csv;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<String[]> readCsv(String fileName) throws IOException {
        List<String[]> csvData = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(fileName));
        String[] row = null;

        while ((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        removeCsvHeader(csvData);
        return csvData;
    }

    private static void removeCsvHeader(List<String[]> csvData) {
        csvData.remove(0);
    }

}
