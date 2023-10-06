package com.adaptorsapp.recyclerviewexample.onClickListenerExample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.adaptorsapp.recyclerviewexample.DemoExample.DetailsModelClass;
import com.adaptorsapp.recyclerviewexample.R;
import com.adaptorsapp.recyclerviewexample.onClickListenerExample.adapter.UserAdapter;
import com.adaptorsapp.recyclerviewexample.onClickListenerExample.modalClass.ModalClass;
import com.adaptorsapp.recyclerviewexample.onClickListenerExample.myInterface.MyClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivityOne extends AppCompatActivity implements MyClickListener {

    RecyclerView rv_one;
    List<ModalClass> list;
    ModalClass modalClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        rv_one = findViewById(R.id.rv_one);

        rv_one.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        list.add(new ModalClass(R.drawable.man_1, "Name1", "Android"));
        list.add(new ModalClass(R.drawable.man_2, "Name2", "Java"));
        list.add(new ModalClass(R.drawable.man_3, "Name3", "Kotlin"));
        list.add(new ModalClass(R.drawable.man_6, "Name4", "Flutter"));
        list.add(new ModalClass(R.drawable.man_7, "Name5", "Kotlin"));
        list.add(new ModalClass(R.drawable.woman_2, "Name7", "React"));
        list.add(new ModalClass(R.drawable.man_1, "Name1", "Android"));
        list.add(new ModalClass(R.drawable.woman_1, "Name6", "Android"));
        list.add(new ModalClass(R.drawable.man_2, "Name2", "Java"));
        list.add(new ModalClass(R.drawable.man_3, "Name3", "Kotlin"));
        list.add(new ModalClass(R.drawable.man_6, "Name4", "Flutter"));
        list.add(new ModalClass(R.drawable.man_7, "Name5", "Kotlin"));
        list.add(new ModalClass(R.drawable.woman_1, "Name6", "Android"));
        list.add(new ModalClass(R.drawable.woman_2, "Name7", "React"));

        UserAdapter userAdapter = new UserAdapter(this, list, this); //for the listener i'll pass this
        rv_one.setAdapter(userAdapter);

    }

    @Override
    public void onItemClick(ModalClass modalClass) {
        this.modalClass = modalClass;
        //Toast.makeText(this, modalClass.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivityOne.this, SecondActivityOne.class);
        Bundle bundle = new Bundle();
//        bundle.putInt("Image", modalClass.getImage());
//        bundle.putString("Name", modalClass.getName());
//        bundle.putString("Course", modalClass.getCourse());
        bundle.putSerializable("Details", modalClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}