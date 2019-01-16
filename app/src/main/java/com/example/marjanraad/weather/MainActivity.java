package com.example.marjanraad.weather;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tvExit, tvLocation, showLocation, showText, showTemp, showDay;
    List<ModelsList> modelsLists;
    RelativeLayout setBackground;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

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
        showText = findViewById(R.id.main_tv_text);
        showTemp = findViewById(R.id.main_tv_temp);
        showDay = findViewById(R.id.main_tv_day);
        setBackground = findViewById(R.id.main_relative);
        dl = findViewById(R.id.dl);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        String location = Hawk.get("LOCATION");

        String url = "http://phoenix-iran.ir/Files_php_App/WeatherApi/newApiWeather.php?city=" + location + "";

        //connected to internet
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                if (!response.equals("null")) {
                    //use Gson for using json
                    Gson gson = new Gson();
                    Weather weather = gson.fromJson(response.toString(), Weather.class);
                    initList(weather);
                    String text = weather.getResult().getCondition().getText();
                    showText.setText(text);
                    int temp = convert(weather.getResult().getCondition().getTemperature());
                    showTemp.setText(temp + " c");
                    String city = weather.getResult().getLocation().getCity();
                    showLocation.setText(city);

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    private int convert(int f) {
        int i = (int) ((f - 32) / 1.8);
        return i;
    }

    //Create list
    private void initList(final Weather api) {
        final List<Forecast> forecasts = api.getResult().getForecasts();
        modelsLists = new ArrayList<>();

        for (int i = 0; i < forecasts.size(); i++) {
            String date = api.getResult().getForecasts().get(i).getDate().toString();
            String low = api.getResult().getForecasts().get(i).getLow().toString();
            String high = api.getResult().getForecasts().get(i).getHigh().toString();
            String day = api.getResult().getForecasts().get(i).getDay();
            String text = api.getResult().getForecasts().get(i).getText();

            modelsLists.add(new ModelsList(date, day, low, high, text));
        }

        final Adapter adapter = new Adapter(modelsLists, MainActivity.this);

        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        adapter.getItems(new Adapter.GetView() {
            @Override
            public void items(View view, final int position) {

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int lowValue = convert(Integer.valueOf(modelsLists.get(position).getLow()));
                        int highValue = convert(Integer.valueOf(modelsLists.get(position).getHigh()));

                        String text = modelsLists.get(position).getText();
                        String day = modelsLists.get(position).getDay();

                        showTemp.setText(lowValue + " c _ " + highValue + " c ");
                        showText.setText(text);
                        setSetBackgroundDay(text);

                        // set names day
                        switch (day) {
                            case "Sat":
                                showDay.setText("Saturday");
                                break;
                            case "Sun":
                                showDay.setText("Sunday");
                                break;
                            case "Mon":
                                showDay.setText("Monday");
                                break;
                            case "Tue":
                                showDay.setText("Tuesday");
                                break;
                            case "Thu":
                                showDay.setText("Thursday");
                                break;
                            case "Wed":
                                showDay.setText("Wednesday");
                                break;
                            case "Fri":
                                showDay.setText("Friday");
                                break;
                            default:
                                break;

                        }


                    }
                });

            }
        });

    }

    public void setSetBackgroundDay(String text) {

        if (text.equals("Cloudy")|| text.equals("Partly Cloudy")){
            setBackground.setBackgroundResource(R.drawable.freezing);
        }else if (text.equals("Sunny")){

        }

//        switch (text) {
//            case "Sunny":
//                setBackground.setBackgroundResource(R.drawable.sunny);
//                break;
//            case "Cloudy":
//                setBackground.setBackgroundResource(R.drawable.cloud);
//                break;
//            case "Cold":
//                setBackground.setBackgroundResource(R.drawable.freezing);
//                break;
//            case "Hail":
//                setBackground.setBackgroundResource(R.drawable.hail);
//                break;
//            case "Partly Cloudy":
//                setBackground.setBackgroundResource(R.drawable.cloud);
//                break;
//            default:
//                break;
//        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:

                dl.openDrawer(Gravity.START);

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (dl.isDrawerOpen(Gravity.START)) {
            dl.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }
}
