package app;

/**
 *
 * @author lucka
 */
public class TabOwner {

    private String name;
    private int priceOneProduct;

    public TabOwner(String name, int price) {
        this.name = name;
        this.priceOneProduct = price;
    }

    @Override
    public String toString() {
        return String.format("%10s %3dKc", this.name, this.priceOneProduct);
    }

}
