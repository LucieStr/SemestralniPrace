package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import utils.Library;

/**
 *
 * @author lucka
 */
public class Customer implements Library {

    private String product;
    private List<Product> tabCustomer;


    /**
     * save file for customer
     *
     * @param result
     * @throws IOException
     */
    @Override
    public void saveFile(File result) throws IOException { //ulozit bez uprav
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result)))) {
            pw.println(String.format("%8s %10s", "Pocet", "Nazev"));
            for (Product p : tabCustomer) {
                pw.println(p);

            }
        }
    }

    /**
     * save List
     * @param tab 
     */
    public void saveCustomertab(List<Product> tab) {
        this.tabCustomer = tab;
    }

    /**
     * check if name of product is correct
     *
     * @param name
     * @return name
     */
    private String checkName(String name) {
        if (!name.matches("[A-Z][a-z]+")) {
            throw new NoSuchElementException("Spatne zadany nazev");
        }
        return name;
    }

    /**
     * find year, month and day
     * @return date
     */
    public String date() {
        LocalDate current_date = LocalDate.now();
        int day = current_date.getDayOfMonth();
        int month = current_date.getMonthValue();
        int year = current_date.getYear();
        String answ = String.format("%2d.%2d.%4d", day, month, year);
        return answ;
    }

    /**
     * deep copy 
     * @return
     */
    public List<Product> getTabCustomer() {
        ArrayList<Product> copy = new ArrayList<>();
        for (Product p : tabCustomer) {
            copy.add(new Product(p));
        }
        return copy;
    }

    /**
     * lower amount of product
     * @param name
     * @param amount
     */
    public void pickProduct(String name, int amount) {
        Product p = findByName(name);
        p.getProduct(amount, name);

    }

    /**
     * find product by name
     * @param name
     * @return ArrayList
     */
    private Product findByName(String name) {
        checkName(name);
        for (Product tab : tabCustomer) {
            if (tab.getName() == null ? name == null : tab.getName().equals(name)) {
                return tab;
            }
        }
        throw new NoSuchElementException("produkt s nazvem " + name + " neexistuje.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product customer : tabCustomer) {
            sb.append(customer).append("\n");
        }
        return sb.toString();
    }

    /**
     * sort by amount
     */
    public void sortByAmount() {
        Comparator cbp = new utils.ComparatorProductByAmount();
        Collections.sort(tabCustomer, cbp);
    }

    /**
     * sort tabCustomer by what it is (food or drink)
     */
    public void sortByWhat() {
        Collections.sort(tabCustomer);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer c = new Customer();
        //c.loadCustomer(new File("zkouska.txt"));
        //System.out.println(c);
        c.getTabCustomer();
        //System.out.println(c);
        //c.findByName("Miudh");
        //c.addproduct("Mila", 20);
        System.out.println(c);
        c.pickProduct("Mila", 5);
        System.out.println(c);
        c.sortByWhat();
        System.out.println(c);

    }

}
