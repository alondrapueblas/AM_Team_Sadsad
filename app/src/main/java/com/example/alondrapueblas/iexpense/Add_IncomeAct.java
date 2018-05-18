package com.example.alondrapueblas.iexpense;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Add_IncomeAct extends AppCompatActivity {
    Database db;
    EditText txtAmt, txtDate, txtNote;
    Button btnSave;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income);

        db = new Database(this);
        //Add Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtAmt=(EditText)findViewById(R.id.txtInc_Amt);
        txtDate=(EditText)findViewById(R.id.txtInc_Date);
        txtNote=(EditText)findViewById(R.id.txtInc_Note);
        btnSave=(Button)findViewById(R.id.btnInc_Save);
        spin=(Spinner)findViewById(R.id.incomeSpin);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = txtDate.getText().toString();
                String note = txtNote.getText().toString();
                String type = spin.getSelectedItem().toString();
                String user="";
              ArrayList<LogUserTable> LogUsers=db.selectLogUser();
               for (int index=0;index < LogUsers.size();index++){
                   user=LogUsers.get(index).user;
                }

                if(txtAmt.toString().equals("")||date.equals("")){
                    Toast.makeText(getApplicationContext(),"All the Fields are Required to fill",Toast.LENGTH_SHORT).show();
                }else {
                    int ID=0;
                    ArrayList<IncomesTable> incomes = db.selectAll_Income();
                    for (int index=0;index < incomes.size();index++){
                        ID=incomes.get(index).in_id;
                    }
                    ID=ID+1;
                    double amt = Double.parseDouble(txtAmt.getText().toString());
                    db.addIncome(ID,user,type,amt,date,note);
                    Toast.makeText(getApplicationContext(),"Successfully Added an Income! ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Add_IncomeAct.this,Income_Act.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }//onCREATE
        public void onStart() {
            super.onStart();
            EditText txtDateV = (EditText) findViewById(R.id.txtInc_Date);
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
