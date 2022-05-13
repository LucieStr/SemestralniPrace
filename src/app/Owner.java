package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author lucka
 */
public class Owner  {
    private int priceOneProduct;
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
    
  
    public int getPriceOneProduct() {
        return this.priceOneProduct;
    }
    
    public void loadOwner(File ownerFile) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ownerFile))) {
            String[] parts;
            String line;
            TabOwner r;
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                r = new TabOwner(parts[0], Integer.parseInt(parts[1]));
                priceOneProduct = Integer.parseInt(parts[1]);
                tabOwner.add(r);
            }
        }
    }

//prepisovani majitel1
    

    public void setPriceOneProduct(int priceOneProduct,String name) {
        for (TabOwner tab : tabOwner) {
            if(name == this.name){
               this.priceOneProduct = priceOneProduct; 
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
        o.loadOwner(new File("majitel1.txt"));
        System.out.println(o);
        
        
    }




}
