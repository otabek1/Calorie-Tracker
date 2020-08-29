package com.beck.caloriestracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 Created by Otabek Abduraufov on 8/29/2020.

*/
public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {


    Meals mMeals;
    private OnMealListener mOnMealListener;

    public MealAdapter(Meals meals, OnMealListener onMealListener) {
        this.mMeals = meals;
        this.mOnMealListener = onMealListener;
    }


    @NonNull
    @Override
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnMealListener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {

        String name = mMeals.getNames().get(position);
        int image = mMeals.getPics().get(position);
        int calories = mMeals.getCalories().get(position);

        holder.setData(name, calories, image);


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public interface OnMealListener {
        void onMealClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnMealListener mOnMealListener;
        TextView name;
        TextView calorie;
        ImageView image;

        public ViewHolder(@NonNull View itemView, OnMealListener onMealListener) {
            super(itemView);
            this.mOnMealListener = onMealListener;
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.meal_name_text_single);
            calorie = itemView.findViewById(R.id.meal_calories_hunderd);
            image = itemView.findViewById(R.id.meal_image);

        }


        @Override
        public void onClick(View v) {
            mOnMealListener.onMealClicked(getAdapterPosition());
        }

        public void setData(String name, int calories, int image) {
            this.name.setText(name);
            calorie.setText(String.valueOf(calories));
            this.image.setImageResource(image);
        }
    }
}