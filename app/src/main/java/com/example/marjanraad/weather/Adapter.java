package com.example.marjanraad.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> list=new ArrayList<>();

    Adapter(List<String> theList){
        list=theList;
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
        String name=list.get(position);
        holder.day.setText(name);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView daraje, day;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            daraje = itemView.findViewById(R.id.backgroundList_daraje);
            day = itemView.findViewById(R.id.backgroundList_day);

        }
    }
}
