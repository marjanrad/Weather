package com.example.marjanraad.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

public class LocationActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnSet;
    RadioButton radioTehran, radioIsfahan, radioTabriz, radiokerman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        radioGroup = findViewById(R.id.location_radio_group);
        btnSet = findViewById(R.id.location_btnSet);
        radioTehran = findViewById(R.id.location_radio_tehran);
        radiokerman = findViewById(R.id.location_radio_kerman);
        radioIsfahan = findViewById(R.id.location_radio_isfahan);
        radioTabriz = findViewById(R.id.location_radio_tabriz);

        Hawk.init(getApplicationContext()).build();

        //set radio button
        int hawkradio = Hawk.get("radio", 1);
        switch (hawkradio) {
            case 1:
                radioTehran.setChecked(true);
                break;
            case 2:
                radioIsfahan.setChecked(true);
                break;
            case 3:
                radioTabriz.setChecked(true);
                break;
            case 4:
                radiokerman.setChecked(true);
                break;
            default:
                break;

        }

        //put values to storage
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);

                Hawk.put("LOCATION", radioButton.getText());
                Hawk.put("radio", radioButton.getId());

                //set radio button value in hawk
                switch (radioButton.getText().toString()) {
                    case "tehran":
                        Hawk.put("radio", 1);
                        break;
                    case "isfahan":
                        Hawk.put("radio", 2);
                        break;
                    case "tabriz":
                        Hawk.put("radio", 3);
                        break;
                    case "kerman":
                        Hawk.put("radio", 4);
                        break;
                    default:
                        break;

                }
                finish();
            }
        });

    }


}
