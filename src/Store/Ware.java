package Store;

import java.util.Date;

public class Ware {
    private String eanNumber;
    private String name;
    private Date priceDate;
    private double price;
    private boolean isBudgetBrand;
    private double quantity;
    private WareUnits unit;
    private boolean isWeighted;
    private boolean isBio;
    private boolean isAvailable;

    public Ware(String eanNumber, String name, Date priceDate, double price, boolean isBudgetBrand, double quantity, WareUnits unit, boolean isWeighted, boolean isBio, boolean isAvailable)
    {
        this.eanNumber = eanNumber;
        this.name = name;
        this.priceDate = priceDate;
        this.price = price;
        this.isBudgetBrand = isBudgetBrand;
        this.quantity = quantity;
        this.unit = unit;
        this.isWeighted = isWeighted;
        this.isBio = isBio;
        this.isAvailable = isAvailable;
    }

    //Just to make the construction of CartWare easier
    public Ware(Ware ware)
    {
        this.eanNumber = ware.eanNumber;
        this.name = ware.name;
        this.priceDate = ware.priceDate;
        this.price = ware.price;
        this.isBudgetBrand = ware.isBudgetBrand;
        this.quantity = ware.quantity;
        this.unit = ware.unit;
        this.isWeighted = ware.isWeighted;
        this.isBio = ware.isBio;
        this.isAvailable = ware.isAvailable;
    }


    public String getEanNumber() {
        return eanNumber;
    }


    public String getName() {
        return name;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBudgetBrand() {
        return isBudgetBrand;
    }

    public double getQuantity() {
        return quantity;
    }

    public WareUnits getUnit() {
        return unit;
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public boolean isBio() {
        return isBio;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
