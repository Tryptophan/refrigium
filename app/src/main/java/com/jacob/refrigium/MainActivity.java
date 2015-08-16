package com.jacob.refrigium;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Create and implement Refrigium graphic for header
        // TODO: Add settings for notification times and date preference
        // TODO: Add edit and delete tools for FoodItems

        // Read main activity objects and instantiate them
        Button addFoodItemButton = (Button)findViewById(R.id.addFoodItemButton);
        ListView foodItemListView = (ListView)findViewById(R.id.foodItemListView);

        // Read List of FoodItems from SQLite Database
        DatabaseHandler db = new DatabaseHandler(this);
        //this.deleteDatabase("foodItemDb");
        FoodItem[] foodItems = db.getFoodItems().toArray(new FoodItem[db.getFoodItems().size()]);
        db.close();

        // Insert the List into the ListView
        ListAdapter foodItemsAdapter = new FoodItemAdapter(this, foodItems);
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
