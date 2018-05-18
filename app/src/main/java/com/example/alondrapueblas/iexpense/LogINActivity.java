package com.example.alondrapueblas.iexpense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

import java.util.ArrayList;

public class LogINActivity extends AppCompatActivity {
    TextView txtLink;
    Database db;
    EditText  username;
    Pinview pin;
    Button btnLog;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        db = new Database(this);


        txtLink =(TextView)findViewById(R.id.txtLinkCreate);
        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogINActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        username =(EditText)findViewById(R.id.txtLOGUser);
        pin = (Pinview)findViewById(R.id.pinLOG);
        btnLog =(Button)findViewById(R.id.btnLogIn);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pinLog = pin.getValue().toString();
                int i1 = pinLog.length();

                ArrayList<AccountsTable> userList = db.selectUserAccounts();
                Boolean userExist=false;
                for (int index=0;index < userList.size();index++){
                    if(userList.get(index).username.equals(user) && userList.get(index).pins.equals(pinLog)){
                        userExist=true;
                        break;
                    }
                }
                if (user.equals("")|| pinLog.equals("")||i1<6){
                    Toast.makeText(getApplicationContext(),"All the Fields are Required to fill",Toast.LENGTH_SHORT).show();
                }else{
                    if (userExist==false){
                        Toast.makeText(getApplicationContext(),"Wrong Username or Password! ",Toast.LENGTH_SHORT).show();
                    }else {


                         ArrayList<LogUserTable> logUser =db.selectLogUser();
                      if(logUser.size()==0){
                            db.addLogUser(1,user);
                        }else {db.deleteLogUser(1);
                         db.addLogUser(1,user);
                        }


                            Toast.makeText(getApplicationContext(),"Log In Successfully!",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LogINActivity.this,NavBar.class);
                            startActivity(i);
                            finish();
                    }
                }

            }
        });


    }

}
