package com.jacob.refrigium;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Create and implement Refrigium graphic for header
        // TODO: Add ads at the bottom of all activities
        // TODO: Add settings for notification times and date preference
        // TODO: Add edit and delete tools for FoodItems

        // Read main activity objects and instantiate them
        Button addFoodItemButton = (Button)findViewById(R.id.addFoodItemButton);
        ListView foodItemListView = (ListView)findViewById(R.id.foodItemListView);

        // Instantiate SQLite Database
        SQLiteDatabase db = openOrCreateDatabase("FoodItemDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS FoodItems (Icon INT, Name VARCHAR, ExpirationDate VARCHAR)");

        // Read SQLite Database items and index them in an ArrayList
        Cursor selectQuery = db.rawQuery("SELECT * FROM FoodItems ORDERBY ExpirationDate", null);
        db.close();
        ArrayList<FoodItem> foodItems = new ArrayList();
        for (selectQuery.moveToFirst(); !selectQuery.isAfterLast(); selectQuery.moveToNext()) {
            foodItems.add(new FoodItem(Integer.parseInt(selectQuery.getString(0)), selectQuery.getString(1), new Date(selectQuery.getString(2))));
        }

        // Concatenate names and expiration dates into another ArrayList for display
        ArrayList<String> foodItemsAdapterList = new ArrayList();

        for (int i = 0; i < foodItems.size(); i++) {
            foodItemsAdapterList.add(foodItems.get(i).getName() + "\n" + foodItems.get(i).getExpirationDate().toString());
        }

        // Insert the ArrayLists into the ListView
        ArrayAdapter<String> foodItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foodItemsAdapterList);
        foodItemListView.setAdapter(foodItemsAdapter);

        // Instantiate the OnClickListener for addFoodItemButton
        addFoodItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddFoodItemActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
        }

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
