package app;

/**
 *
 * @author lucka
 */
public class Product implements Comparable<Product> {

    private String name;
    private int amount;
    private final String what;

    /**
     * constructor
     * @param amount
     * @param name
     * @param what 
     */
    public Product(int amount, String name, String what) {
        this.name = name;
        this.amount = amount;
        this.what = what;
    }

    /**
     * constructor for deep copy
     * @param p 
     */
    public Product(Product p) {
        this.name = p.name;
        this.amount = p.amount;
        this.what = p.what;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get amount
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * get what (food or drink)
     * @return what
     */
    public String getWhat() {
        return what;
    }

    /**
     * get lower amount of product
     *
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
     * string one line
     * @return string
     */
    @Override
    public String toString() {
        return String.format("%3d kusu %10s", this.amount, this.name);
    }

    /**
     * compare food/drink
     * @param o
     * @return  food or drink
     */
    @Override
    public int compareTo(Product o) {
        return this.getWhat().compareTo(o.getWhat());
    }
}
