package com.example.herud.bmi;

import android.content.Context;
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

import java.util.Locale;

public class BmiScreen extends AppCompatActivity {
    private  static String bmiKey="bmi";
    private static String categoryKey="category";
    private TextView bmiText;
    private TextView yourBmi;
    private Toolbar myChildToolbar;
    //private double bmiVal;

    public static void start(Context context,double bmi,BmiCategory category) {
        Intent starter = new Intent(context, BmiScreen.class);

        starter.putExtra(bmiKey,bmi);
        starter.putExtra(categoryKey,category);
        context.startActivity(starter);
    }
    private int getBmiColor()
    {
        Bundle extras = getIntent().getExtras();
        BmiCategory bmiCategory= (BmiCategory) extras.getSerializable(categoryKey);
        if(bmiCategory==BmiCategory.OVERWEIGHT)
            return getResources().getColor(R.color.red);
        else if(bmiCategory==BmiCategory.UNDERWEIGHT)
            return getResources().getColor(R.color.blue);
        return getResources().getColor(R.color.green);
    }
    private void initFields()
    {
        bmiText = findViewById(R.id.textView4);
        yourBmi=findViewById(R.id.textView5);
        myChildToolbar =(Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_screen);
        initFields();

        Bundle extras = getIntent().getExtras();
        double bmiVal=extras.getDouble(bmiKey);

        bmiText.setText(String.valueOf(bmiVal));


        int textColor=getBmiColor();

        yourBmi.setTextColor(textColor);
        bmiText.setTextColor(textColor);








    }
}
