package com.example.herud.bmi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class BmiScreen extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_screen);

        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);


        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);





        TextView bmi = findViewById(R.id.textView4);
        TextView yBmi=findViewById(R.id.textView5);
        bmi.setText("nope");
        Bundle extras = getIntent().getExtras();
        double mass = extras.getDouble("m");
        double height = extras.getDouble("h");
        boolean isMetric=extras.getBoolean("unit");


            BmiObj bmiO=new BmiObj(mass,height, isMetric);
            try{
                double bmiVal=bmiO.calculate();
                bmi.setText(String.valueOf(bmiVal));
                if(bmiVal>25) {
                    yBmi.setTextColor(getResources().getColor(R.color.red));
                    bmi.setTextColor(getResources().getColor(R.color.red));
                } else
                if(bmiVal<18.5) {
                    yBmi.setTextColor(getResources().getColor(R.color.blue));
                    bmi.setTextColor(getResources().getColor(R.color.blue));
                } else {
                    yBmi.setTextColor(getResources().getColor(R.color.green));
                    bmi.setTextColor(getResources().getColor(R.color.green));
                }
            }catch(IllegalArgumentException e){
                yBmi.setText("illegal arguments");
            }







    }
}
