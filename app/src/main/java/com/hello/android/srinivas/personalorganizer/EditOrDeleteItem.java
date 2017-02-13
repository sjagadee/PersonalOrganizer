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
 * Created by srinivas on 2/11/17.
 */

public class EditOrDeleteItem extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button editItem;
    private Button deleteItem;
    private EditText itemEdit;
    private Spinner prioritySpinnerEdit;
    private String itemName;
    private String priorityName;
    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_or_delete_item);

        // below two lines would set up app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        itemEdit = (EditText) findViewById(R.id.itemEdit);
        prioritySpinnerEdit = (Spinner) findViewById(R.id.edit_priority_spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.priority, android.R.layout.simple_spinner_dropdown_item);
        prioritySpinnerEdit.setAdapter(adapter);

        Intent intent = this.getIntent();
        itemName = intent.getStringExtra("item");
        priorityName = intent.getStringExtra("priority");
        id = intent.getExtras().getInt("id");

        itemEdit.setText(itemName);
        prioritySpinnerEdit.setSelection(((ArrayAdapter<String>)prioritySpinnerEdit.getAdapter()).getPosition(priorityName));

        editItem = (Button) findViewById(R.id.b_edit_item);
        deleteItem = (Button) findViewById(R.id.b_delete_item);

        editItem.setOnClickListener(this);
        deleteItem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.b_edit_item) {
            if(itemEdit.getText() != null) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("item", itemEdit.getText().toString());
                intent.putExtra("priority", prioritySpinnerEdit.getSelectedItem().toString());
                intent.putExtra("action", "Update");
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Enter data", Toast.LENGTH_LONG).show();
            }
        }
        if(v.getId() == R.id.b_delete_item) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("action", "Delete");
            intent.putExtra("id", id);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
