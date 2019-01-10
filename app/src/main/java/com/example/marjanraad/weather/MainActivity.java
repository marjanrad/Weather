package com.example.marjanraad.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.marjanraad.weather.weatherpack.Forecast;
import com.example.marjanraad.weather.weatherpack.Weather;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    TextView tvExit, tvLocation, showLocation;
    List<ModelsList> modelsLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //save data in phone
        Hawk.init(getApplicationContext()).build();


        // set butten for menuo
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                startActivity(intent);
            }
        });


    }

    private void init() {
        tvExit = findViewById(R.id.Drawer_tv_Exit);
        tvLocation = findViewById(R.id.Drawer_tv_Location);
        showLocation = findViewById(R.id.main_tv_location);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
       // String location = Hawk.get("LOCATION");

    //    showLocation.setText(location);


        String url = "http://www.mocky.io/v2/5c2fb801320000b62e59093e";

        //connected to internet
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                //use Gson for using json
                Gson gson = new Gson();
                Weather weather = gson.fromJson(response.toString(), Weather.class);
                initList(weather);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("loog", "log3");
            }
        });
    }

    private void initList(Weather api) {
        List<Forecast> forecasts = api.getResult().getForecast();
        modelsLists = new ArrayList<>();

        for (int i = 0; i < forecasts.size(); i++) {
            String date = api.getResult().getForecast().get(i).getDate();
            String low = api.getResult().getForecast().get(i).getLow();
            String high = api.getResult().getForecast().get(i).getHigh();
            String day = api.getResult().getForecast().get(i).getDay();
            String text = api.getResult().getForecast().get(i).getText();

            modelsLists.add(new ModelsList(date, day, low, high, text));
        }

        Adapter adapter = new Adapter(modelsLists, MainActivity.this);

        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

    }
}
