package com.checkr.interviews;

import com.checkr.interviews.csv.CsvLayout;
import com.checkr.interviews.csv.CsvReader;
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

            if (filterCsv(options, mapped, csvDatum, COMPANY_NAME)) continue;

            if (filterCsv(options, mapped, csvDatum, CITY)) continue;

            if (filterCsv(options, mapped, csvDatum, STATE)) continue;

            if (filterCsv(options, mapped, csvDatum, ROUND)) continue;

            return mapped;
        }

        throw new NoSuchEntryException();
    }

    private static boolean filterCsv(Map<String, String> options, Map<String, String> mapped, String[] csvDatum, CsvLayout csvLayout) {
        String csvLayoutKey = csvLayout.key();
        int csvLayoutIndex = csvLayout.index();
        if (options.containsKey(csvLayoutKey)) {
            String searchValue = options.get(csvLayoutKey);
            if (csvDatum[csvLayoutIndex].equals(searchValue)) {
                mapped.putAll(FundingDetailsCsvRetriever.retrieveFundingDetailsFromCsvDatum(csvDatum));
            } else {
                return true;
            }
        }
        return false;
    }

    private static List<String[]> retrieveDataFromCsv(Map<String, String> options, List<String[]> csvData, CsvLayout csvLayout) {
        String csvLayoutKey = csvLayout.key();
        int csvLayoutIndex = csvLayout.index();
        if (options.containsKey(csvLayoutKey)) {
            String searchValue = options.get(csvLayoutKey);

            List<String[]> results = new ArrayList<>();

            for (String[] csvDatum : csvData) {
                if (csvDatum[csvLayoutIndex].equals(searchValue)) {
                    results.add(csvDatum);
                }
            }
            return results;
        }
        return csvData;
    }

}

