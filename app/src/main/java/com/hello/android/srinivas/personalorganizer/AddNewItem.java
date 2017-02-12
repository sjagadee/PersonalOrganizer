package com.hello.android.srinivas.personalorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by srinivas on 2/5/17.
 */

public class AddNewItem extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button addItem;
    private EditText item;
    private Spinner prioritySpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        // below two lines would set up app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        item = (EditText) findViewById(R.id.item);
        prioritySpinner = (Spinner) findViewById(R.id.priority_spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.priority, android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapter);

        addItem = (Button) findViewById(R.id.b_add_item);

        addItem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.b_add_item) {
            if(item.getText() != null) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                intent.putExtra("item", item.getText().toString());
                intent.putExtra("priority", prioritySpinner.getSelectedItem().toString());
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Enter data", Toast.LENGTH_LONG).show();
            }
        }
    }
}
