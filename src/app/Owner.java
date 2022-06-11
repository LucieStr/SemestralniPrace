package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import utils.Library;

/**
 *
 * @author lucka
 */
public class Owner implements Library {

    private String name;
    private List<Product> tab;
    private String[][] ow;

    /**
     * constructor
     */
    public Owner() {
        this.name = name;
        this.tab = new ArrayList<>();
        this.ow = new String[tab.size()][3];
    }

    /**
     * deep copy
     *
     * @return ArrayList copy
     */
    public List<Product> getTabOwner() {
        ArrayList<Product> copy = new ArrayList<>();
        for (Product tabb : tab) {
            copy.add(new Product(tabb));
        }
        return copy;
    }



    /**
     * save file for owner
     *
     * @param result
     * @throws IOException
     */
    @Override
    public void saveFile(File result) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result)))) {
            pw.println(String.format("%10s %5s", "Nazev", "Cena"));
            for (Product tabb : tab) {
                pw.println(tabb);

            }
        }
    }

    /**
     * save List
     *
     * @param tab
     */
    public void saveTab(List<Product> tab) {
        this.tab = tab;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (Product customer : tab) {
//            sb.append(customer).append("\n");
//        }
//        return sb.toString();
//
//    }
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
//
//    public void saveCustomertab(List<Product> t) {
//        this.tab = t;
//    }
    public static void main(String[] args) throws IOException {
        Owner o = new Owner();

        //o.parselCus(o.load(new File("zkouska.txt")));
        //System.out.println(o);
        //o.setPriceOneProduct(15, "Mila");
        // o.saveOwner(new File("zkouska4.txt"));
        //String[][] t = o.getOw();
        //for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < 3; j++) {
                //System.out.format(t[i][j] + " ");

            }
            System.out.println("");
        }

    }


