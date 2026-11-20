package Helpers.CSV;

//The exception to be thrown when CSVData is attempting to add a new row that doesn't match the headers of the rest of the data
public class CSVHeaderMismatchException extends RuntimeException {
    public CSVHeaderMismatchException(String message) {
        super(message);
    }
}
