package com.example.marjanraad.weather;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<ModelsList> listWeather ;
    Context context;


    Adapter(List<ModelsList> listWeather, Context context){
        this.listWeather =listWeather;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.bachground_list,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelsList modelsList= listWeather.get(position);
        String day=modelsList.getDay();
        String date=modelsList.getDate();
        String low=modelsList.getLow();
        String high=modelsList.getHigh();
        String text=modelsList.getText();

        holder.day.setText(day);
        holder.daraje.setText(low);

        Hawk.init(context).build();

            Hawk.put("DAY",day);


    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView daraje, day;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            daraje = itemView.findViewById(R.id.backgroundList_daraje);
            day = itemView.findViewById(R.id.backgroundList_day);

            //set onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(),ShowLocationNextDay.class);

                 //   intent.putExtra(listWeather.get)
                    itemView.getContext().startActivities(new Intent[]{intent});

                }
            });

        }
    }
}
