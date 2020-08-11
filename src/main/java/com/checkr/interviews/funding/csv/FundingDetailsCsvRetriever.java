package com.checkr.interviews.funding.csv;

import java.util.HashMap;
import java.util.Map;

import static com.checkr.interviews.funding.csv.FundingCsvLayout.*;

public class FundingDetailsCsvRetriever {
    public static Map<String, String> retrieveFundingDetailsFromCsvDatum(String[] csvDatum) {
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
}
