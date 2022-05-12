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
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lucka
 */
public class Customer {
    private String product;
    public static int amount;
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
    public List<Product> getTabCustomer() {
        return tabCustomer;
    }


    public void updatetabCustomer(){
        //predelat amount zakaznik
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product customer : tabCustomer) {
            sb.append(customer).append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer c = new Customer();
        c.loadCustomer(new File("zakaznik.txt"));
        System.out.println(c);
        

    }

}
