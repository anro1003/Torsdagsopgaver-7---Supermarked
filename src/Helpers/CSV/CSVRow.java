package Helpers.CSV;

import java.util.ArrayList;
import java.util.HashMap;

public class CSVRow {
    private HashMap<String,String> rowData = new HashMap<>();

    public CSVRow(ArrayList<String> headers, String rawRowData, char separator)
    {
        String[] lines = rawRowData.split(""+ separator);

        for(int i = 0; i < lines.length; i++)
        {
            rowData.put(headers.get(i),lines[i]);
        }
    }

    public CSVRow(CSVRow row) //I don't know if we have learned about copy contructors yet, sooooo... XDD
    {
        this.rowData = new HashMap<>(row.rowData);
    }

    public String getData(String header)
    {
        return rowData.get(header);
    }

    public ArrayList<String> headersToArrayList()
    {
        return new ArrayList<>(rowData.keySet());
    }
}
