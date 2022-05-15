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
import java.util.List;

/**
 *
 * @author lucka
 */
public class Money {

    private int income;
    private int price;
    private static Customer customer;
    private static Owner owner;

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
        this.price = price;
    }

    /**
     * upgrade income with money from customer
     * @param money 
     */
    public void moneyFromCustomer(int money) {
        this.income = income + money;
        setIncome(this.income);
    }

    /**
     * find income
     * @return enum
     */
    public Income income() {
        if (income > price) {
            return Income.VYDELEK;
        } else if (income < price) {
            return Income.PRODELEK;
        } else {
            return Income.NEPRODELEK;
        }
    }

    /**
     * sum price from amount and price from one product
     * @return 
     */
    public int price() { //nevim 

        return this.price;
    }

    /**
     * safe binary file
     * @param money
     * @throws IOException 
     */
    public void safeBinaryFile(File money) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(money))) {
            out.writeInt(price());
            out.writeInt(getIncome());
        }
    }

    /**
     * read binary file
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
                    sb.append(String.format(" Vse stalo %1dKc a vydelek je %1dKc%n", nprice, nincome));

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
        //c.loadCustomer(new File("zkouska.txt"));
       // r.loadOwner(new File("zkouska2.txt"));
        m.price();
        int price = 100;
        int income = 500;
        m.moneyFromCustomer(income);
        m.setPrice(price);
        //System.out.println(m);
        m.safeBinaryFile(new File("zkouska3.dat"));
        System.out.println(m.readBinaryFile(new File("zkouska3.dat")));
        System.out.println(m.income());
        //System.out.println(m);
    }

}
