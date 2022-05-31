/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import app.Product;
import java.util.Comparator;

/**
 *
 * @author lucka
 */
public class ComparatorProductByAmount implements Comparator<Product> {

    @Override
    public int compare(Product o1,Product o2 ){
        return o1.getAmount()-(o2.getAmount());
    }
}
