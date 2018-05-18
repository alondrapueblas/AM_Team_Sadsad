package com.example.alondrapueblas.iexpense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

   Database db;
    EditText fname, lname, address, username;
    Pinview pin1, pin2;
    Button btnSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new Database(this);
        //Add Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fname =(EditText)findViewById(R.id.txtFname);
        lname =(EditText)findViewById(R.id.txtLname);
        address =(EditText)findViewById(R.id.txtAddress);
        username =(EditText)findViewById(R.id.txtUname);
        pin1 = (Pinview)findViewById(R.id.pinview1);
        pin2 = (Pinview)findViewById(R.id.pinview2);
        btnSign =(Button)findViewById(R.id.btnSignUp);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn, ln,add, un, p1, p2;

                fn = fname.getText().toString();
                ln = lname.getText().toString();
                add = address.getText().toString();
                un = username.getText().toString();
                p1 = pin1.getValue().toString();
                p2 = pin2.getValue().toString();
                int i1 = p1.length();
                int i2 = p2.length();


                if (fn.equals("")|| ln.equals("")||add.equals("")||un.equals("")||pin1.equals("")||pin2.equals("")||i1<6 || i2<6){
                    Toast.makeText(getApplicationContext(),"All the Fields are Required to fill",Toast.LENGTH_SHORT).show();
                }else{

                    if (p1.equals(p2)){
                        ArrayList<AccountsTable> userList=db.selectUserAccounts();
                        Boolean userExist=false;
                        for (int index=0;index < userList.size();index++){
                            if(userList.get(index).username.equals(un)){
                                userExist=true;
                            }
                        }
                        if (userExist==true){
                            Toast.makeText(getApplicationContext(),"Username already taken!",Toast.LENGTH_SHORT).show();
                        }else {
                            db.addUserAccounts(fn,ln,add,un,p1);
                            Toast.makeText(getApplicationContext(),"Successfully Created an Account!",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUpActivity.this,LogINActivity.class);
                            startActivity(i);
                            finish();
                        }

                    }else {
                        Toast.makeText(getApplicationContext(),"PIN do not match!",Toast.LENGTH_SHORT).show();
                    }

                }//main IF Else

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
