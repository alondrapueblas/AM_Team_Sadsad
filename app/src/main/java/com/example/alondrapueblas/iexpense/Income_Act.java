package com.example.alondrapueblas.iexpense;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Income_Act extends AppCompatActivity {
GridLayout incomegrid;
TextView txtT;
Database db;
public LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_);
        db =new Database(this);
        incomegrid=(GridLayout)findViewById(R.id.incomegrid);
        txtT=(TextView)findViewById(R.id.txtTotal);
        layout=(LinearLayout) findViewById(R.id.listofIncome);

        //Add Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Income_Act.this, Add_IncomeAct.class));
                finish();
            }
        });
        double  Total =0;
        ArrayList<LogUserTable> u =db.selectLogUser();
        ArrayList<IncomesTable> incomes = db.selectByUser_Income(u.get(0).user);
        for(int i=0;i<incomes.size();i++){
            Button b =new Button(getApplicationContext());
            String am = incomes.get(i).amt+"";
            b.setText(""+incomes.get(i).intype.toString() + "    "+am +"  "+incomes.get(i).date);
            Total +=incomes.get(i).amt;
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
