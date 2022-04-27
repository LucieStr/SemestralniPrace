package semestralniprace;

import java.util.Scanner;

/**
 *
 * @author Lucie Strnadkova
 */
public class SemestralniPrace {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {        
        boolean end = false;
        int choice;
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
                    System.out.println("neplatna volba volba");
            }
        } while (!end);
    }

    private static void displayMenu() {
        System.out.println("1 = majitel");
        System.out.println("2 = zakaznik");
        System.out.println("0 = konec");
    }

    private static void owner() {
        boolean end = false;
        int choice;
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
                case 0:
                    System.out.println("konec");
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
        System.out.println("0 = konec");
    }

    private static void customer() {
        System.out.println("Vitam te v Horskem stanku.");
        System.out.println("Kde zaplatis kolik budes chtit.");
        boolean end = false;
        int choice;
        do {
            displayMenuCustomer();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    displayDrinks();
                    break;
                case 2:
                    displayfood();
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
                    System.out.println("konec");
                    end = true;
                    break;
                default:
                    System.out.println("neplatna volba");
            }
        } while (!end);
    }

    private static void displayMenuCustomer() {
        System.out.println("1 = zobrazit napoje");
        System.out.println("2 = zobrazit pokrmy");
        System.out.println("3 = zaplatit");
        System.out.println("4 = ohodnotit");
        System.out.println("5 = ukazat mapu");
        System.out.println("0 = konec");
    }
}
