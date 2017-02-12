package com.hello.android.srinivas.personalorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
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
    private crudOperation crud;


    private Toolbar toolbar;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this toolbar is used to set the custom created app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        intent = getIntent();

        toDoList = (RecyclerView) findViewById(R.id.toDoList);
        toDoList.setLayoutManager(new LinearLayoutManager(this));
        toDoList.setItemAnimator(new DefaultItemAnimator());

        crud = new crudOperation(InformationCollection.getInformation());
        adapter = new ItemsAdapter(this, crud.getInformations());

        //toDoList.setAdapter(adapter);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 999 && resultCode == RESULT_OK) {
            Information information = new Information();
            information.setItemName(data.getStringExtra("item"));
            information.setPriorityName(data.getStringExtra("priority"));

            if(crud.addNewInfo(information)) {
                toDoList.setAdapter(adapter);
            }
        }
    }

    public static List<Information> getData(Intent intent) {

        List<Information> data = new ArrayList<>();

        String item = intent.getStringExtra("item");
        String priority = intent.getStringExtra("priority");

        Information current = new Information();
        current.itemName = item;
        current.priorityName = priority;
        data.add(current);

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
            case R.id.add_new_item:
                Intent intent = new Intent(getApplicationContext(), AddNewItem.class);
                startActivityForResult(intent, 999);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
