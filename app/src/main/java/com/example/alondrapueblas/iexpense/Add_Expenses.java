package com.example.alondrapueblas.iexpense;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_Expenses extends AppCompatActivity {
    Database db;
    EditText txtAmt, txtDate, txtNote;
    Button btnSave;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add__expenses);
        db = new Database(this);

        //Add Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtAmt=(EditText)findViewById(R.id.txtExpAmount);
        txtDate=(EditText)findViewById(R.id.txtExpDate);
        txtNote=(EditText)findViewById(R.id.txtExpNote);
        btnSave=(Button)findViewById(R.id.btnExpOK);
        spin=(Spinner)findViewById(R.id.spinnerExpType);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String am = txtAmt.getText().toString();
                String date = txtDate.getText().toString();
                String note = txtNote.getText().toString();
                String type = spin.getSelectedItem().toString();
                String user="";

                if(am.equals("")||date.equals("")){
                    Toast.makeText(getApplicationContext(),"All the Fields are Required to fill",Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<LogUserTable> list = db.selectLogUser();
                    if(list.size()>0){
                        for (int index=0;index < list.size();index++){
                          //  Toast.makeText(getApplicationContext(),"All Log Users" + list.get(index).user.toString(),Toast.LENGTH_SHORT).show();
                            user=list.get(index).user.toString();
                        }
                    }
                    double amt = Double.parseDouble(txtAmt.getText().toString());
                    db.addExpenses(user,type,amt,date,note);
                    Toast.makeText(getApplicationContext(),"Successfully Added an Expense! ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Add_Expenses.this,ExpensesActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }//onCREATE
    public void onStart() {
        super.onStart();
        EditText txtDateV = (EditText) findViewById(R.id.txtExpDate);
        txtDateV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    DateDialog dialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "Date of Acquired");
                }
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
