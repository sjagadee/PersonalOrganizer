package com.hello.android.srinivas.personalorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView toDoList;
    private ItemsAdapter adapter;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this toolbar is used to set the custom created app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        toDoList = (RecyclerView) findViewById(R.id.toDoList);
        adapter = new ItemsAdapter(this, getData());
        toDoList.setAdapter(adapter);
        toDoList.setLayoutManager(new LinearLayoutManager(this));


    }

    public static List<Information> getData() {

        List<Information> data = new ArrayList<>();

        // should change according the list input

        int[] icons = {R.mipmap.ic_launcher};
        String[] titles = {"Srini", "Sandy", "Nandu", "Chalie",
                "Bali", "Akki", "Nikki", "Sona", "Mona", "Su", "suni", "Dinesh", "Kishore"};

        for (String text : titles) {
            Information current = new Information();
            current.itemName = "";
            current.priorityName = "";
            data.add(current);
        }
        return data;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(getApplicationContext(), AddNewItem.class);
                startActivity(intent);
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
