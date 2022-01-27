package com.auto.helper;

import com.auto.model.Statistic;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvHelper {
    public static String TIME_FORMAT="YYYY-MM-DD";
    static SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "county_fips",
            "county_name",
            "state_name",
            "date",
            "county_vmt",
            "baseline_jan_vmt",
            "percent_change_from_jan",
            "mean7_county_vmt",
            "mean7_percent_change_from_jan",
            "date_at_low",
            "mean7_county_vmt_at_low",
            "percent_change_from_low"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Statistic> csvToStatistic(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Statistic> tutorials = new ArrayList<Statistic>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Statistic tutorial = new Statistic();
                tutorial.setCounty_fips(Integer.parseInt(csvRecord.get("county_fips")));
                tutorial.setCountry_name(csvRecord.get("county_name"));
                tutorial.setState_name(csvRecord.get("state_name"));
                tutorial.setDate(sdf.parse(csvRecord.get("date")));
                tutorial.setCounty_vmt(Integer.parseInt(csvRecord.get("county_vmt")));
                tutorial.setBaseline_jan_vmt(Integer.parseInt(csvRecord.get("baseline_jan_vmt")));
                tutorial.setPercentageChangeFromJan(Double.parseDouble(csvRecord.get("percent_change_from_jan")));
                tutorial.setMeanCountryVmt(Double.parseDouble(csvRecord.get("mean7_county_vmt")));
                tutorial.setMeanPercentChangeFromJan(Double.parseDouble(csvRecord.get("mean7_percent_change_from_jan")));
                tutorial.setDateAtLow(sdf.parse(csvRecord.get("date_at_low")));
                tutorial.setMeanCountryVmtAtLow(Double.parseDouble(csvRecord.get("mean7_county_vmt_at_low")));
                tutorial.setPercentChangeFromLow(Double.parseDouble(csvRecord.get("percent_change_from_low")));



                tutorials.add(tutorial);
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException("fail to parse date CSV file: " + e.getMessage());

        }
    }

}