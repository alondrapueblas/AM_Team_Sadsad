package com.example.alondrapueblas.iexpense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class BudgetActivity extends AppCompatActivity {
Button btnOk;
    Database db;
    EditText txtb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        db = new Database(this);

        txtb=(EditText)findViewById(R.id.txtBudget) ;

        //Add Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnOk =(Button)findViewById(R.id.btnBudOK);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ArrayList<IncomesTable> now =db.selectAll_Income();
                txtb.setText(txtb.getText().toString());
                Toast.makeText(getApplicationContext(),"Successfully added a Budget",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BudgetActivity.this, NavBar.class));
                finish();
            }
        });
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

