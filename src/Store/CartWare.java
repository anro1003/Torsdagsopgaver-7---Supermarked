package Store;

public class CartWare extends Ware {

    private double discountPrice;
    private int inCartCount = 1;
    public CartWare(Ware ware, double discountPrice)
    {
        //Copy all properties of the original ware
        super(ware);
        this.discountPrice = discountPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public boolean isDiscounted()
    {
        return discountPrice != super.getPrice();
    }

    public void addToCart()
    {
        inCartCount++;
    }

    @Override
    public double getPrice()
    {
        return discountPrice*inCartCount;
    }

    public double getOriginalPrice()
    {
        return super.getPrice();
    }

    public int getInCartCount()
    {
        return this.inCartCount;
    }

    public String getDiscountString()
    {
        if(!isDiscounted())
        {
            return "";
        }
        return String.valueOf(inCartCount) + "x Discount: " + (this.getDiscountPrice()-this.getOriginalPrice());
    }

    @Override
    public String toString()
    {
        String ret = String.valueOf(inCartCount) + "x " + this.getName() + " (" + this.getQuantity() + " " + getUnit().toString() + ") - " + this.getOriginalPrice();
        return ret;
    }
}
