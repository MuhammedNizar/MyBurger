package com.teamfixit.myburger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.teamfixit.myburger.R;

public class register extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    Button b1;

    sqlhelper db;

    AwesomeValidation awesomevalidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        e1 = findViewById(R.id.register_username);
        e2 = findViewById(R.id.register_email);
        e3 = findViewById(R.id.register_contactNumber);
        e4 = findViewById(R.id.register_password);
        e5 = findViewById(R.id.register_confirmPassword);

        //validations
        awesomevalidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomevalidation.addValidation(this, R.id.register_username, RegexTemplate.NOT_EMPTY,R.string.empty_username);
        awesomevalidation.addValidation(this, R.id.register_email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",R.string.invalid_email);
        awesomevalidation.addValidation(this, R.id.register_password,RegexTemplate.NOT_EMPTY, R.string.empty_password);
        awesomevalidation.addValidation(this, R.id.register_confirmPassword, RegexTemplate.NOT_EMPTY, R.string.empty_password);

        b1 = findViewById(R.id.signUp_btn);

        //db connection
        db = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        db.queryData("CREATE TABLE IF NOT EXISTS user(username text primary key, email text, contact text, password text)");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();


                if (awesomevalidation.validate()){
                    if (s4.equals(s5)){

                        Boolean checkUsername = db.checkUsername(s1);

                        if (checkUsername){

                            Boolean insert =db.insertUser(s1,s2,s3,s4);

                            if (insert){

                                Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(register.this, Login.class);
                                startActivity(intent);

                            }

                        }else {
                            Toast.makeText(getApplicationContext(), "Username Already Have", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}