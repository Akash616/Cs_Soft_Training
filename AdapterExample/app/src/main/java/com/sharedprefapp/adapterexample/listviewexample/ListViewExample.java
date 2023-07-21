package com.sharedprefapp.adapterexample.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sharedprefapp.adapterexample.R;

public class ListViewExample extends AppCompatActivity {

    ListView listview_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_example);

        listview_one = findViewById(R.id.listview_one);

        String[] products_arr = {"Xiaomi", "Samsung", "Vivo", "Oppo", "Apple", "Realme", "OnePlus", "Poco", "Honor", "Nokia", "Asus", "LG", "Motorola",
                "Xiaomi", "Samsung", "Vivo", "Oppo", "Apple", "Realme", "OnePlus", "Poco", "Honor", "Nokia", "Asus", "LG", "Motorola",
                "Xiaomi", "Samsung", "Vivo", "Oppo", "Apple", "Realme", "OnePlus", "Poco", "Honor", "Nokia", "Asus", "LG", "Motorola"};
        int[] price_arr = {10000, 20000, 15000, 25000, 30000, 15999, 12999, 14500, 16945, 18345, 16000, 14000, 45000,
                10000, 20000, 15000, 25000, 30000, 15999, 12999, 14500, 16945, 18345, 16000, 14000, 45000,
                10000, 20000, 15000, 25000, 30000, 15999, 12999, 14500, 16945, 18345, 16000, 14000, 45000};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, products_arr);
        listview_one.setAdapter(arrayAdapter);

        listview_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewExample.this, "Product Name : "+products_arr[position]+"\nPrice : "+price_arr[position], Toast.LENGTH_SHORT).show();
            }
        });

    }
}