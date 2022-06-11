package utils;

import app.Product;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Library {

    /*
     * saves file to the "data" directory
     */
    public void saveFile(File result) throws IOException;

    public void saveTab(List<Product> t);
    
}
