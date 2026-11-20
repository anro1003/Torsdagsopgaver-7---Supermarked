package Helpers.CSV;

import java.util.ArrayList;
import java.util.Arrays;

public class CSVParser {

    public static CSVData parseRawCSV(String data, char separator)
    {
        String[] lines = data.split("\n");
        ArrayList<String> headers = parseHeaders(lines[0],separator);

        CSVData csvData = new CSVData();

        for(int i = 1; i < lines.length; i++)
        {
            csvData.addRow(new CSVRow(headers,lines[i],separator));
        }
        return csvData;
    }

    public static ArrayList<String> parseHeaders(String rawCSVHeader, char separator)
    {
        return new ArrayList<>(Arrays.stream(rawCSVHeader.split(""+separator)).toList());
    }

}
