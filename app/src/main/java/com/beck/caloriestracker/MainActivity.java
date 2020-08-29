package com.beck.caloriestracker;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ovqat";
    public static Resources resources;
    RecyclerView infoRecycler;
    long milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources = getResources();
        FloatingActionButton addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddMeal.class));

            }
        });

        DayScrollDatePicker mPicker = findViewById(R.id.day_date_picker);

        mPicker.setStartDate(10, 8, 2020);
        mPicker.setEndDate(30, 8, 2020);

        mPicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                if (date != null) {
                    milliseconds = date.getTime();
                    Log.i(TAG, "onDateSelected: " + milliseconds);

                    SQLiteDatabase sqLiteDatabase = MainActivity.this.openOrCreateDatabase("Data", MODE_PRIVATE, null);
                    Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM calories WHERE date==" + milliseconds, null);
                    int dateIndex = c.getColumnIndex("date");
                    int posIndex = c.getColumnIndex("pos");

                    if (c.moveToFirst()) {
                        while (!c.isAfterLast()) {
                            Log.i(TAG, c.getString(posIndex));
                            c.moveToNext();
                        }
                    }
                    c.close();

                    // do something with selected date
                }
            }
        });

        infoRecycler = findViewById(R.id.info_recycler);
        InfoAdapter adapter = new InfoAdapter();
        infoRecycler.setLayoutManager(new LinearLayoutManager(this));
        infoRecycler.setAdapter(adapter);


    }
}