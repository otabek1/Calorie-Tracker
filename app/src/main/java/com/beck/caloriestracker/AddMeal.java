package com.beck.caloriestracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class AddMeal extends AppCompatActivity implements MealAdapter.OnMealListener {

    private static final String TAG = "ovqat";
    RecyclerView mealsRecycler;
    MealAdapter mAdapter;
    Meals mMeals = new Meals();
    long milliseconds;
    String selectedMealName;
    int selectedMealCalorie;
    int position;
    int amount;
    EditText amountEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        mAdapter = new MealAdapter(mMeals, this);

        mealsRecycler = findViewById(R.id.meals_recycler);
        mealsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mealsRecycler.setAdapter(mAdapter);


    }

    @Override
    public void onMealClicked(int position) {
        selectedMealName = mMeals.getNames().get(position);
        selectedMealCalorie = mMeals.getCalories().get(position);
        this.position = position;

//    startActivity(new Intent(AddMeal.this, AddDetails.class));
        showDialog();

    }

    public void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_details, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();


        Button save = dialogView.findViewById(R.id.save_btn);
        amountEdt = dialogView.findViewById(R.id.amount_edt);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                milliseconds = date.getTime();
                amount = Integer.parseInt(amountEdt.getText().toString());
                SQLiteDatabase sqLiteDatabase = AddMeal.this.openOrCreateDatabase("Data", MODE_PRIVATE, null);
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS calories (date INT, pos INT, amount INT)");
                sqLiteDatabase.execSQL("INSERT INTO calories(date, pos, amount) VALUES (" + milliseconds + "," + position + "," + amount + ")");

                Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM calories", null);
                int dateIndex = c.getColumnIndex("date");
                int mealIndex = c.getColumnIndex("pos");

                if (c.moveToFirst()) {
                    while (!c.isAfterLast()) {
//                        Log.i(TAG, c.getString(mealIndex));
                        c.moveToNext();
                    }
                }
                c.close();

                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }
}