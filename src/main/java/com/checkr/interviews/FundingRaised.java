package com.checkr.interviews;

import com.checkr.interviews.csv.CsvReader;
import com.checkr.interviews.funding.csv.FundingCsvLayout;
import com.checkr.interviews.funding.csv.FundingDetailsCsvRetriever;
import com.checkr.interviews.funding.csv.NoSuchEntryException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.checkr.interviews.funding.csv.FundingCsvLayout.*;

public class FundingRaised {

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = CsvReader.readCsv("startup_funding.csv");
        csvData = retrieveDataFromCsv(options, csvData, COMPANY_NAME);
        csvData = retrieveDataFromCsv(options, csvData, CITY);
        csvData = retrieveDataFromCsv(options, csvData, STATE);
        csvData = retrieveDataFromCsv(options, csvData, ROUND);

        List<Map<String, String>> output = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            output.add(FundingDetailsCsvRetriever.retrieveFundingDetailsFromCsvDatum(csvDatum));
        }

        return output;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = CsvReader.readCsv("startup_funding.csv");
        Map<String, String> mapped = new HashMap<> ();

        for (String[] csvDatum : csvData) {
            if (options.containsKey(COMPANY_NAME.key())) {
                if (csvDatum[COMPANY_NAME.index()].equals(options.get(COMPANY_NAME.key()))) {
                    mapped.putAll(FundingDetailsCsvRetriever.retrieveFundingDetailsFromCsvDatum(csvDatum));
                } else {
                    continue;
                }
            }

            if (options.containsKey(CITY.key())) {
                if (csvDatum[CITY.index()].equals(options.get(CITY.key()))) {
                    mapped.putAll(FundingDetailsCsvRetriever.retrieveFundingDetailsFromCsvDatum(csvDatum));
                } else {
                    continue;
                }
            }

            if (options.containsKey(STATE.key())) {
                if (csvDatum[STATE.index()].equals(options.get(STATE.key()))) {
                    mapped.putAll(FundingDetailsCsvRetriever.retrieveFundingDetailsFromCsvDatum(csvDatum));
                } else {
                    continue;
                }
            }

            if (options.containsKey(ROUND.key())) {
                if (csvDatum[ROUND.index()].equals(options.get(ROUND.key()))) {
                    mapped.putAll(FundingDetailsCsvRetriever.retrieveFundingDetailsFromCsvDatum(csvDatum));
                } else {
                    continue;
                }
            }

            return mapped;
        }

        throw new NoSuchEntryException();
    }

    private static List<String[]> retrieveDataFromCsv(Map<String, String> options, List<String[]> csvData, FundingCsvLayout csvLayout) {
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

