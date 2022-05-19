/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

/**
 *
 * @author lucka
 */
public class Customer {

    private String product;
    private List<Product> tabCustomer;

    public Customer() {
        this.product = product;
        this.tabCustomer = new ArrayList<>();
    }

    /**
     * load file for customer
     *
     * @param customerFile
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadCustomer(File customerFile) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(customerFile))) {
            String[] parts;
            String line;
            Product r;
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                r = new Product(Integer.parseInt(parts[0]), parts[1], parts[2]);
                tabCustomer.add(r);
            }
        }
    }

    /**
     * save file for customer
     *
     * @param result
     * @throws IOException
     */
    public void saveCustomer(File result) throws IOException { //ulozit bez uprav
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result)))) {
            pw.println(String.format("%8s %10s", "Pocet", "Nazev"));
            for (Product p : tabCustomer) {
                pw.println(p);

            }
        }
    }

    public void saveCustomertab(List<Product> tab) {
        this.tabCustomer = tab;
    }

    /**
     * check if name of product is correct
     *
     * @param name
     * @return
     */
    private String checkName(String name) {
        if (!name.matches("[A-Z][a-z]+")) {
            throw new NoSuchElementException("Spatne zadany nazev");
        }
        return name;
    }

    /**
     * find year, month and day
     *
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

    public List<Product> getTabCustomer() {
        ArrayList<Product> copy = new ArrayList<>();
        for (Product p : tabCustomer) {
            copy.add(new Product(p));
        }
        return copy;
    }

    /**
     * lower amount of product
     *
     * @param name
     * @param amount
     */
    public void pickProduct(String name, int amount) {
        Product p = findByName(name);
        p.getProduct(amount, name);

    }

    /**
     * upgrade amount of product
     *
     * @param name
     * @param amount
     */
    public void addproduct(String name, int amount) {
        Product p = findByName(name);
        p.addProduct(amount, name);
    }

    /**
     * find product by name
     *
     * @param name
     * @return 
     */
    private Product findByName(String name) { //upravit javaDoc
        checkName(name);
        for (Product tab : tabCustomer) {
            if (tab.getName() == null ? name == null : tab.getName().equals(name)) {
                return tab;
            }
        }
        throw new NoSuchElementException("produkt s nazvem " + name + " neexistuje.");
    }

    
    public int[][] getArrayAmount() { //?
        int[][] amount = new int[tabCustomer.size()][1];
              String[][] cuArray = tabCustomer.toArray(new String[tabCustomer.size()][3]);
            for (int i = 0; i < amount.length; i++) {
                amount[i][1] = Integer.parseInt(cuArray[i][0]);

            }
        return amount;
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
    public void sortByAmount() { //zmenit na jidlo a piti
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
        c.loadCustomer(new File("zkouska.txt"));
        //System.out.println(c);
        c.getTabCustomer();
        //System.out.println(c);
        //c.findByName("Miudh");
        c.addproduct("Mila", 20);
        System.out.println(c);
        c.pickProduct("Mila", 5);
        System.out.println(c);
        c.sortByWhat();
        System.out.println(c);

    }

}
