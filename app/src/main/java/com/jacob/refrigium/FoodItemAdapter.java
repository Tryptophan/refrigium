package com.jacob.refrigium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by jacob on 8/16/15.
 */
public class FoodItemAdapter extends ArrayAdapter<FoodItem> {
    public FoodItemAdapter(Context context, FoodItem[] values) {
        super(context, R.layout.food_item_row_layout, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.food_item_row_layout, parent, false);

        // TODO: Setep icons for different images
        //String foodItemTypeImageView = getItem(position).getFoodType();

        // Set TextViews for list items from db
        String foodItemName = getItem(position).getName();
        TextView foodItemNameTextView = (TextView)view.findViewById(R.id.foodItemNameTextView);
        foodItemNameTextView.setText(foodItemName);

        String foodItemExpirationDate = getItem(position).getExpirationDate();
        TextView foodItemExpirationDateTextView = (TextView)view.findViewById(R.id.foodItemExpirationDateTextView);
        foodItemExpirationDateTextView.setText(foodItemExpirationDate);

        return view;
    }
}
