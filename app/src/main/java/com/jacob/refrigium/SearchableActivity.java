package com.jacob.refrigium;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        // Hide/show textviews based on query size
        TextView noResults = (TextView)findViewById(R.id.noResultsTextView);
        TextView foodResults = (TextView)findViewById(R.id.foodItemResultsTextView);
        TextView recipeResults = (TextView)findViewById(R.id.recipeResultsTextView);
        noResults.setVisibility(View.GONE);

        // Instantiate arrays for food and recipe results
        ListView foodItemResultsListView = (ListView)findViewById(R.id.foodItemResultsListView);
        FoodItem[] foodItems = {};

        ListView recipeResultsListView = (ListView)findViewById(R.id.recipeResultsListView);
        Recipe[] recipes = {};

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            // Read the query and search arrays
            String query = intent.getStringExtra(SearchManager.QUERY);
            DatabaseHandler foodDb = new DatabaseHandler(this);
            foodItems = foodDb.search(query).toArray(new FoodItem[foodDb.search(query).size()]);

            // TODO: add recipe db handler

            if (foodItems.length == 0 && recipes.length == 0) {
                noResults.setVisibility(View.VISIBLE);
                foodResults.setVisibility(View.GONE);
                recipeResults.setVisibility(View.VISIBLE);
            }
            foodDb.close();
        }

        // Insert the List into the ListView
        ListAdapter foodItemsAdapter = new FoodItemAdapter(this, foodItems);
        foodItemResultsListView.setAdapter(foodItemsAdapter);

        // TODO: use query to read recipe database
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_searchable, menu);
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
