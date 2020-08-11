package com.checkr.interviews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.checkr.interviews.CsvLayout.*;

public class FundingRaised {

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = CsvReader.readCsv("startup_funding.csv");
        csvData = retrieveDataFromCsv(options, csvData, "company_name", COMPANY_NAME_INDEX.getIndex());
        csvData = retrieveDataFromCsv(options, csvData, "city", CITY_INDEX.getIndex());
        csvData = retrieveDataFromCsv(options, csvData, "state", STATE_INDEX.getIndex());
        csvData = retrieveDataFromCsv(options, csvData, "round", ROUND_INDEX.getIndex());

        List<Map<String, String>> output = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            Map<String, String> mapped = new HashMap<>();
            retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
            output.add(mapped);
        }

        return output;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = CsvReader.readCsv("startup_funding.csv");
        Map<String, String> mapped = new HashMap<> ();

        for (String[] csvDatum : csvData) {
            if (options.containsKey("company_name")) {
                if (csvDatum[COMPANY_NAME_INDEX.getIndex()].equals(options.get("company_name"))) {
                    retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
                } else {
                    continue;
                }
            }

            if (options.containsKey("city")) {
                if (csvDatum[CITY_INDEX.getIndex()].equals(options.get("city"))) {
                    retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
                } else {
                    continue;
                }
            }

            if (options.containsKey("state")) {
                if (csvDatum[STATE_INDEX.getIndex()].equals(options.get("state"))) {
                    retrieveFundingDetailsFromCsvDatum(mapped, csvDatum);
                } else {
                    continue;
                }
            }

            if (options.containsKey("round")) {
                if (csvDatum[ROUND_INDEX.getIndex()].equals(options.get("round"))) {
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
        mapped.put("permalink", csvDatum[PERMALINK_INDEX.getIndex()]);
        mapped.put("company_name", csvDatum[COMPANY_NAME_INDEX.getIndex()]);
        mapped.put("number_employees", csvDatum[NUMBER_OF_EMPLOYEES_INDEX.getIndex()]);
        mapped.put("category", csvDatum[CATEGORY_INDEX.getIndex()]);
        mapped.put("city", csvDatum[CITY_INDEX.getIndex()]);
        mapped.put("state", csvDatum[STATE_INDEX.getIndex()]);
        mapped.put("funded_date", csvDatum[FUNDED_DATE_INDEX.getIndex()]);
        mapped.put("raised_amount", csvDatum[RAISED_AMOUNT_INDEX.getIndex()]);
        mapped.put("raised_currency", csvDatum[RAISED_CURRENCY_INDEX.getIndex()]);
        mapped.put("round", csvDatum[ROUND_INDEX.getIndex()]);
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
