package com.hello.android.srinivas.personalorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView toDoList;
    private ItemsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoList = (RecyclerView) findViewById(R.id.toDoList);
        adapter = new ItemsAdapter(this, getData());
        toDoList.setAdapter(adapter);
        toDoList.setLayoutManager(new LinearLayoutManager(this));

    }

    public static List<Information> getData() {

        List<Information> data = new ArrayList<>();

        int[] icons = {R.mipmap.ic_launcher};
        String[] titles = {"Srini", "Sandy", "Nandu", "Chalie",
                "Bali", "Akki", "Nikki", "Sona", "Mona", "Su", "suni", "Dinesh", "Kishore"};

        for(String text: titles) {
            Information current = new Information();
            current.iconId = icons[0];
            current.title = text;
            data.add(current);
        }
        return data;

    }
}
