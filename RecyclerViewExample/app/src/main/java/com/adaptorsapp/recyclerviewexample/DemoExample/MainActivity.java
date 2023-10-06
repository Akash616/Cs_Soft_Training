package com.adaptorsapp.recyclerviewexample.DemoExample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.adaptorsapp.recyclerviewexample.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_one = findViewById(R.id.recycler_one);
        recycler_one.setLayoutManager(new LinearLayoutManager(this));

        List<DetailsModelClass> list = new ArrayList<>();
        list.add(new DetailsModelClass(R.drawable.man_1, "Name1", "Android"));
        list.add(new DetailsModelClass(R.drawable.man_2, "Name2", "Java"));
        list.add(new DetailsModelClass(R.drawable.man_3, "Name3", "Kotlin"));
        list.add(new DetailsModelClass(R.drawable.man_6, "Name4", "Flutter"));
        list.add(new DetailsModelClass(R.drawable.man_7, "Name5", "Kotlin"));
        list.add(new DetailsModelClass(R.drawable.woman_1, "Name6", "Android"));
        list.add(new DetailsModelClass(R.drawable.woman_2, "Name7", "React"));
        list.add(new DetailsModelClass(R.drawable.man_1, "Name1", "Android"));
        list.add(new DetailsModelClass(R.drawable.man_2, "Name2", "Java"));
        list.add(new DetailsModelClass(R.drawable.man_3, "Name3", "Kotlin"));
        list.add(new DetailsModelClass(R.drawable.man_6, "Name4", "Flutter"));
        list.add(new DetailsModelClass(R.drawable.man_7, "Name5", "Kotlin"));
        list.add(new DetailsModelClass(R.drawable.woman_1, "Name6", "Android"));
        list.add(new DetailsModelClass(R.drawable.woman_2, "Name7", "React"));

        ItemAdapter itemAdapter = new ItemAdapter(this, list);
        recycler_one.setAdapter(itemAdapter);

    }
}