package utilities;

import com.opencsv.CSVWriter;
import output.Out;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CSVParser {

    private void writeCsv(List<Out> out, String[] headers) {
        String fileName = "SCRAPER_OUTPUT_" + LocalDate.now() + ".csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {

            writer.writeNext(headers);

            for (Out o : out) {

                String author = o.getAuthorName();
                String title = o.getTitle();
               // String contents = o.getContents();
               // LocalDate date = o.getDate();  etc etc. Add whatever content you need in the CSV

                String[] row = new String[]{ author, title };

                writer.writeNext(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeCSV(List<Out> out, String[] headers) {

        if (out.isEmpty()) {
            System.out.println("NO DATA FOUND");
        } else {
            writeCsv(out, headers);
        }
    }
}
