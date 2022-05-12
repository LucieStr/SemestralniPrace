/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author lucka
 */
public class Money {

    private int income;
    private int price;

    public Money(){
        this.income = income;
        this.price = price;
    }    
    public Money(int income, int price) {
        this.income = income;
        this.price = price;
    }

    public int moneyFromCustomer(int money) {
        return this.income = income + money;
    }

    public Income income() {
        if (income > price) {
            return Income.VYDELEK;
        } else if(income < price){
            return Income.PRODELEK;
        }else{
            return Income.NEPRODELEK;
        }
    }

    public int price() {
        for (TabOwner owner : Owner.tabOwner ) {
            this.price = this.price +(Owner.priceOneProduct * Customer.amount);
        }
        return this.price;
    }

    public String toString() {
        return String.format("%10d %10d", this.price, this.income);       
    }
    
    public static void main(String[] args) throws IOException {
        Money m = new Money();
        Owner r = new Owner();
        Customer c = new Customer();
        c.loadCustomer(new File("zakaznik.txt"));
        r.loadOwner(new File("majitel1.txt"));
        m.price();
        System.out.println(m);
    }
}
