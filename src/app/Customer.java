/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private int amount;
    private List<Product> tabCustomer;

    public Customer() {
        this.amount = amount;
        this.product = product;
        this.tabCustomer = new ArrayList<>();
    }

    public void loadCustomer(File customerFile) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(customerFile))) {
            String[] parts;
            String line;
            Product r;
            br.readLine(); // preskoceni hlavicky
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                r = new Product(Integer.parseInt(parts[0]),parts[1]);
                this.amount = Integer.parseInt(parts[0]);
                tabCustomer.add(r);
            }
        }
    }
//prpisovani souboru zakaznik

    public int getAmount() {
        return this.amount;
    }
    

    public List<Product> getTabCustomer() {
        ArrayList<Product> copy = new ArrayList<>();
        for (Product product1 : tabCustomer) {
            copy.add(new Product());
        }
        return copy;
    }


    public void pickProduct(String name, int amount){
        Product p = findByName(name);
        p.getProduct(amount, name);

    }
    
    public void addproduct(String name, int amount){
        Product p = findByName(name);
        p.addProduct(amount, name);
    }
    private Product findByName(String name){
              for (Product tab : tabCustomer) {
            if (tab.getName() == name) {
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

    public void sortByName(){ //zmenit na jidlo a piti
        Comparator cbp = new utils.ComparatorProductByName();
        Collections.sort(tabCustomer,cbp);
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer c = new Customer();
        c.loadCustomer(new File("zkouska.txt"));
        System.out.println(c);
        c.getTabCustomer();
        System.out.println(c);
        

    }



}
