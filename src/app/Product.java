/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author lucka
 */
public class Product {

    private String name;
    private int amount;

    public Product(int amount, String name) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void getProduct(int amount, String name) {
        if (amount > this.amount) {
            throw new utils.MyException("Zadali jste vetsi mnozstvi nez toho mame.");
        } else {
            this.amount = this.amount - amount;
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%3d kusu %10s", this.amount, this.name);
    }
    public static void main(String[] args) {
        
    }
}
