package com.example.marjanraad.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvExit , tvLocation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //create list for days
        List<String> list = new ArrayList<>();
        list.add("Sat");
        list.add("Sun");
        list.add("Mon");
        list.add("Tue");
        list.add("Wed");
        list.add("Thu");

        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        Adapter adapter = new Adapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));


        // set exit butten
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LocationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        tvExit=findViewById(R.id.Drawer_tv_Exit);
        tvLocation=findViewById(R.id.Drawer_tv_Location);
    }
}
