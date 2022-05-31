package app;

import utils.Income;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import utils.Library;

/**
 *
 * @author lucka
 */
public class Money implements Library{

    private int income;
    private int price;

    /**
     * constructor
     */
    public Money() {
        this.income = income;
        this.price = price;
    }
    
    /**
     * constructor
     * @param income
     * @param price 
     */
    public Money(int income, int price) {
        this.income = income;
        this.price = price;
    }

    /**
     * get income
     * @return income
     */
    public int getIncome() {
        return income;
    }

    /**
     * set income
     * @param income 
     */
    public void setIncome(int income) {
        this.income = income;
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
     * @return price
     * @throws java.io.IOException
     */
    public int price() throws IOException {
        this.price = 0;
        String[][] cus = uiSemestralniPrace.ConsolaUi.parseAmount();      
        String[][] own = uiSemestralniPrace.ConsolaUi.parsePOP();       
        for (int i = 0; i < cus.length; i++) {
            for (int j = 0; j < own.length; j++) {
                if (cus[i][1] == null ? own[j][0] == null : cus[i][1].equals(own[j][0])) {
                    price = price+ (Integer.parseInt(cus[i][0]) * Integer.parseInt(own[j][1]));
                }
            }
        }
        return price;
    }

    /**
     * safe binary file
     *
     * @param money
     * @throws IOException
     */
    @Override
    public void saveFile(File money) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(money))) {
            int pri = price();
            int income = getIncome();
            out.writeInt(pri);
            out.writeInt(income);
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

    /**
     * string for file money
     * @return string
     */
    @Override
    public String toString() {
        return String.format("%10d %10d", this.price, this.income);
    }
    

    public static void main(String[] args) throws IOException {
        Money m = new Money();
        Owner r = new Owner();
        Customer c = new Customer();
        //c.loadCustomer(new File("zkouska.txt"));
        //r.loadOwner(new File("zkouska2.txt"));
        //int price = 100;
        int income = 500;
        m.moneyFromCustomer(income);
        m.price();
        //m.setPrice(price);
        //System.out.println(m);
        //m.saveBinaryFile(new File("zkouska3.dat"));
        System.out.println(m.readBinaryFile(new File("zkouska3.dat")));
//        System.out.println(m.income());
//        System.out.println(m.getPrice());
//        String[][] am = parseAmount();
//        for (int i = 0; i < am.length; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.println(am[i][0] + am[i][1]);
//
//            }
//
//        }
        //System.out.println(m);

    }

}
