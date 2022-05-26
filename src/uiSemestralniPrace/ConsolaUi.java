package uiSemestralniPrace;

import app.Product;
import app.TabOwner;
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

/**
 *
 * @author Lucie Strnadkova
 */
public class ConsolaUi {

    private int choice;
    private static Scanner sc = new Scanner(System.in);
    private static app.Customer customer;
    private static app.Owner owner;
    private static app.Money money;
    private static String parent;
    private static File dataDirectory;

    public static void main(String[] args) throws IOException { 
        try {
            String[][] tabOw, tabCus;
            boolean end = false;
            int choice;
            parent = System.getProperty("user.dir") + File.separator + "data";
            dataDirectory = new File(parent);
            owner = new app.Owner();
            money = new app.Money();
            customer = new app.Customer();
            tabOw = load(new File(dataDirectory, "majitel1.txt"));
            parselOw(tabOw);
            tabCus = load(new File(dataDirectory, "zakaznik.txt"));
            parselCus(tabCus);
            String input = "";
            do {
                System.out.println("Kdo jsi?");
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
                            end = true;
                            break;
                        default:
                            System.out.println("neplatna volba ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(input + " neni cislo");
                }
            } while (!end);
        } catch (IOException e) {
            System.out.println("Neco se pokazilo");
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
                        case 0:
                            end = true;
                            break;
                        default:
                            System.out.println("neplatna volba");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(input + " neni cislo");
                }
            } while (!end);
        } catch (IOException e) {
            System.out.println("Neco se pokazilo");
        }
    }

    /**
     * display menu for owner
     */
    private static void displayMenuOwner() {
        System.out.println("1 = zobrazit produkty");
        System.out.println("2 = informace o vydelku");
        System.out.println("3 = zobrazit ceny jednotlivych produktu");
        System.out.println("0 = zpet");
    }

    /**
     * menu for customer
     *
     * @throws IOException
     */
    private static void customer() throws IOException { 
        try {
            System.out.println("Vitam te v Horskem stanku.");
            System.out.println("Kde zaplatis kolik budes chtit.");
            String date = customer.date();
            System.out.println("Dnes je " + date);
            boolean end = false;
            String input = "";
            int choice;
            do {
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
                    }
                } catch (NumberFormatException e) {
                    System.out.println(input + " neni cislo");
                }
            } while (!end);
        } catch (IOException e) {
            System.out.println("Neco se pokazilo");
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
        System.out.println(owner);
    }

    /**
     * get money from customer
     *
     * @throws IOException
     */
    private static void pay() throws IOException { 
        try {
            System.out.println("Zadejte castku");
            String input = "";
            try {
                input = sc.next();
                int number = Integer.parseInt(input);
                money.moneyFromCustomer(number);
            } catch (NumberFormatException e) {
                System.out.println(input + " neni cislo");
            }
            System.out.println("Dekuju za zaplaceni");
            money.saveBinaryFile(new File(dataDirectory, "price.dat"));
        } catch (IOException e) {
            System.out.println("Chyba pri praci se souborem");
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
            System.out.println("Napiste nazev produktu a pocet");
            try {
                String name = sc.next();
                input = sc.next();
                int amount = Integer.parseInt(input);
                customer.pickProduct(name, amount);
            } catch (NumberFormatException e) {
                System.out.println(input + " neni cislo");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            System.out.println("Zkuste to znovu");
        }
        customer.saveFile(new File(dataDirectory, "zakaznikUlozeni.txt"));
    }

    /**
     * display products sort by what it is (food or drink)
     *
     * @throws IOException
     */
    private static void displayProducts() {
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
            money.saveFile(new File(dataDirectory, "price.dat"));
        } catch (IOException e) {
            System.out.println("Chyba pri praci se souborem");
        }
        try {
            System.out.println(money.readBinaryFile(new File(dataDirectory, "price.dat"))); 
            System.out.println(money.income());
        } catch (IOException e) {
            System.out.println("Chyba pri praci se souborem");
        }

    }

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

    private static void parselOw(String[][] tab) {
        List<TabOwner> tabOwner = new ArrayList<>();
        String parts;
        String[] split;
        TabOwner r;
        for (int i = 0; i < tab.length; i++) {
            parts = tab[i][0];
            split = parts.split("[ ]+");
            r = new TabOwner(split[0], Integer.parseInt(split[1]));
            tabOwner.add(r);

        }
        owner.saveOwnertab(tabOwner);
    }

    private static void parselCus(String[][] tab) {
        List<Product> tabCustomer = new ArrayList<>();
        String parts;
        String[] split;
        Product r;
        for (int i = 0; i < tab.length; i++) {
            parts = tab[i][0];
            split = parts.split("[ ]+");
            r = new Product(Integer.parseInt(split[0]), split[1], split[2]);
            tabCustomer.add(r);
        }
        customer.saveCustomertab(tabCustomer);
    }

    public static String[][] parseAmount() throws IOException {
        String[][] tab = load(new File(dataDirectory, "zakaznik.txt"));
        String[][] amount = new String[tab.length][2];
        String parts;
        String[] split;
        for (int i = 0; i < amount.length; i++) {
            parts = tab[i][0];
            split = parts.split("[ ]+");
            amount[i][0] = split[0];
            amount[i][1] = split[1];
        }
        return amount;
    }

    public static String[][] parsePOP() throws IOException {
        String[][] tab = load(new File(dataDirectory, "majitel1.txt"));
        String[][] price = new String[tab.length][2];
        String parts;
        String[] split;
        for (int i = 0; i < price.length; i++) {
            parts = tab[i][0];
            split = parts.split("[ ]+");
            price[i][0] = split[0];
            price[i][1] = split[1];
        }
        return price;
    }
}
