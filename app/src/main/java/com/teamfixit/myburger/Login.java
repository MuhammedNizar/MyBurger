package com.teamfixit.myburger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamfixit.myburger.R;

public class Login extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;



    public static sqlhelper db;

    public static sqlhelper sqliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = findViewById(R.id.login_username);
        e2 = findViewById(R.id.login_password);
        b1 = findViewById(R.id.login_btn);
        b2 = findViewById(R.id.direct_signUp);


        db = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        sqliteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);



        //nevigate to sign up
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,register.class);
                startActivity(intent);

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();



                Boolean checkUsernamePassword = db.emailPassword(s1,s2);

                if (checkUsernamePassword){

                    Intent intent = new Intent(Login.this,user_Home_Page.class);
                    intent.putExtra("username1",s1);
                    startActivity(intent);

                } else if (s1.equals("admin123") && s2.equals("admin123")){

                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }

                else {

                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();

                    e1.setText("");
                    e2.setText("");
                }

            }
        });
    }

}