package com.checkr.interviews;

import java.util.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class FundingRaised {
    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = readCsv();

        csvData = retrieveDataFromCsv(options, csvData, "company_name", 1);

        csvData = retrieveDataFromCsv(options, csvData, "city", 4);

        csvData = retrieveDataFromCsv(options, csvData, "state", 5);

        csvData = retrieveDataFromCsv(options, csvData, "round", 9);

        List<Map<String, String>> output = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            Map<String, String> mapped = new HashMap<>();
            retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
            output.add(mapped);
        }

        return output;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = readCsv();
        Map<String, String> mapped = new HashMap<> ();

        for (String[] csvDatum : csvData) {
            if (retrieveFundingFromCsvDatum(options, mapped, csvDatum, "company_name", 1)) continue;

            if (retrieveFundingFromCsvDatum(options, mapped, csvDatum, "city", 4)) continue;

            if (retrieveFundingFromCsvDatum(options, mapped, csvDatum, "state", 5)) continue;

            if (retrieveFundingFromCsvDatum(options, mapped, csvDatum, "round", 9)) continue;

            return mapped;
        }

        throw new NoSuchEntryException();
    }

    private static void retrieveFundingDetailsFromCsvDatum(Map<String, String> mapped, String[] csvDatum) {
        mapped.put("permalink", csvDatum[0]);
        mapped.put("company_name", csvDatum[1]);
        mapped.put("number_employees", csvDatum[2]);
        mapped.put("category", csvDatum[3]);
        mapped.put("city", csvDatum[4]);
        mapped.put("state", csvDatum[5]);
        mapped.put("funded_date", csvDatum[6]);
        mapped.put("raised_amount", csvDatum[7]);
        mapped.put("raised_currency", csvDatum[8]);
        mapped.put("round", csvDatum[9]);
    }

    private static boolean retrieveFundingFromCsvDatum(Map<String, String> options, Map<String, String> mapped, String[] csvDatum, String key, int index) {
        if (options.containsKey(key)) {
            if (csvDatum[index].equals(options.get(key))) {
                retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
            } else {
                return true;
            }
        }
        return false;
    }

    private static List<String[]> retrieveDataFromCsv(Map<String, String> options, List<String[]> csvData, String key, int index) {
        if (options.containsKey(key)) {
            List<String[]> results = new ArrayList<>();

            for (String[] csvDatum : csvData) {
                if (csvDatum[index].equals(options.get(key))) {
                    results.add(csvDatum);
                }
            }
            csvData = results;
        }
        return csvData;
    }

    private static List<String[]> readCsv() throws IOException {
        List<String[]> csvData = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
        String[] row = null;

        while ((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        csvData.remove(0); // remove header
        return csvData;
    }

    public static void main(String[] args) {
        try {
            Map<String, String> options = new HashMap<> ();
            options.put("company_name", "Facebook");
            options.put("round", "a");
            System.out.print(FundingRaised.where(options).size());
        } catch(IOException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }
}

class NoSuchEntryException extends Exception {}
