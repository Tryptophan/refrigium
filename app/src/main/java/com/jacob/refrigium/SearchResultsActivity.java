package com.jacob.refrigium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        String query = getIntent().getStringExtra("searchQuery");

        ListView foodItemResultsListView = (ListView)findViewById(R.id.foodItemResultsListView);

        DatabaseHandler db = new DatabaseHandler(this);
        FoodItem[] foodItems = db.search(query).toArray(new FoodItem[db.search(query).size()]);
        db.close();

        // Insert the List into the ListView
        ListAdapter foodItemsAdapter = new FoodItemAdapter(this, foodItems);
        foodItemResultsListView.setAdapter(foodItemsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}