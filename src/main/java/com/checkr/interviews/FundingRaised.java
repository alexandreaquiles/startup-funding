package com.checkr.interviews;

import java.util.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class FundingRaised {

    public static final int PERMALINK_INDEX = CsvLayout.PERMALINK_INDEX.getIndex();
    public static final int COMPANY_NAME_INDEX = CsvLayout.COMPANY_NAME_INDEX.getIndex();
    public static final int NUMBER_OF_EMPLOYEES_INDEX = CsvLayout.NUMBER_OF_EMPLOYEES_INDEX.getIndex();
    public static final int CATEGORY_INDEX = CsvLayout.CATEGORY_INDEX.getIndex();
    public static final int CITY_INDEX = CsvLayout.CITY_INDEX.getIndex();
    public static final int STATE_INDEX = CsvLayout.STATE_INDEX.getIndex();
    public static final int FUNDED_DATE_INDEX = CsvLayout.FUNDED_DATE_INDEX.getIndex();
    public static final int RAISED_AMOUNT_INDEX = CsvLayout.RAISED_AMOUNT_INDEX.getIndex();
    public static final int RAISED_CURRENCY_INDEX = CsvLayout.RAISED_CURRENCY_INDEX.getIndex();
    public static final int ROUND_INDEX = CsvLayout.ROUND_INDEX.getIndex();

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = readCsv();
        csvData = retrieveDataFromCsv(options, csvData, "company_name", COMPANY_NAME_INDEX);
        csvData = retrieveDataFromCsv(options, csvData, "city", CITY_INDEX);
        csvData = retrieveDataFromCsv(options, csvData, "state", STATE_INDEX);
        csvData = retrieveDataFromCsv(options, csvData, "round", ROUND_INDEX);

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
            if (options.containsKey("company_name")) {
                if (csvDatum[COMPANY_NAME_INDEX].equals(options.get("company_name"))) {
                    retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
                } else {
                    continue;
                }
            }

            if (options.containsKey("city")) {
                if (csvDatum[CITY_INDEX].equals(options.get("city"))) {
                    retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
                } else {
                    continue;
                }
            }

            if (options.containsKey("state")) {
                if (csvDatum[STATE_INDEX].equals(options.get("state"))) {
                    retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
                } else {
                    continue;
                }
            }

            if (options.containsKey("round")) {
                if (csvDatum[ROUND_INDEX].equals(options.get("round"))) {
                    retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
                } else {
                    continue;
                }
            }

            return mapped;
        }

        throw new NoSuchEntryException();
    }

    private static void retrieveFundingDetailsFromCsvDatum(Map<String, String> mapped, String[] csvDatum) {
        mapped.put("permalink", csvDatum[PERMALINK_INDEX]);
        mapped.put("company_name", csvDatum[COMPANY_NAME_INDEX]);
        mapped.put("number_employees", csvDatum[NUMBER_OF_EMPLOYEES_INDEX]);
        mapped.put("category", csvDatum[CATEGORY_INDEX]);
        mapped.put("city", csvDatum[CITY_INDEX]);
        mapped.put("state", csvDatum[STATE_INDEX]);
        mapped.put("funded_date", csvDatum[FUNDED_DATE_INDEX]);
        mapped.put("raised_amount", csvDatum[RAISED_AMOUNT_INDEX]);
        mapped.put("raised_currency", csvDatum[RAISED_CURRENCY_INDEX]);
        mapped.put("round", csvDatum[ROUND_INDEX]);
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
