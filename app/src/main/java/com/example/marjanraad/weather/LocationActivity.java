package com.example.marjanraad.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

public class LocationActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnSet;
    RadioButton radioTehran, radioIsfahan, radioTabriz, radioFars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        radioGroup = findViewById(R.id.location_radio_group);
        btnSet = findViewById(R.id.location_btnSet);
        radioTehran = findViewById(R.id.location_radio_tehran);
        radioFars = findViewById(R.id.location_radio_fars);
        radioIsfahan = findViewById(R.id.location_radio_isfahan);
        radioTabriz = findViewById(R.id.location_radio_tabriz);


        Hawk.init(getApplicationContext()).build();
        int radioId = Hawk.get("RADIO_LOCATION");
        Toast.makeText(this, radioId, Toast.LENGTH_SHORT).show();
        switch (radioId) {
            case 1:
                radioTehran.isChecked();
                break;
            case 2:
                radioIsfahan.isChecked();
                break;
            default:
                Toast.makeText(this, "breake", Toast.LENGTH_SHORT).show();
                break;

        }


        //put values to storage
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);
                Hawk.put("LOCATION", radioButton.getText());
//                Hawk.put("RADIO_LOCATION",radioButtonId);
                int s = (int) radioButton.getTag();
                Hawk.put("RADIO_LOCATION", s);

            }
        });
    }


}
