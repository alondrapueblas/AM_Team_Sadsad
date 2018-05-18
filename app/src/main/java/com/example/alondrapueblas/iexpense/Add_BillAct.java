package com.example.alondrapueblas.iexpense;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_BillAct extends AppCompatActivity {
Button btn;
EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add__bill);
        //Add Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn=(Button)findViewById(R.id.btnBillOk);
        t1 =(EditText)findViewById(R.id.txtBillDate);
        t2=(EditText)findViewById(R.id.txtBill);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().equals("")||t2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"All fields are Required",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Successfully Added a Bill",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Add_BillAct.this, NavBar.class));
                    finish();
                }

            }
        });
    }//onCREATE
    public void onStart() {
        super.onStart();
        EditText txtDateV = (EditText) findViewById(R.id.txtBillDate);
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
