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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import utils.Income;
import utils.Library;

/**
 *
 * @author lucka
 */
public class Customer implements Library {

    private int price;
    private List<Product> tab;
    private String[][] newTab;
    private int income;

    /**
     * save file for customer
     *
     * @param result
     * @throws IOException
     */
    @Override
    public void saveFile(File result) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result)))) {
            pw.println(String.format("%8s %10s", "Pocet", "Nazev"));
            for (Product p : tab) {
                pw.println(p);

            }
        }
    }

    /**
     * save List
     *
     * @param t
     */
    public void saveTab(List<Product> t) {
        this.tab = t;
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

    /**
     * deep copy
     *
     * @return
     */
    public List<Product> getTabCustomer() {
        ArrayList<Product> copy = new ArrayList<>();
        for (Product p : tab) {
            copy.add(new Product(p));
        }
        return copy;
    }

    /**
     * lower amount of product
     *
     * @param name
     * @param amount
     * @return
     */
    public String[][] pickProduct(String name, int amount) {
        Product p = findByName(name);
        p.getProduct(amount, name);
        newTab = new String[1][2];
        for (int j = 0; j < newTab.length; j++) {
            newTab[j][0] = String.valueOf(amount);
            newTab[j][1] = name;
        }
        return newTab;
    }

    public String[][] getNewTab() {
        return newTab;
    }

    public int moneyOwner() {
        String[][] t = getNewTab();
        int amountO = 0;
        String nameO = " ";
        for (int i = 0; i < t.length; i++) {
            amountO = Integer.parseInt(t[i][0]);
            nameO = t[i][1];
        }
        for (Product p : tab) {
            if (p.getName() == null ? nameO == null : p.getName().equals(nameO)) {
                price = price + (amountO * p.getPriceOneProduct());
            }
        }
        return price;
    }

    public String readFile(File result) throws FileNotFoundException, IOException {
        String everything;
        try (BufferedReader br = new BufferedReader(new FileReader(result))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        }
        return everything;
    }

    public void saveNewFile(File result) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result, true)))) {
            for (int j = 0; j < newTab.length; j++) {
                String amount = newTab[j][0];
                String name = newTab[j][1];
                pw.print(amount);
                pw.print(" ");
                pw.print(name);
                pw.print("\n");
            }

        }
    }

    /**
     * upgrade income with money from customer
     *
     * @param money
     */
    public void moneyFromCustomer(int money) {
        this.income = income + money;
        setIncome(this.income);

    }

    /**
     * set income
     *
     * @param income
     */
    public void setIncome(int income) {
        this.income = income;
    }

    /**
     * get income
     *
     * @return income
     */
    public int getIncome() {
        return income;
    }

    /**
     *
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = moneyOwner();
    }

    /**
     * find income
     *
     * @return enum
     */
    public Income income() {
        if (income > price) {
            return Income.VYDELEK;
        } else if (income == price) {
            return Income.NEPRODELEK;
        } else {
            return Income.PRODELEK;
        }
    }

    /**
     * find product by name
     *
     * @param name
     * @return ArrayList
     */
    private Product findByName(String name) {
        checkName(name);
        for (Product tab : tab) {
            if (tab.getName() == null ? name == null : tab.getName().equals(name)) {
                return tab;
            }
        }
        throw new NoSuchElementException("produkt s nazvem " + name + " neexistuje.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product customer : tab) {
            sb.append(customer).append("\n");
        }
        return sb.toString();
    }

    /**
     * sort by amount
     */
    public void sortByAmount() {
        Comparator cbp = new utils.ComparatorProductByAmount();
        Collections.sort(tab, cbp);
    }

    /**
     * sort tabCustomer by what it is (food or drink)
     */
    public void sortByWhat() {
        Collections.sort(tab);
    }

//    /**
//     * load file
//     *
//     * @param nameFile
//     * @return
//     * @throws IOException
//     */
//    public String[][] load(File nameFile) throws IOException {
//        int i = 0;
//        String line;
//        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
//            br.readLine();
//            while ((line = br.readLine()) != null) {
//                i++;
//            }
//        }
//        String[][] tab = new String[i][1];
//        i = 0;
//        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
//            br.readLine();
//            while ((line = br.readLine()) != null) {
//                tab[i][0] = line;
//                i++;
//            }
//            return tab;
//        }
//    }
//
//    /**
//     * parsel to get ArrayList with amount, name and what
//     *
//     * @param tab
//     */
//    private void parselCus(String[][] tab) {
//        List<Product> tabCustomer = new ArrayList<>();
//        String parts;
//        String[] split;
//        Product r;
//        for (int i = 0; i < tab.length; i++) {
//            parts = tab[i][0];
//            split = parts.split("[ ]+");
//            r = new Product(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), split[3]);
//            tabCustomer.add(r);
//        }
//        saveCustomertab(tabCustomer);
//    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer c = new Customer();
       // c.parselCus(c.load(new File("zkouska.txt")));
        System.out.println(c);
        c.getTabCustomer();
        //System.out.println(c);
        //c.findByName("Miudh");
        //c.addproduct("Mila", 20);
        System.out.println(c);
        String[][] tab = c.pickProduct("Mila", 5);
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                System.out.format(tab[i][j] + " ");

            }
            System.out.println("");
        }
        c.saveNewFile(new File("alkdj"));
//        System.out.println(c);
//        c.sortByWhat();
//        System.out.println(c);
        System.out.println(c.moneyOwner());
    }

}
