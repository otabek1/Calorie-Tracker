package com.beck.caloriestracker;

import java.util.ArrayList;

/*
 Created by Otabek Abduraufov on 8/29/2020.

*/
public class Meals {
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> pics = new ArrayList<>();
    private ArrayList<Integer> calories = new ArrayList<>();

    public Meals() {
        names.add("Palov");
        String s = MainActivity.resources.getString(R.string.soup);
        names.add(s);
        names.add("Shashlik");
        names.add("Somsa");


        pics.add(R.drawable.plov);
        pics.add(R.drawable.shorva);
        pics.add(R.drawable.kabob);
        pics.add(R.drawable.somsa);

        calories.add(500);
        calories.add(300);
        calories.add(100);
        calories.add(400);
        calories.add(400);


    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Integer> getPics() {
        return pics;
    }

    public ArrayList<Integer> getCalories() {
        return calories;
    }

    public int getSize() {
        return 4;
    }
}
