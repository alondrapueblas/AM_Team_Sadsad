package com.example.alondrapueblas.iexpense;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {
    GridLayout expensegrid;
    TextView txtT;
    Database db;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        db =new Database(this);
        expensegrid=(GridLayout)findViewById(R.id.expensegrid);
        txtT=(TextView)findViewById(R.id.txtTotalEx);
        layout=(LinearLayout) findViewById(R.id.listofExpenses);
        //Add Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fbEx);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExpensesActivity.this, Add_Expenses.class));
                finish();
            }
        });
        double  Total =0;
        ArrayList<LogUserTable> u =db.selectLogUser();
        ArrayList<ExpensesTable> expensesTables = db.selectByUser_Expenses(u.get(0).user);
        for(int i=0;i<expensesTables.size();i++){
            Button b =new Button(getApplicationContext());
            String am = expensesTables.get(i).amt+"";
            b.setText(""+expensesTables.get(i).intype.toString() + "    "+am +"  "+expensesTables.get(i).date);
            Total +=expensesTables.get(i).amt;
            layout.addView(b);

            // b.setOnClickListener(new EditOnClick(i,db,getApplicationContext()));
        }

        txtT.setText(""+Total);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}