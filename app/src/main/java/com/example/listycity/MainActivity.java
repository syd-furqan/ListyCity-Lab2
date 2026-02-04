package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare variables to reference them later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1; // Track the index of the city selected for deletion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize UI components from the layout
        cityList = findViewById(R.id.city_list); //
        final EditText addCityField = findViewById(R.id.add_city_field);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button deleteCityButton = findViewById(R.id.delete_city_button);

        // 2. Initialize the data list with some default cities
        String[] cities = {"Edmonton", "Vancouver", "Toronto", "Lahore", "Berlin", "Tokyo"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities)); //

        // 3. Set up the ArrayAdapter to link the dataList to the ListView
        // We use the 'content.xml' layout for each row and 'content_view' as the TextView ID
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter); //

        // 4. ADD CITY Logic: Adds the text from the EditText to the list
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = addCityField.getText().toString();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName); // Update the data source
                    cityAdapter.notifyDataSetChanged(); // Refresh the display
                    addCityField.setText(""); // Clear the input field for next use
                }
            }
        });

        // 5. SELECTION Logic: Detects which city in the list was tapped
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position; // Save the index of the clicked item
            }
        });

        // 6. DELETE CITY Logic: Removes the selected city from the list
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Only remove if an item has actually been selected
                if (selectedPosition != -1) {
                    dataList.remove(selectedPosition); // Remove from data source
                    cityAdapter.notifyDataSetChanged(); // Refresh the display
                    selectedPosition = -1; // Reset selection so we don't accidentally delete again
                }
            }
        });
    }
}