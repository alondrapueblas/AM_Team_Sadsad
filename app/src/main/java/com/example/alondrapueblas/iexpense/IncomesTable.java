package com.example.alondrapueblas.iexpense;

/**
 * Created by Alondra Pueblas on 15/05/2018.
 */

public class IncomesTable {

    public IncomesTable(int id,String user, String type, double amount,String indate,String notes){
        in_id =id;
        username = user;
        intype = type;
        amt = amount;
        date =indate;
        note = notes;
    }
    public String username,intype,date,note;
    public double amt;
    public int in_id;
}
