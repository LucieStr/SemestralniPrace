package uiSemestralniPrace;

import app.Product;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utils.MyException;

/**
 *
 * @author Lucie Strnadkova
 */
public class ConsolaUi {

    private static Scanner sc = new Scanner(System.in);
    private static app.Customer customer;
    private static app.Owner owner;
    private static app.Money money;
    private static String parent;
    private static File dataDirectory;
    private static app.Product product;

    public static void main(String[] args) throws IOException {
        try {                
            String[][] tabCus;
            boolean konec = false;
            int choice;
            parent = System.getProperty("user.dir") + File.separator + "data";
            dataDirectory = new File(parent);
            owner = new app.Owner();
            money = new app.Money();
            customer = new app.Customer();
//            tabOw = load(new File(dataDirectory, "majitel1.txt"));
//            parselOw(tabOw);
            tabCus = load(new File(dataDirectory, "zakaznik.txt"));
            parselCus(tabCus);
            String input = "";
            do {
                System.out.println("Kdo jsi?");
                System.out.println(" ");
                displayMenu();
                try {
                    input = sc.next();
                    choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            owner();
                            break;
                        case 2:
                            customer();
                            break;
                        case 0:
                            System.out.println("konec");
                            konec = true;
                            break;
                        default:
                            System.out.println("neplatna volba ");
                            System.out.println(" ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(input + " neni cislo");
                    System.out.println(" ");
                }
            } while (!konec);
        } catch (IOException e) {
            System.out.println("Neco se pokazilo");
            System.out.println(" ");
        }
    }

    /**
     * display menu if owner or customer
     */
    private static void displayMenu() {
        System.out.println("1 = majitel");
        System.out.println("2 = zakaznik");
        System.out.println("0 = konec");
    }

    /**
     * menu for owner
     *
     * @throws IOException
     */
    private static void owner() throws IOException {
        try {
            boolean end = false;
            int choice;
            String input = "";
            do {
                System.out.println(" ");
                displayMenuOwner();
                try {
                    input = sc.next();
                    choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            displayProductsByAmount();
                            break;
                        case 2:
                            displayMoney();
                            break;
                        case 3:
                            displayPrice();
                            break;
                        case 4:
                            price();
                            break;
                        case 0:
                            end = true;
                            break;
                        default:
                            System.out.println("neplatna volba");
                            System.out.println(" ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(input + " neni cislo");
                    System.out.println("");
                }
            } while (!end);
        } catch (IOException e) {
            System.out.println("Neco se pokazilo");
            System.out.println(" ");
        }
    }

    /**
     * display menu for owner
     */
    private static void displayMenuOwner() {
        System.out.println("1 = zobrazit produkty");
        System.out.println("2 = informace o vydelku");
        System.out.println("3 = zobrazit ceny jednotlivych produktu");
        System.out.println("4 = zobrazit kolik produkty staly celkem");
        System.out.println("0 = zpet");
    }

    /**
     * menu for customer
     *
     * @throws IOException
     */
    private static void customer() throws IOException {
        try {
            System.out.println(" ");
            System.out.println("Vitam te v Horskem stanku.");
            System.out.println("Kde zaplatis kolik budes chtit.");
            String date = customer.date();
            System.out.println("Dnes je " + date);
            boolean end = false;
            String input = "";
            int choice;
            do {
                System.out.println(" ");
                displayMenuCustomer();
                try {
                    input = sc.next();
                    choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            displayProducts();
                            break;
                        case 2:
                            pickProduct();
                            break;
                        case 3:
                            pay();
                            break;
                        case 4:
                            showMap();
                            break;
                        case 0:
                            end = true;
                            break;
                        default:
                            System.out.println("neplatna volba");
                            System.out.println(" ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(input + " neni cislo");
                    System.out.println(" ");
                }
            } while (!end);
        } catch (IOException e) {
            System.out.println("Neco se pokazilo");
            System.out.println(" ");
        }
    }

    /**
     * display menu for customer
     */
    private static void displayMenuCustomer() {
        System.out.println("1 = zobrazit menu");
        System.out.println("2 = vybrat produkty");
        System.out.println("3 = zaplatit");
        System.out.println("4 = ukazat mapu");
        System.out.println("0 = zpet");
    }

    /**
     * display products sort by amount
     *
     * @throws IOException
     */
    private static void displayProductsByAmount() {
        System.out.println(" ");
        customer.getTabCustomer();
        customer.sortByAmount();
        System.out.println(customer);
    }

    /**
     * display products and price of one product
     *
     * @throws IOException
     */
    private static void displayPrice() {
        System.out.println(" ");
        List<Product> tab = owner.getTabOwner();
        String[][] ow = new String[tab.size()][3];
        int help;
        for (Product p : tab) {
            for (int j = 0; j < tab.size(); j++) {
                for (int i = 0; i < tab.size(); i++) {
                    ow[i][0] = tab.get(i).getName();
                    help = tab.get(i).getPriceOneProduct();
                    ow[i][1] = String.valueOf(help);
                    ow[i][2] = "Kc";
                }
            }
        }
        for (int i = 0; i < ow.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.format(ow[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * get money from customer
     *
     * @throws IOException
     */
    private static void pay() throws IOException {
        try {
            System.out.println(" ");
            System.out.println("Zadejte castku");
            String input = "";
            try {
                input = sc.next();
                int number = Math.abs(Integer.parseInt(input));
                if (number == 0) {
                    System.out.println("Nic nebylo vlozeno");
                } else {
                    customer.moneyFromCustomer(number);
                    System.out.println("Dekuju za zaplaceni");
                }
            } catch (NumberFormatException e) {
                System.out.println(input + " neni cislo");
                System.out.println(" ");
            }
            System.out.println(" ");
            money.saveFile(new File(dataDirectory, "price.dat"));
        } catch (IOException e) {
            System.out.println("Chyba pri praci se souborem");
            System.out.println(" ");
        }
    }

    /**
     * load what product customer pick
     *
     * @throws IOException
     */
    private static void pickProduct() throws IOException {
        try {
            String input = "";
            System.out.println(" ");
            System.out.println("Napiste nazev produktu a pocet");
            try {
                try {
                    String name = sc.next();
                    input = sc.next();
                    int amount = Math.abs(Integer.parseInt(input));
                    if (amount == 0) {
                        System.out.println("Nic jste nevybrali");
                    } else {
                        customer.pickProduct(name, amount);
                        System.out.println(" ");
                        customer.saveFile(new File(dataDirectory, "zakaznikUlozeni.txt"));
                        customer.saveNewFile(new File(dataDirectory, "nakup.txt"));
                        System.out.format("Mate vybrano (ulozen soubor s novym poctem produktu)");
                        System.out.println(" ");
                    }
                } catch (MyException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println(input + " neni cislo");
                System.out.println(" ");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            System.out.println("Zkuste to znovu");
            System.out.println(" ");
        }
    }

    /**
     * display products sort by what it is (food or drink)
     *
     * @throws IOException
     */
    private static void displayProducts() {
        System.out.println(" ");
        customer.getTabCustomer();
        customer.sortByWhat();
        System.out.println(customer);
    }

    /**
     * show map
     */
    private static void showMap() {
        try {
            BufferedImage img = ImageIO.read(new File(dataDirectory, "map.jpg"));
            JLabel pick = new JLabel(new ImageIcon(img));
            JPanel jPanel = new JPanel();
            jPanel.add(pick);
            JFrame f = new JFrame();
            f.setSize(new Dimension(img.getWidth(), img.getHeight()));
            f.add(jPanel);
            f.setVisible(true);
        } catch (IOException e) {
            System.out.println("Chyba pri praci se souborem");
        }
    }

    /**
     * display file with price and income
     *
     * @throws IOException
     */
    private static void displayMoney() throws IOException {
        try {
            System.out.println(" ");
            String[][] tab = customer.getNewTab();
            if (tab == null) {
                System.out.println("Zakaznik si nic nekoupil");
            } else {
                System.out.println("Zakaznik si koupil");
                System.out.println(customer.readFile(new File(dataDirectory,"nakup.txt")));
                System.out.println("a zaplatil za to "+customer.getIncome()+" Kc");
                System.out.println("");
                System.out.println("Majitel za stejne mnoztvi produktu zaplatil "+ customer.moneyOwner()+" Kc");
            }
            //money.saveFile(new File(dataDirectory, "price.dat"));
        } catch (IOException e) {
            System.out.println("Chyba pri praci se souborem");
        }
        //System.out.println(money.readBinaryFile(new File(dataDirectory, "price.dat")));
        System.out.println(customer.income());

    }

    /**
     * load file
     *
     * @param nameFile
     * @return
     * @throws IOException
     */
    public static String[][] load(File nameFile) throws IOException {
        int i = 0;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                i++;
            }
        }
        String[][] tab = new String[i][1];
        i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                tab[i][0] = line;
                i++;
            }
            return tab;
        }
    }

//    /**
//     * paarsel file to get ArrayList with prices and names
//     *
//     * @param tab
//     */
//    private static void parselOw(String[][] tab) {
//        List<TabOwner> tabOwner = new ArrayList<>();
//        String parts;
//        String[] split;
//        TabOwner r;
//        for (int i = 0; i < tab.length; i++) {
//            parts = tab[i][0];
//            split = parts.split("[ ]+");
//            r = new TabOwner(split[0], Integer.parseInt(split[1]));
//            tabOwner.add(r);
//
//        }
//        owner.saveOwnertab(tabOwner);
//    }
    /**
     * parsel to get ArrayList with amount, name and what
     *
     * @param tab
     */
    private static void parselCus(String[][] t) {
        List<Product> tab = new ArrayList<>();
        String parts;
        String[] split;
        Product r;
        for (int i = 0; i < t.length; i++) {
            parts = t[i][0];
            split = parts.split("[ ]+");
            r = new Product(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), split[3]);
            tab.add(r);
        }
        customer.saveTab(tab);
        owner.saveTab(tab);
        money.saveTab(tab);
    }

//    /**
//     * parsel file zakaznik
//     *
//     * @return array with amounts
//     * @throws IOException
//     */
//    public static String[][] parseAmount() throws IOException {
//        String[][] tab = load(new File(dataDirectory, "zakaznik.txt"));
//        String[][] amount = new String[tab.length][2];
//        String parts;
//        String[] split;
//        for (int i = 0; i < amount.length; i++) {
//            parts = tab[i][0];
//            split = parts.split("[ ]+");
//            amount[i][0] = split[0];
//            amount[i][1] = split[1];
//        }
//        return amount;
//    }
//
//    /**
//     * parsel file majitel1
//     *
//     * @return array with prices of one product
//     * @throws IOException
//     */
//    public static String[][] parsePOP() throws IOException {
//        String[][] tab = load(new File(dataDirectory, "majitel1.txt"));
//        String[][] price = new String[tab.length][2];
//        String parts;
//        String[] split;
//        for (int i = 0; i < price.length; i++) {
//            parts = tab[i][0];
//            split = parts.split("[ ]+");
//            price[i][0] = split[0];
//            price[i][1] = split[1];
//        }
//        return price;
//    }

    private static void price() {
        System.out.println(" ");
        System.out.println("Majitel zapaltil za vsechny produkty " +money.price()+"Kc.");
        
    }
}
