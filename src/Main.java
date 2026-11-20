import Store.Store;
import Store.*;

import java.math.BigInteger;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        String waresCsvPath = "data/varer.csv";
        String waresSalesCsv = "data/tilbud.csv";
        Store store = new Store(waresCsvPath,waresSalesCsv);
        ArrayList<Ware> cart = new ArrayList<>();
        cart.add(store.getWareById("8000500279823"));
        cart.add(store.getWareById("8000500279823"));
        cart.add(store.getWareById("7314888018495"));
        cart.add(store.getWareById("7314888018495"));
        cart.add(store.getWareById("5714446001730"));
        cart.add(store.getWareById("5705010082392"));
        cart.add(store.getWareById("5703161006601"));
        cart.add(store.getWareById("5740001203769"));
        store.purchase(cart);
    }
}