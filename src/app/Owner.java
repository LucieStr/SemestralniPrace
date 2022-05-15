package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author lucka
 */
public class Owner  {
    
    private String name;
    private List<TabOwner> tabOwner;

    public Owner() {
        this.name = name;
        this.tabOwner = new ArrayList<>();
    }


    public List<TabOwner> getTabOwner() {
        ArrayList<TabOwner> copy = new ArrayList<>();
        for (TabOwner tab : tabOwner) {
            copy.add(new TabOwner());
        }
        return copy;
    }
    
  /**
   * load file for owner
   * @param ownerFile
   * @throws FileNotFoundException
   * @throws IOException 
   */
    public void loadOwner(File ownerFile) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ownerFile))) {
            String[] parts;
            String line;
            TabOwner r;
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                r = new TabOwner(parts[0], Integer.parseInt(parts[1]));
                
                tabOwner.add(r);
            }
        }
    }

    /**
     * safe file for owner
     * @param result
     * @throws IOException 
     */
    public void safeOwner (File result) throws IOException{
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(result)))){
            pw.println(String.format("%10s %5s","Nazev","Cena"));
            for (TabOwner tab : tabOwner) {
                pw.println(tab);
                
            }
        }
    }
    
    /**
     * upgrade price of one product
     * @param priceOneProduct
     * @param name 
     */
    public void setPriceOneProduct(int priceOneProduct,String name) {
        for (TabOwner tab : tabOwner) {
            if(name == null ? tab.getName() == null : name.equals(tab.getName())){
               tab.setProductPrice(priceOneProduct);
            }
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
        o.loadOwner(new File("zkouska2.txt"));
        System.out.println(o);
        o.setPriceOneProduct(15, "Mila");
        System.out.println(o);
        o.safeOwner(new File("zkouska4.txt"));
        
    }




}
