package app;

/**
 *
 * @author lucka
 */
public class TabOwner {

    private String name;
    private int priceOneProduct;


    /**
     * constructor for copy
     * @param t 
     */
    public TabOwner(TabOwner t) {
        this.name = t.name;
        this.priceOneProduct = t.priceOneProduct;
    }

    /**
     * constructor
     * @param name
     * @param price 
     */
    public TabOwner(String name, int price) {
        this.name = name;
        this.priceOneProduct = price;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get price of one produc
     * @return price of one product
     */
    public int getPriceOneProduct() {
        return priceOneProduct;
    }

    @Override
    public String toString() {
        return String.format("%10s %3dKc", this.name, this.priceOneProduct);
    }

}
