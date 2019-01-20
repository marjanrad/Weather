package com.example.marjanraad.weather;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<ModelsList> listWeather;
    Context context;
    private GetView getView;
    private int lastPosition = -1;

    Adapter(List<ModelsList> listWeather, Context context) {
        this.listWeather = listWeather;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bachground_list, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (getView != null) {
            getView.items(holder.itemView, position);
        }

        ModelsList modelsList = listWeather.get(position);
        String day = modelsList.getDay();
        String low = modelsList.getLow();
        String text = modelsList.getText();


        //set item for recycler list
        int convertLow = convert(Integer.valueOf(low));
        holder.day.setText(day);
        holder.temp.setText(convertLow + " c");

        //set image icon
        switch (text) {
            case "Sunny":
                holder.icon.setImageResource(R.drawable.sunnyicon);
                break;
            case "Mostly Sunny":
                holder.icon.setImageResource(R.drawable.sunnyicon);
                break;
            case "Snow":
                holder.icon.setImageResource(R.drawable.snowicon);
                break;
            case "Snow Flurries":
                holder.icon.setImageResource(R.drawable.snowicon);
                break;
            case "Light Snow Showers":
                holder.icon.setImageResource(R.drawable.snowicon);
                break;
            case "Blowing Snow":
                holder.icon.setImageResource(R.drawable.snowicon);
                break;
            case "Mixed Rain And Snow":
                holder.icon.setImageResource(R.drawable.snowicon);
                break;
            case "Mixed Rain And Sleet":
                holder.icon.setImageResource(R.drawable.rainicon);
                break;
            case "Freezing Rain":
                holder.icon.setImageResource(R.drawable.hailicon);
                break;
            case "Rain":
                holder.icon.setImageResource(R.drawable.rainicon);
                break;
            case "Cold":
                holder.icon.setImageResource(R.drawable.snowicon);
                break;
            case "Freezing Drizzle":
                holder.icon.setImageResource(R.drawable.snowicon);
                break;
            case "Showers":
                holder.icon.setImageResource(R.drawable.rainicon);
                break;
            case "Hail":
                holder.icon.setImageResource(R.drawable.hailicon);
                break;
            case "Windy":
                holder.icon.setImageResource(R.drawable.windyicon);
                break;
            case "Hot":
                holder.icon.setImageResource(R.drawable.hoticon);
                break;
            case "Heavy Snow":
                holder.icon.setImageResource(R.drawable.snowicon);
                break;
            case "Partly Cloudy":
                holder.icon.setImageResource(R.drawable.partycloudyicon);
                break;
            case "Cloudy":
                holder.icon.setImageResource(R.drawable.cloudicon);
                break;
            case "Mostly Cloudy":
                holder.icon.setImageResource(R.drawable.cloudicon);
                break;
            case "Scattered Showers":
                holder.icon.setImageResource(R.drawable.rainicon);
                break;
            case "Clear":
                holder.icon.setImageResource(R.drawable.sunnyicon);
                break;
            default:
                holder.icon.setImageResource(R.drawable.partycloudyicon);
                break;

        }




        Hawk.init(context).build();
        Hawk.put("DAY", day);

        //set animation
        setAnimation(holder.itemView, position);


    }

    public void getItems(GetView getView) {
        this.getView = getView;
    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView temp, day;
        ImageView icon;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            temp = itemView.findViewById(R.id.backgroundList_temp);
            day = itemView.findViewById(R.id.backgroundList_day);
            icon=itemView.findViewById(R.id.backgroundList_icon);

            //set onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), MainActivity.class);

                    intent.putExtra("DAY", listWeather.get(getAdapterPosition()).getDay());
                    intent.putExtra("DATE", listWeather.get(getAdapterPosition()).getDate());
                    intent.putExtra("LOW", listWeather.get(getAdapterPosition()).getLow());
                    intent.putExtra("TEXT", listWeather.get(getAdapterPosition()).getText());
                    intent.putExtra("HIGH", listWeather.get(getAdapterPosition()).getHigh());

                    itemView.getContext().startActivities(new Intent[]{intent});


                }
            });

        }
    }

    //send view and position for select list on mainActivity
    public interface GetView {
        void items(View view, int position);
    }

    private int convert(int f) {
        int i = (int) ((f - 32) / 1.8);
        return i;
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)

            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

}
