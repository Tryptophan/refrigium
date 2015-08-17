package com.jacob.refrigium;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Date;
import java.util.Scanner;


public class AddFoodItemActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        // Instantiate views from activity layout
        Button addFoodItemSubmitButton = (Button)findViewById(R.id.addFoodItemSubmitButton);
        // TODO: add carousel for food type options

        // TODO: Expiration hint printing sequence of numbers (id?)
        // Set hint for expiration date format dynamically based on settings
        EditText foodItemExpirationDateField = (EditText)findViewById(R.id.foodItemExpirationDateField);
        foodItemExpirationDateField.setHint("Expiration Date " + getDateFormat());

        // Instantiate onClickListener for addFoodItemSubmitButton
        addFoodItemSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Read user input for food item params
                // TODO: add food item type to form
                String foodType = "vegetable";
                EditText foodItemNameField = (EditText)(findViewById(R.id.foodItemNameField));
                EditText foodItemExpirationDateField = (EditText)(findViewById(R.id.foodItemExpirationDateField));

                String name = foodItemNameField.getText().toString();
                String expirationDate = foodItemExpirationDateField.getText().toString();

                // Set new id using size of table
                DatabaseHandler db = new DatabaseHandler(view.getContext());
                // Insert values into SQLite Database
                db.addFoodItem(new FoodItem(db.getFoodItems().size(), foodType, name, expirationDate));
                db.close();

                // TOOD: Set conditions for saving and notify user if success or error
                Toast.makeText(AddFoodItemActivity.this, "Saved successfully!", Toast.LENGTH_SHORT).show();

                // Set notificationTask timer
                // TODO: add notificationTask that triggers before expirationDate
                //Timer expirationTimer = new Timer(notificationTask, expirationDate);

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_food_item, menu);
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

    // TODO: Change user input to Date
    public Date stringtoDate(String s, String delimiter) {
        // Loop through the expiration date and convert to date
        Scanner dateScanner = new Scanner(s);
        dateScanner.useDelimiter(delimiter);
        int day, month, year = 0;

        // Parse string
        month = Integer.parseInt(dateScanner.next());
        day = Integer.parseInt(dateScanner.next());
        year = Integer.parseInt(dateScanner.next());

        // Set expiration date
        Date date = new Date(year, month, day);

        return date;
    }
    // TODO: get date format setting from user preferences
    public String getDateFormat() {
        return "";
    }
}
