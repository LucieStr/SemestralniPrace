package app;

import java.io.BufferedReader;
import utils.Income;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.Library;

/**
 *
 * @author lucka
 */
public class Money implements Library {

    private List<Product> tab;
    private int income;
    private int price;
    private static app.Customer c;

    /**
     * constructor
     */
    public Money() {
        this.income = income;
        this.price = price;
        this.tab = new ArrayList<>();
    }

    /**
     * constructor
     *
     * @param income
     * @param price
     */
    public Money(int income, int price) {
        this.income = income;
        this.price = price;
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
     * set income
     *
     * @param income
     */
    public void setIncome(int income) {
        this.income = income;
    }

    public void saveTab(List<Product> t){
        this.tab = t;
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
     *
     * @return string
     */
    @Override
    public String toString() {
        return String.format("%10d %10d", this.price, this.income);
    }

    public int price() {
        this.price = 0;
        for (Product product : tab) {
            price = price + (product.getPriceOneProduct() * product.getAmount());
        }
        return price;
    }



//    
//            /**
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
//    private void parselCus(String[][] tabb) {
//        List<Product> tab = new ArrayList<>();
//        String parts;
//        String[] split;
//        Product r;
//        for (int i = 0; i < tabb.length; i++) {
//            parts = tabb[i][0];
//            split = parts.split("[ ]+");
//            r = new Product(Integer.parseInt(split[0]), split[1], split[2], Integer.parseInt(split[3]));
//            tab.add(r);
//        }
//        saveCustomertab(tab);
//    }
//        public void saveCustomertab(List<Product> tab) {
//        this.tab = tab;
//    }
    public static void main(String[] args) throws IOException {
        Money m = new Money();
        Owner r = new Owner();
        Customer c = new Customer();
        //m.parselCus(c.load(new File("zkouska.txt")));
        //c.loadCustomer(new File("zkouska.txt"));
        //r.loadOwner(new File("zkouska2.txt"));
        //int price = 100;
        int income = 500;
        //m.moneyFromCustomer(income);
        m.price();
        //m.setPrice(price);
        //System.out.println(m);
        m.saveFile(new File("zkouska3.dat"));
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
