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
    String selected="";

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
       // Hawk.put("LOCATION","tehran");
        String toastCity=Hawk.get("LOCATION","tehran");
        Toast.makeText(this, toastCity, Toast.LENGTH_SHORT).show();

        //set radio button

            int hawkradio=Hawk.get("radio",1);
            switch (hawkradio) {
                case 1:
                    radioTehran.setChecked(true);
                    Log.d("loog","1");
                    break;
                case 2:
                    radioIsfahan.setChecked(true);
                    Log.d("loog","2");
                    break;
                case 3:
                    radioTabriz.setChecked(true);
                    Log.d("loog","3");
                    break;
                case 4:
                    radiokerman.setChecked(true);
                    Log.d("loog","4");
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
                String toastCitys=Hawk.get("LOCATION");
                Toast.makeText(LocationActivity.this, toastCitys, Toast.LENGTH_SHORT).show();
                Hawk.put("radio", radioButton.getId());

                //set radio button value in hawk
                switch (radioButton.getText().toString()) {
                    case "tehran":
                        Hawk.put("radio",1);
                        Log.d("loog","tehran");
                        break;
                    case "isfahan":
                        Hawk.put("radio",2);
                        Log.d("loog","isfahan");
                        break;
                    case "tabriz":
                        Hawk.put("radio",3);
                        Log.d("loog","tabriz");
                        break;
                    case "kerman":
                        Hawk.put("radio",4);
                        Log.d("loog","kerman");
                        break;
                    default:
                        Log.d("loog","defult");
                        break;

                }
                selected="1";
                Log.d("loog","boolean");
                finish();

            }
        });


    }



}
