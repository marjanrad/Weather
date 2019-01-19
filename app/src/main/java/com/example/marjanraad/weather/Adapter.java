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

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<ModelsList> listWeather;
    Context context;
    private GetView getView;

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

        if (getView != null){
            getView.items(holder.itemView , position);
        }

        ModelsList modelsList = listWeather.get(position);
        String day = modelsList.getDay();
        String date = modelsList.getDate();
        String low = modelsList.getLow();
        String high = modelsList.getHigh();
        String text = modelsList.getText();

        int convertLow=convert(Integer.valueOf(low));
        holder.day.setText(day);
        holder.temp.setText(convertLow +" c");
        holder.text.setText(text);

        Hawk.init(context).build();

        Hawk.put("DAY", day);

    }

    public void getItems(GetView getView){
        this.getView = getView;
    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView temp, day , text;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            temp = itemView.findViewById(R.id.backgroundList_temp);
            day = itemView.findViewById(R.id.backgroundList_day);
            text=itemView.findViewById(R.id.backgroundList_text);

            //set onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), MainActivity.class);

                    intent.putExtra("DAY",listWeather.get(getAdapterPosition()).getDay());
                    intent.putExtra("DATE",listWeather.get(getAdapterPosition()).getDate());
                    intent.putExtra("LOW",listWeather.get(getAdapterPosition()).getLow());
                    intent.putExtra("TEXT",listWeather.get(getAdapterPosition()).getText());
                    intent.putExtra("HIGH",listWeather.get(getAdapterPosition()).getHigh());

                    itemView.getContext().startActivities(new Intent[]{intent});


                }
            });

        }

    }

    public interface GetView{
        void items (View view , int position);
    }
    private int convert(int f) {
        int i = (int) ((f - 32) / 1.8);
        return i;
    }

}
