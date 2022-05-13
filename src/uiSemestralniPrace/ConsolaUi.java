package uiSemestralniPrace;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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

    public static void main(String[] args) throws IOException { //IO??
        boolean end = false;
        int choice;
        parent = System.getProperty("user.dir") + File.separator + "data"; 
        dataDirectory = new File(parent);
        do {
            System.out.println("Kdo jsi?");
            displayMenu();
            choice = sc.nextInt();
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
        } while (!end);
    }

    private static void displayMenu() {
        System.out.println("1 = majitel");
        System.out.println("2 = zakaznik");
        System.out.println("0 = konec");
    }

    private static void owner() throws IOException { //IO??
        boolean end = false;
        int choice;
        owner = new app.Owner();
        money = new app.Money();
        customer = new app.Customer();
        do {
            displayMenuOwner();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    displayMoney();
                    break;
                case 4:
                    displaPrice();
                case 0:
                    end = true;
                    break;
                default:
                    System.out.println("neplatna volba");
            }
        } while (!end);
    }

    private static void displayMenuOwner() {
        System.out.println("1 = zobrazit produkty");
        System.out.println("2 = pridat produkty");
        System.out.println("3 = informace o vydelku");
        System.out.println("4 = zobrazit ceny jednotlivych produktu");
        System.out.println("0 = zpet");
    }

    private static void customer() throws IOException { //muze tam byt IOException???
        System.out.println("Vitam te v Horskem stanku.");
        System.out.println("Kde zaplatis kolik budes chtit.");
        System.out.println("Dnes je "+"a prave je "); //datuma hodiny Local.date      
        boolean end = false;
        int choice;
        customer = new app.Customer();
        money = new app.Money();
        do {
            displayMenuCustomer();
            choice = sc.nextInt();
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
                    rate();
                    break;
                case 5:
                    showMap();
                    break;
                case 0: 
                    end = true;
                    break;
                default:
                    System.out.println("neplatna volba");
            }
        } while (!end);
    }

    private static void displayMenuCustomer() {
        System.out.println("1 = zobrazit menu");
        System.out.println("2 = vybrat produkty");
        System.out.println("3 = zaplatit");
        System.out.println("4 = ohodnotit");
        System.out.println("5 = ukazat mapu");
        System.out.println("0 = zpet");
    }

    /**
     * display products and amount
     *
     * @throws IOException
     */
    private static void displayProducts() throws IOException { //io??
        customer.loadCustomer(new File(dataDirectory, "zakaznik.txt"));
        System.out.println(customer);
    }

    /**
     * display products with price
     * @throws IOException 
     */
    private static void displaPrice() throws IOException {
        owner.loadOwner(new File(dataDirectory, "majitel1.txt"));
        System.out.println(owner);
    }

    private static void pay() {
        System.out.println("Zadejte castku");
        money.moneyFromCustomer(sc.nextInt());
        System.out.println("Dekuju");
    }

    private static void pickProduct() {
        System.out.println("Napiste nazev produktu a pocet");
        customer.pickProduct(sc.next(), sc.nextInt());
    }

    private static void addProduct() {
        System.out.println("Napiste nazev a mnozstvi produktu, ktere chcete pridat");
        customer.addproduct(sc.next(), sc.nextInt());
    }


}
