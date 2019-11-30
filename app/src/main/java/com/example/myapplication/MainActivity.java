package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private ListView lv;
    private ArrayList<Customer> info = new ArrayList<>();

    private ContactDHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
        setUpDatabase();
        setUpAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomerAdapter adapter = (CustomerAdapter) lv.getAdapter();
        adapter.updateData(dbHelper.getAllContacts());
    }

    private void findViews() {
        btnAdd = findViewById(R.id.add);
        lv = findViewById(R.id.lv);
    }

    private void setListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(i);
            }
        });
    }

    private void setUpDatabase() {
        dbHelper = new ContactDHelper(this);
    }

    private void setUpAdapter() {
        ArrayList<Customer> contactList = new ArrayList<>();
        contactList
                = dbHelper.getAllContacts();

        CustomerAdapter adapter = new CustomerAdapter(contactList, this);
        lv.setAdapter(adapter);
    }
}

