package com.teamfixit.myburger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.teamfixit.myburger.R;

public class driver_Add extends AppCompatActivity {

    EditText newdriverid,newdrivername,newdrivernumber,newdriveraddress,newdriveremail,newdrivervehiclemodel,newdrivervehiclenumber,newdriverdob;
    Button btnnewdriveradd,btnnewdriverslist;
    AwesomeValidation awesomevalidation;

    public static sqlhelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__add);

        awesomevalidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomevalidation.addValidation(this, R.id.newdriverid, RegexTemplate.NOT_EMPTY, R.string.enter_newdriverid);
        awesomevalidation.addValidation(this, R.id.newdrivername,RegexTemplate.NOT_EMPTY, R.string.enter_newdrivername);
        awesomevalidation.addValidation(this, R.id.newdriverdob,RegexTemplate.NOT_EMPTY, R.string.enter_newdriverdob);
        awesomevalidation.addValidation(this, R.id.newdrivervehiclenumber,RegexTemplate.NOT_EMPTY, R.string.enter_newdrivervehiclenumber);
        awesomevalidation.addValidation(this, R.id.newdrivervehiclemodel,RegexTemplate.NOT_EMPTY, R.string.enter_newdrivervehiclemodel);


        init();

        sqLiteHelper = new sqlhelper(this, "FoodDB.sqlite",null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS DRIVERDETAILS (ID INTEGER PRIMARY KEY AUTOINCREMENT,newdriverid VARCHAR, newdrivername VARCHAR,newdrivernumber VERCHAR,newdriveraddress VARCHAR,newdriveremail VARCHAR,newdrivervehiclemodel VARCHAR, newdrivervehiclenumber VARCHAR ,newdriverdob VARCHAR )");


        btnnewdriveradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomevalidation.validate()){
                    try {
                        sqLiteHelper.insertDataDriver(
                                newdriverid.getText().toString().trim(),
                                newdrivername.getText().toString().trim(),
                                newdrivernumber.getText().toString().trim(),
                                newdriveraddress.getText().toString().trim(),
                                newdriveremail.getText().toString().trim(),
                                newdrivervehiclemodel.getText().toString().trim(),
                                newdrivervehiclenumber.getText().toString().trim(),
                                newdriverdob.getText().toString().trim()
                        );
                        Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
                        newdriverid.setText("");
                        newdrivername.setText("");
                        newdrivernumber.setText("");
                        newdriveraddress.setText("");
                        newdriveremail.setText("");
                        newdrivervehiclemodel.setText("");
                        newdrivervehiclenumber.setText("");
                        newdriverdob.setText("");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    private Boolean emailValidaion(EditText newdriveremail){

        String emailInput = newdriveremail.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(driver_Add.this, "", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(driver_Add.this, "Email validation is failed.Update it again", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void init(){
        newdriverid = (EditText) findViewById(R.id.newdriverid);
        newdrivername = (EditText) findViewById(R.id.newdrivername);
        newdrivernumber = (EditText) findViewById(R.id.newdrivernumber);
        newdriveraddress = (EditText) findViewById(R.id.newdriveraddress);
        newdriveremail = (EditText) findViewById(R.id.newdriveremail);
        newdrivervehiclemodel = (EditText) findViewById(R.id.newdrivervehiclemodel);
        newdrivervehiclenumber = (EditText) findViewById(R.id.newdrivervehiclenumber);
        newdriverdob = (EditText) findViewById(R.id.newdriverdob);
        btnnewdriveradd = (Button) findViewById(R.id.btnnewdriveradd);
        btnnewdriverslist = (Button) findViewById(R.id.btnnewdriverslist);
    }
}