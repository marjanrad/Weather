package com.example.marjanraad.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

public class ShowLocationNextDay extends AppCompatActivity {
    TextView day, temp, text, location , showDate;
    RelativeLayout relativ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location_next_day);

        init();

        Intent intent = getIntent();
        String days = intent.getStringExtra("DAY");
        String date = intent.getStringExtra("DATE");
        String low = intent.getStringExtra("LOW");
        String high = intent.getStringExtra("HIGH");
        String textWeather = intent.getStringExtra("TEXT");


        switch (days) {
            case "Sat":
                day.setText("Saturday");
                break;
            case "Sun":
                day.setText("Sunday");
                break;
            case "Mon":
                day.setText("Monday");
                break;
            case "Tue":
                day.setText("Tuesday");
                break;
            case "Thu":
                day.setText("Thursday");
                break;
            case "Wed":
                day.setText("Wednesday");
                break;
            case "Fri":
                day.setText("Friday");
                break;
            default:
                break;

        }

        temp.setText(low + " - " + high +" c");
        text.setText(textWeather);
        showDate.setText(date);
        relativ.setBackgroundResource(R.drawable.snow);


    }

    private void init() {
        day = findViewById(R.id.ShowLocation_day);
        temp = findViewById(R.id.ShowLocation_daraje);
        location = findViewById(R.id.ShowLocation_location);
        text = findViewById(R.id.ShowLocation_weather);
        relativ = findViewById(R.id.ShowLocation_Relative);
        showDate=findViewById(R.id.ShowLocation_date);
    }
}
