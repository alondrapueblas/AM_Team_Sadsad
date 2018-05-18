package com.example.alondrapueblas.iexpense;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.alondrapueblas.iexpense.Add_IncomeAct;
import com.example.alondrapueblas.iexpense.Database;
import com.example.alondrapueblas.iexpense.Income_Act;
import com.example.alondrapueblas.iexpense.IncomesTable;

import java.util.ArrayList;

/**
 * Created by Alondra Pueblas on 18/05/2018.
 */

public class EditOnClick implements View.OnClickListener {

    public int id;
    Database db;
    Context ctx;
    Income_Act income;
    public EditOnClick(int Id, Database ex, Context c){{
    id = Id;
    db =ex;
    ctx =c;
    }
    }
    @Override
    public void onClick(View view) {
        ArrayList<IncomesTable> incomes = db.selectAll_Income();
        Intent i = new Intent(ctx,Add_IncomeAct.class);
         i.putExtra("ID",incomes.get(id).in_id);
         i.putExtra("Type",incomes.get(id).intype);
        i.putExtra("Amount",incomes.get(id).amt);
        i.putExtra("Date", incomes.get(id).date);

     /*   in_id =id;
        username = user;
        intype = type;
        amt = amount;
        date =indate;
        note = notes;*/
    }
}
