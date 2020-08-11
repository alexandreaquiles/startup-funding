package com.checkr.interviews;

import java.util.*;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class FundingRaised {
    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = readCsv();

        if(options.containsKey("company_name")) {
            List<String[]> results = new ArrayList<>();

            for (String[] csvDatum : csvData) {
                if (csvDatum[1].equals(options.get("company_name"))) {
                    results.add(csvDatum);
                }
            }
            csvData = results;
        }

        if(options.containsKey("city")) {
            List<String[]> results = new ArrayList<>();

            for (String[] csvDatum : csvData) {
                if (csvDatum[4].equals(options.get("city"))) {
                    results.add(csvDatum);
                }
            }
            csvData = results;
        }

        if(options.containsKey("state")) {
            List<String[]> results = new ArrayList<>();

            for (String[] csvDatum : csvData) {
                if (csvDatum[5].equals(options.get("state"))) {
                    results.add(csvDatum);
                }
            }
            csvData = results;
        }

        if(options.containsKey("round")) {
            List<String[]> results = new ArrayList<>();

            for (String[] csvDatum : csvData) {
                if (csvDatum[9].equals(options.get("round"))) {
                    results.add(csvDatum);
                }
            }
            csvData = results;
        }

        List<Map<String, String>> output = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            Map<String, String> mapped = new HashMap<>();
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
            output.add(mapped);
        }

        return output;
    }

    private static List<String[]> readCsv() throws IOException {
        List<String[]> csvData = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
        String[] row = null;

        while ((row = reader.readNext()) != null) {
            csvData.add(row);
        }

        reader.close();
        csvData.remove(0);
        return csvData;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = readCsv();
        Map<String, String> mapped = new HashMap<> ();

        for (String[] csvDatum : csvData) {
            if (options.containsKey("company_name")) {
                if (csvDatum[1].equals(options.get("company_name"))) {
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
                } else {
                    continue;
                }
            }

            if (options.containsKey("city")) {
                if (csvDatum[4].equals(options.get("city"))) {
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
                } else {
                    continue;
                }
            }

            if (options.containsKey("state")) {
                if (csvDatum[5].equals(options.get("state"))) {
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
                } else {
                    continue;
                }
            }

            if (options.containsKey("round")) {
                if (csvDatum[9].equals(options.get("round"))) {
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
                } else {
                    continue;
                }
            }

            return mapped;
        }

        throw new NoSuchEntryException();
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
