package com.beck.caloriestracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 Created by Otabek Abduraufov on 8/29/2020.

*/
class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    public InfoAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView calorie;
        TextView amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.info_name);
            calorie = itemView.findViewById(R.id.info_calorie);
            amount = itemView.findViewById(R.id.info_amount);
        }
    }
}
