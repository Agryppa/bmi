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

    Button mButton;
    EditText height;
    EditText mass;

    Switch switch1;
    TextView hText;
    TextView mText;
    boolean isMetric=true;





    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        String mTemp = sp.getString("mSave", "");
        String hTemp = sp.getString("hSave", "");
        Boolean uTemp=sp.getBoolean("uSave", false);
        if(mTemp!=null)
            mass.setText(mTemp);
        if(hTemp!=null)
            height.setText(hTemp);
        if(uTemp!=null)
            switch1.setChecked(uTemp);
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
                SharedPreferences.Editor sedt = sp.edit();

                sedt.putString("mSave", mass.getText().toString());
                sedt.putString("hSave", height.getText().toString());
                sedt.putBoolean("uSave", switch1.isChecked());
                sedt.commit();
                break;
            default:
                break;
        }
        return true;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);


        mass = findViewById(R.id.editText);
        height = findViewById(R.id.editText2);

        mButton = findViewById(R.id.button);
        hText=findViewById(R.id.textView2);
        mText=findViewById(R.id.textView);


        switch1=findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });


        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if(!(mass.getText().toString().length()==0||height.getText().toString().length()==0)) {
                            double m = Double.valueOf(mass.getText().toString());
                            double h = Double.valueOf(height.getText().toString());
                            Intent intent = new Intent(MainActivity.this, BmiScreen.class);
                            intent.putExtra("m", m);
                            intent.putExtra("h", h);
                            intent.putExtra("unit",isMetric);


                            startActivity(intent);
                        }

                    }
                });





    }
}
