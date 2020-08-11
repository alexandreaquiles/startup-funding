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
        csvData = retrieveDataFromCsv(options, csvData, COMPANY_NAME);
        csvData = retrieveDataFromCsv(options, csvData, CITY);
        csvData = retrieveDataFromCsv(options, csvData, STATE);
        csvData = retrieveDataFromCsv(options, csvData, ROUND);

        List<Map<String, String>> output = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            output.add(retrieveFundingDetailsFromCsvDatum(csvDatum));
        }

        return output;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = CsvReader.readCsv("startup_funding.csv");
        Map<String, String> mapped = new HashMap<> ();

        for (String[] csvDatum : csvData) {
            if (options.containsKey(COMPANY_NAME.key())) {
                if (csvDatum[COMPANY_NAME.index()].equals(options.get(COMPANY_NAME.key()))) {
                    mapped.putAll(retrieveFundingDetailsFromCsvDatum(csvDatum));
                } else {
                    continue;
                }
            }

            if (options.containsKey(CITY.key())) {
                if (csvDatum[CITY.index()].equals(options.get(CITY.key()))) {
                    mapped.putAll(retrieveFundingDetailsFromCsvDatum(csvDatum));
                } else {
                    continue;
                }
            }

            if (options.containsKey(STATE.key())) {
                if (csvDatum[STATE.index()].equals(options.get(STATE.key()))) {
                    mapped.putAll(retrieveFundingDetailsFromCsvDatum(csvDatum));
                } else {
                    continue;
                }
            }

            if (options.containsKey(ROUND.key())) {
                if (csvDatum[ROUND.index()].equals(options.get(ROUND.key()))) {
                    mapped.putAll(retrieveFundingDetailsFromCsvDatum(csvDatum));
                } else {
                    continue;
                }
            }

            return mapped;
        }

        throw new NoSuchEntryException();
    }

    private static Map<String, String> retrieveFundingDetailsFromCsvDatum(String[] csvDatum) {
        Map<String, String> mapped = new HashMap<>();
        mapped.put("permalink", csvDatum[PERMALINK.index()]);
        mapped.put("company_name", csvDatum[COMPANY_NAME.index()]);
        mapped.put("number_employees", csvDatum[NUMBER_OF_EMPLOYEES.index()]);
        mapped.put("category", csvDatum[CATEGORY.index()]);
        mapped.put("city", csvDatum[CITY.index()]);
        mapped.put("state", csvDatum[STATE.index()]);
        mapped.put("funded_date", csvDatum[FUNDED_DATE.index()]);
        mapped.put("raised_amount", csvDatum[RAISED_AMOUNT.index()]);
        mapped.put("raised_currency", csvDatum[RAISED_CURRENCY.index()]);
        mapped.put("round", csvDatum[ROUND.index()]);
        return mapped;
    }

    private static List<String[]> retrieveDataFromCsv(Map<String, String> options, List<String[]> csvData, CsvLayout csvLayout) {
        String csvLayoutKey = csvLayout.key();
        if (options.containsKey(csvLayoutKey)) {
            String searchValue = options.get(csvLayoutKey);

            List<String[]> results = new ArrayList<>();

            for (String[] csvDatum : csvData) {
                if (csvDatum[csvLayout.index()].equals(searchValue)) {
                    results.add(csvDatum);
                }
            }
            csvData = results;
        }
        return csvData;
    }

}

class NoSuchEntryException extends Exception {}
