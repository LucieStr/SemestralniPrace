package app;

/**
 *
 * @author lucka
 */
public class Product implements Comparable<Product> {

    private String name;
    private int amount;
    private String what;

    public Product(int amount, String name, String what) {
        this.name = name;
        this.amount = amount;
        this.what = what;
    }

    public Product() {
        this.amount = amount;
        this.name = name;
        this.what = what;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getWhat() {
        return what;
    }

    /**
     * get lower amount of product
     * @param amount
     * @param name 
     */
    public void getProduct(int amount, String name) {
        if (amount > this.amount) {
            throw new utils.MyException("Zadali jste vetsi mnozstvi nez toho mame.");
        } else {
            this.amount = this.amount - amount;
        }
        this.name = name;
    }

    /**
     * get new amount of product
     * @param amount
     * @param name 
     */
    public void addProduct(int amount, String name) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%3d kusu %10s", this.amount, this.name);
    }

    @Override
    public int compareTo(Product o) {
        return this.getWhat().compareTo(o.getWhat());
    }
}
