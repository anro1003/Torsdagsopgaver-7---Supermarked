package Helpers.CSV;

import java.util.ArrayList;
import java.util.Arrays;

public class CSVData {
    private ArrayList<CSVRow> rows = new ArrayList<>();

    //We need to confirm that all rows have the same header - So we retrieve the header of the first row (if any), and use that to
    //check the others
    public CSVData(ArrayList<CSVRow> rows)
    {
        this.rows.addAll(rows);
    }

    public CSVData()
    {

    }

    public void addRow(CSVRow row) throws CSVHeaderMismatchException
    {
        if(!this.rows.isEmpty())
        {
            if(!this.rows.getFirst().headersToArrayList().equals(row.headersToArrayList()))
            {
                throw new CSVHeaderMismatchException("CSV header on added row, doesn't match the dataset... - Incomming headers: [" + row.headersToArrayList().toString() + "] - existing headers [" + getHeaders().toString() + "]");
            }
        }
        rows.add(row);
    }

    public ArrayList<String> getHeaders()
    {
        if(this.rows.isEmpty())
        {
            return new ArrayList<>();
        }
        return new ArrayList<>(this.rows.getFirst().headersToArrayList());
    }

    public ArrayList<CSVRow> getRows()
    {
        return new ArrayList<>(rows);
    }

    public CSVRow getDataAt(int rowIndex) throws IndexOutOfBoundsException
    {
        return new CSVRow(rows.get(rowIndex));
    }

    public int getHeaderLength()
    {
        return getHeaders().size();
    }

    public int getRowCount()
    {
        return rows.size();
    }
}
