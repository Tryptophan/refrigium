package com.jacob.refrigium;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacob on 8/16/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "foodItemDb";
    private static final String TABLE_FOOD_ITEMS = "foodItems";

    // Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_NAME = "name";
    private static final String KEY_EXPIRATION_DATE = "expirationDate";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOOD_ITEMS_TABLE = "CREATE TABLE " + TABLE_FOOD_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TYPE + " TEXT, "
                + KEY_NAME + " TEXT, "
                + KEY_EXPIRATION_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_FOOD_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD_ITEMS);
    }

    public void addFoodItem(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, foodItem.getFoodType());
        values.put(KEY_NAME, foodItem.getName());
        values.put(KEY_EXPIRATION_DATE, foodItem.getExpirationDate());

        try {
            db.insert(TABLE_FOOD_ITEMS, null, values);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }

    public FoodItem getFoodItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOOD_ITEMS, new String[] {KEY_ID, KEY_TYPE, KEY_NAME, KEY_EXPIRATION_DATE},
                KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        FoodItem foodItem = new FoodItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return foodItem;
    }

    public List<FoodItem> getFoodItems() {
        List<FoodItem> foodItemList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_FOOD_ITEMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FoodItem foodItem = new FoodItem();
                foodItem.setId(Integer.parseInt(cursor.getString(0)));
                foodItem.setFoodType(cursor.getString(1));
                foodItem.setName(cursor.getString(2));
                foodItem.setExpirationDate(cursor.getString(3));
                foodItemList.add(foodItem);
            } while (cursor.moveToNext());
        }
        return foodItemList;
    }

    public int getFoodItemsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FOOD_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public List<FoodItem> search(String query) {
        List<FoodItem> foodItems = getFoodItems();
        List<FoodItem> newFoodItems = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getFoodType().equalsIgnoreCase(query) ||
                    foodItems.get(i).getExpirationDate().equals(query) ||
                    foodItems.get(i).getName().equalsIgnoreCase(query)) {
                newFoodItems.add(foodItems.get(i));
            }
        }

        return newFoodItems;
    }

    public int updateFoodItem(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, foodItem.getFoodType());
        values.put(KEY_NAME, foodItem.getName());
        values.put(KEY_EXPIRATION_DATE, foodItem.getExpirationDate());

        return db.update(TABLE_FOOD_ITEMS, values, KEY_ID + " = ?", new String[] {String.valueOf(foodItem.getId())});
    }

    public void deleteFoodItem(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD_ITEMS, KEY_ID + " = ?", new String[] {String.valueOf(foodItem.getId())});
        db.close();
    }
}
