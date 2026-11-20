package Store;

import Helpers.CSV.CSVData;
import Helpers.CSV.CSVParser;
import Helpers.CSV.CSVRow;
import Helpers.FileIO;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Store {
    private ArrayList<Ware> wares = new ArrayList<>();
    private ArrayList<Ware> salesWares = new ArrayList<>();

    public Store(String waresCsvPath, String waresSalesCsvPath)
    {
        CSVData wareCsvData;
        CSVData wareSalesCsvData;

        try
        {
            wareCsvData = CSVParser.parseRawCSV(FileIO.readData(waresCsvPath),';');
            wareSalesCsvData = CSVParser.parseRawCSV(FileIO.readData(waresSalesCsvPath),';');

            loadDataFromCSVData(wareCsvData,this.wares);
            loadDataFromCSVData(wareSalesCsvData,this.salesWares);

        }
        catch (FileNotFoundException e)
        {
            System.err.println("Failed to load store wares from CSV: " + waresCsvPath + " : File Not Found...");
        }

    }

    private void loadDataFromCSVData(CSVData data, ArrayList<Ware> targetList)
    {
        for(int i = 0; i < data.getRowCount(); i++)
        {
            //id;name;priceDate;price;isBudgetBrand;quantity;unit;isWeighted;isBio;isAvailable
            CSVRow currentRow = data.getDataAt(i);
            try {
                targetList.add(new Ware(
                        currentRow.getData("id"),
                        currentRow.getData("name"),
                        new SimpleDateFormat("yyyy-MM-dd").parse(currentRow.getData("priceDate")),
                        Double.parseDouble(currentRow.getData("price")),
                        Boolean.parseBoolean(currentRow.getData("isBudgetBrand")),
                        Double.parseDouble(currentRow.getData("quantity")),
                        WareUnits.fromString(currentRow.getData("unit") + "."),
                        Boolean.parseBoolean(currentRow.getData("isWeighted")),
                        Boolean.parseBoolean(currentRow.getData("isBio")),
                        Boolean.parseBoolean(currentRow.getData("isAvailable"))
                ));
            }
            catch (NumberFormatException e)
            {
                System.err.println("Failed to parse a number from a ware: price: " + currentRow.getData("price") + " - quantity: " + currentRow.getData("quantity"));
            }
            catch (ParseException e)
            {
                System.err.println("Failed to parse the priceDate on ware... - Value: " + currentRow.getData("priceDate"));
            }
        }
    }

    public void purchase(ArrayList<Ware> cart)
    {
        HashMap<String, CartWare> printableCart = new HashMap<>();
        //Do duplicate search

        for(Ware ware : cart)
        {

            Ware saleWare = getWareOnSaleById(ware.getEanNumber());
            CartWare cartWare = new CartWare(ware,ware.getPrice());
            if(saleWare != null)
            {
                if (saleWare.getPriceDate().after(ware.getPriceDate()))
                {
                    cartWare = new CartWare(ware,saleWare.getPrice());
                }
            }

            if(printableCart.containsKey(cartWare.getEanNumber()))
            {
                printableCart.get(ware.getEanNumber()).addToCart();
                continue;
            }
            printableCart.put(cartWare.getEanNumber(),cartWare);
        }

        prettyPrintReciept(printableCart.values().stream().toList());

    }

    private void prettyPrintReciept(List<CartWare> cartWares)
    {
        System.out.println("===== Here is your reciept =====");
        for(CartWare cartWare : cartWares)
        {
            System.out.println(cartWare.toString() + "kr");
            if(cartWare.isDiscounted())
            {
                System.out.println(cartWare.getDiscountString() + "kr");
            }
        }
        double rawTotal = calculateTotal(cartWares);
        double vat = rawTotal*0.25;
        System.out.println("Total: " + rawTotal);
        System.out.println("VAT (25%) makes up: " + vat + "kr");
        System.out.println("===========");
    }

    public Ware getWareById(String eanNum)
    {
        List<Ware> results = this.wares.stream().filter(w -> w.getEanNumber().equals(eanNum)).toList();
        return results.isEmpty() ? null : results.getFirst();
    }

    public Ware getWareOnSaleById(String eanNum)
    {
        List<Ware> results = this.salesWares.stream().filter(w -> w.getEanNumber().equals(eanNum)).toList();
        return results.isEmpty() ? null : results.getFirst();
    }

    private double calculateTotal(List<CartWare> cart)
    {
        double total = 0.0d;
        for(CartWare ware : cart)
        {
            total += ware.getPrice();
        }
        return total;
    }

}
