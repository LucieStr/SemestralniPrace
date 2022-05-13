package app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

    public int moneyFromCustomer(int money) {
        return this.income = income + money;
    }

    public Income income() {
        if (income > price) {
            return Income.VYDELEK;
        } else if (income < price) {
            return Income.PRODELEK;
        } else {
            return Income.NEPRODELEK;
        }
    }

    public int price() { //nevim 
        Owner owner = new Owner();
        Customer customer = new Customer();
        for (int i = 0; i < (owner.getTabOwner().size()); i++) {
            for (int j = 0; j < (customer.getTabCustomer().size()); j++) {
                this.price = price + (owner.getPriceOneProduct() * customer.getAmount());
            }
        }
        return this.price;
    }

    public void safeBinaryFile(File money) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(money))) {
            out.writeInt(price());
            out.writeInt(getIncome());
        }
    }

    public String readBinaryFile(File money) throws IOException {
        StringBuilder sb = new StringBuilder();
        int nprice, nincome;
        try (DataInputStream in = new DataInputStream(new FileInputStream(money))) {
            nprice = in.readInt();
            nincome = in.readInt();
            sb.append(String.format("Vse stalo %10d Kc Vydelek je %10d", nprice, nincome));
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
        m.price();
        int price = 100;
        int income = 500;
        System.out.println(m);
        m.safeBinaryFile(new File("zkouska3.dat"));

    }

}
