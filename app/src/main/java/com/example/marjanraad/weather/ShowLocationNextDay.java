package com.example.marjanraad.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

public class ShowLocationNextDay extends AppCompatActivity {
    TextView day , temp , text , location ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location_next_day);

        init();

        Hawk.init(getApplicationContext()).build();
        String showDay=Hawk.get("DAY");
        day.setText(showDay);


    }

    private void init() {
        day=findViewById(R.id.ShowLocation_day);
        temp=findViewById(R.id.ShowLocation_daraje);
        location=findViewById(R.id.ShowLocation_location);
        text=findViewById(R.id.ShowLocation_weather);
    }
}
