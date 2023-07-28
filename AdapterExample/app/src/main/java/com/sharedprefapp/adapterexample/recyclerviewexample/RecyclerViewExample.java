package com.sharedprefapp.adapterexample.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sharedprefapp.adapterexample.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExample extends AppCompatActivity {

    RecyclerView recyclerview_demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);

        recyclerview_demo = findViewById(R.id.recyclerview_demo);


        List<StudentModalClass> student_list = new ArrayList<>();

        StudentModalClass student_obj = new StudentModalClass();
        student_obj.setName("Akash");
        student_obj.setCourse("Android");
        student_obj.setImage(R.drawable.man_1);
        student_list.add(student_obj);

        StudentModalClass student_obj2 = new StudentModalClass();
        student_obj2.setName("Ravi");
        student_obj2.setCourse("Flutter");
        student_obj2.setImage(R.drawable.man_2);
        student_list.add(student_obj2);

        StudentModalClass student_obj3 = new StudentModalClass();
        student_obj3.setName("Rahul");
        student_obj3.setCourse("Java");
        student_obj3.setImage(R.drawable.man_5);
        student_list.add(student_obj3);

        StudentModalClass student_obj4 = new StudentModalClass();
        student_obj4.setName("Radha");
        student_obj4.setCourse("Kotlin");
        student_obj4.setImage(R.drawable.woman_1);
        student_list.add(student_obj4);

        StudentModalClass student_obj5 = new StudentModalClass();
        student_obj5.setName("Krishna");
        student_obj5.setCourse("Java");
        student_obj5.setImage(R.drawable.man_1);
        student_list.add(student_obj5);

        StudentModalClass student_obj6 = new StudentModalClass();
        student_obj6.setName("Rohan");
        student_obj6.setCourse("C#");
        student_obj6.setImage(R.drawable.man_2);
        student_list.add(student_obj6);

        StudentModalClass student_obj7 = new StudentModalClass();
        student_obj7.setName("Swati");
        student_obj7.setCourse("Python");
        student_obj7.setImage(R.drawable.woman_2);
        student_list.add(student_obj7);

        StudentModalClass student_obj8 = new StudentModalClass();
        student_obj8.setName("Manoj");
        student_obj8.setCourse("Java");
        student_obj8.setImage(R.drawable.man_6);
        student_list.add(student_obj8);

        ItemAdapter itemAdapter = new ItemAdapter(this, student_list);
        recyclerview_demo.setLayoutManager(new LinearLayoutManager(this));
        recyclerview_demo.setAdapter(itemAdapter);

    }
}