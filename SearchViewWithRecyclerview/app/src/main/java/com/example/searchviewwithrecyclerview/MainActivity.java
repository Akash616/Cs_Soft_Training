package com.example.searchviewwithrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    List<ItemModel> list;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list.add(new ItemModel(R.drawable.man_1, "Name1", "Android"));
        list.add(new ItemModel(R.drawable.man_2, "Name2", "Java"));
        list.add(new ItemModel(R.drawable.man_3, "Name3", "Kotlin"));
        list.add(new ItemModel(R.drawable.man_6, "Name4", "Flutter"));
        list.add(new ItemModel(R.drawable.man_7, "Name5", "Kotlin"));
        list.add(new ItemModel(R.drawable.woman_1, "Name6", "Android"));
        list.add(new ItemModel(R.drawable.woman_2, "Name7", "React"));
        list.add(new ItemModel(R.drawable.man_1, "Name1", "Android"));
        list.add(new ItemModel(R.drawable.man_2, "Name2", "Java"));
        list.add(new ItemModel(R.drawable.man_3, "Name3", "Kotlin"));
        list.add(new ItemModel(R.drawable.man_6, "Name4", "Flutter"));
        list.add(new ItemModel(R.drawable.man_7, "Name5", "Kotlin"));
        list.add(new ItemModel(R.drawable.woman_1, "Name6", "Android"));
        list.add(new ItemModel(R.drawable.woman_2, "Name7", "React"));

        itemAdapter = new ItemAdapter(this, list);
        recyclerView.setAdapter(itemAdapter);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus(); //remove the cursor from the searchview
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }
    private void filterList(String text) {
        List<ItemModel> filteredList = new ArrayList<>();
        for (ItemModel item : list) {
            if (item.course.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            filteredList.add(0, new ItemModel(R.drawable.empty, "Not Found", "......."));
            itemAdapter.setFilteredList(filteredList);
        } else {
            itemAdapter.setFilteredList(filteredList);
        }
    }
}