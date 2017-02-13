package com.hello.android.srinivas.personalorganizer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView toDoList;
    private ItemsAdapter adapter;
    ArrayList<Information> info = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this toolbar is used to set the custom created app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        // Set up recycle view
        toDoList = (RecyclerView) findViewById(R.id.toDoList);
        toDoList.setLayoutManager(new LinearLayoutManager(this));
        toDoList.setItemAnimator(new DefaultItemAnimator());

        adapter = new ItemsAdapter(this, info);

        retrieve();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 999 && resultCode == RESULT_OK) {
            String item = data.getStringExtra("item");
            String priority = data.getStringExtra("priority");
            save(item, priority);
        } else if (requestCode == 111 && resultCode == RESULT_OK) {
            int id = data.getExtras().getInt("id");
            String action = data.getStringExtra("action");
            if(action.contains("Delete")) {
                delete(id);
            } else if (action.contains("Update")) {
                String item = data.getStringExtra("item");
                String priority = data.getStringExtra("priority");
                update(id, item,priority);
            }
        }
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

    // Save the data in the database
    private void save(String item, String priority) {
        DatabaseAdapter db = new DatabaseAdapter(this);

        // open database
        db.openDatabase();

        // insert into database
        long result = db.addData(item, priority);

        if(result >  0) {
            Toast.makeText(getApplicationContext(), item +" Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), item +" Insert Unsuccessful", Toast.LENGTH_SHORT).show();
        }
        db.closeDatabase();

        retrieve();
    }

    // retrieve data
    private void retrieve() {
        DatabaseAdapter db = new DatabaseAdapter(this);

        // open database
        db.openDatabase();

        info.clear();

        Cursor cursor = db.getAllInformation();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String item = cursor.getString(1);
            String priority = cursor.getString(2);

            Information i = new Information(id, item, priority);
            info.add(i);

            if(!info.isEmpty()) {
                toDoList.setAdapter(adapter);
            }

        }
    }

    // update database
    private void update(int id, String item, String priority) {
        DatabaseAdapter db = new DatabaseAdapter(this);

        // open database
        db.openDatabase();

        // insert into database
        long result = db.updateData(id, item, priority);

        if(result >  0) {
            Toast.makeText(getApplicationContext(), item +" Updated Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), item +" Update Unsuccessful", Toast.LENGTH_SHORT).show();
        }
        db.closeDatabase();

        retrieve();
    }

    // delete
    private void delete(int id) {
        DatabaseAdapter db = new DatabaseAdapter(this);

        // open database
        db.openDatabase();

        // insert into database
        long result = db.deleteData(id);

        if(result >  0) {
            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Delete Unsuccessful", Toast.LENGTH_SHORT).show();
        }
        db.closeDatabase();

        retrieve();
    }
}
