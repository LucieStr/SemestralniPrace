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
public class Owner implements Library{

    private String name;
    private List<TabOwner> tabOwner;

    public Owner() {
        this.name = name;
        this.tabOwner = new ArrayList<>();
    }

    public List<TabOwner> getTabOwner() {
        ArrayList<TabOwner> copy = new ArrayList<>();
        for (TabOwner tab : tabOwner) {
            copy.add(new TabOwner(tab));
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
            for (TabOwner tab : tabOwner) {
                pw.println(tab);

            }
        }
    }

    public void saveOwnertab(List<TabOwner> tab) {
        this.tabOwner = tab;
    }

    /**
     * upgrade price of one product
     *
     * @param priceOneProduct
     * @param name
     */
    public void setPriceOneProduct(String name, int priceOneProduct) {
        for (TabOwner tab : tabOwner) {
            //if (tab.getName() == null ? name == null : tab.getName().equals(name)) {
                tab.setProductPrice(priceOneProduct);
               
            //}
            // throw new NoSuchElementException("Produkt s nazvem " + name + " neexistuje");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TabOwner owner : tabOwner) {
            sb.append(owner).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Owner o = new Owner();

        //o.loadOwner(new File("zkouska2.txt"));
        System.out.println(o);
        //o.setPriceOneProduct(15, "Mila");
        System.out.println(o);
       // o.saveOwner(new File("zkouska4.txt"));

    }

}
