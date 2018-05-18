package com.example.alondrapueblas.iexpense;

/**
 * Created by Alondra Pueblas on 16/05/2018.
 */

public class ExpensesTable {
    public ExpensesTable(int id,String user, String type, double amount,String exdate,String notes){
        in_id =id;
        username = user;
        intype = type;
        amt = amount;
        date =exdate;
        note = notes;
    }
    public String username,intype,date,note;
    public double amt;
    public int in_id;
}
