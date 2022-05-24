package app;

import utils.Income;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author lucka
 */
public class Money {

    private int income;
    private int price = 2677;
//    private static Customer customer;
//    private static Owner owner;
//    private static Product product;
//    private static TabOwner tabOwner;

    public Money() {
        this.income = income;
        this.price = price;
    }

    public Money(int income, int price) {
        this.income = income;
        this.price = price;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setPrice(int price) {
        this.price = 2677;
        //this.price = price+(priceOneProduct * amount);
    }

    public int getPrice() {
        return price;
    }

    /**
     * upgrade income with money from customer
     *
     * @param money
     */
    public void moneyFromCustomer(int money) { //podminka kdyz money se nerovna int
        this.income = income + money;
        setIncome(this.income);

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
     * sum price from amount and price from one product
     *
     * @return
     * @throws java.io.IOException
     */
    public int price() throws IOException { //nevim 
        //owner = new Owner();
        //customer = new Customer();
//        int amount, pOP;
//        int [] am = uiSemestralniPrace.ConsolaUi.parseAmount();
//        int [] p = uiSemestralniPrace.ConsolaUi.parsePOP();
////        List<Product> cu = customer.getTabCustomer();
////        List<TabOwner> ow = owner.getTabOwner();
////        String[][] cuArray = cu.toArray(new String[cu.size()][3]);
////        String[][] owArray = ow.toArray(new String[cu.size()][2]);
//        for (int i = 0; i < am.length; i++) {
//            amount = am[i];
//            pOP = p[i];
//            this.price = this.price + (amount * pOP);
        this.price = 2677;
//        }
        return this.price;
    }

    /**
     * safe binary file
     *
     * @param money
     * @throws IOException
     */
    public void safeBinaryFile(File money) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(money))) {
            out.writeInt(getPrice());
            out.writeInt(getIncome());
        }
    }

    /**
     * read binary file
     *
     * @param money
     * @return price and income
     * @throws IOException
     */
    public String readBinaryFile(File money) throws IOException {
        StringBuilder sb = new StringBuilder();
        int nprice, nincome;
        try (DataInputStream in = new DataInputStream(new FileInputStream(money))) {
            boolean end = false;
            while (!end) {
                try {
                    nprice = in.readInt();
                    nincome = in.readInt();
                    sb.append(String.format("%n Vse stalo %1dKc a vydelek je %1dKc%n", nprice, nincome));
                    income = nincome;
                    price = nprice;
                } catch (EOFException e) {
                    end = true;
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("%10d %10d", this.price, this.income);
    }

    public static void main(String[] args) throws IOException {
        Money m = new Money();
        Owner r = new Owner();
        Customer c = new Customer();
        c.loadCustomer(new File("zkouska.txt"));
        r.loadOwner(new File("zkouska2.txt"));
        //int price = 100;
        int income = 500;
        m.moneyFromCustomer(income);
        m.price();
        //m.setPrice(price);
        //System.out.println(m);
        m.safeBinaryFile(new File("zkouska3.dat"));
        System.out.println(m.readBinaryFile(new File("zkouska3.dat")));
        System.out.println(m.income());
        System.out.println(m.getPrice());
        //System.out.println(m);

    }

}
