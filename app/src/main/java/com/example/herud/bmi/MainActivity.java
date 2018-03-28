package com.example.herud.bmi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static String massSave = "mSave";
    private static String heightSave = "hSave";
    private static String unitSave = "uSave";

    private Button mButton;
    private EditText height;
    private EditText mass;

    private Switch unitSwitch;
    private TextView hText;
    private TextView mText;
    private boolean isMetric=true;






    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        String mTemp = sp.getString(massSave, "");
        String hTemp = sp.getString(heightSave, "");
        Boolean uTemp=sp.getBoolean(unitSave, false);
        if(mTemp!=null)
            mass.setText(mTemp);
        if(hTemp!=null)
            height.setText(hTemp);
        if(uTemp!=null)
            unitSwitch.setChecked(uTemp);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactimenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i3=new Intent(MainActivity.this, Photo.class);
                startActivity(i3);
                break;
            case R.id.action_save:

                SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor spEditor = sp.edit();

                spEditor.putString(massSave, mass.getText().toString());
                spEditor.putString(heightSave, height.getText().toString());
                spEditor.putBoolean(unitSave, unitSwitch.isChecked());
                spEditor.commit();
                break;
            default:
                break;
        }
        return true;
    }

    private void initFields()
    {
        mass = findViewById(R.id.editText);
        height = findViewById(R.id.editText2);
        mButton = findViewById(R.id.button);
        hText=findViewById(R.id.textView2);
        mText=findViewById(R.id.textView);
        unitSwitch=findViewById(R.id.switch1);
        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
    }

    View.OnClickListener buttonListener()
    {
        View.OnClickListener listener=new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(!(mass.getText().toString().length()==0||height.getText().toString().length()==0)) {
                    double massVal = Double.valueOf(mass.getText().toString());
                    double heightVal = Double.valueOf(height.getText().toString());
                    Intent intent = new Intent(MainActivity.this, BmiScreen.class);

                    BmiObj bmiO=new BmiObj(massVal,heightVal, isMetric);
                    Double bmiVal=null;
                    try {
                        bmiVal = bmiO.calculate();

                    }catch(IllegalArgumentException e){}
                    if(bmiVal!=null)
                        BmiScreen.start(MainActivity.this,bmiVal, bmiO.getCategory());
                }

            }
        };
        return listener;
    }
    CompoundButton.OnCheckedChangeListener switchListener()
    {
        CompoundButton.OnCheckedChangeListener listener=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isMetric=false;
                    hText.setText(R.string.height_inch);
                    mText.setText(R.string.mass_lbs);
                }
                else {
                    isMetric=true;
                    hText.setText(R.string.height_m);
                    mText.setText(R.string.mass_kg);
                }
            }
        };

        return listener;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFields();

        unitSwitch.setOnCheckedChangeListener(switchListener());
        mButton.setOnClickListener(buttonListener());

    }
}
