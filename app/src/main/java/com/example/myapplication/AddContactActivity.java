package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {
    private Button btnSave;
    private EditText editName, editNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        findViews();
        setListeners();
    }
    private void findViews(){
        btnSave = findViewById(R.id.btnSave);
        editName = findViewById(R.id.editName);
        editNumber = findViewById(R.id.editNumber);
    }

    private void setListeners(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editName.getText().toString().isEmpty() &&
                        !editNumber.getText().toString().isEmpty()){
                    ContactDHelper dbHelper = new ContactDHelper( AddContactActivity.this);
                    Customer contact = new Customer();
                    contact.setName(editName.getText().toString());
                    contact.setNumber(editNumber.getText().toString());
                    dbHelper.insertContact(contact);
                    AddContactActivity.this.finish();
                } else {
                    if(editName.getText().toString().isEmpty()){
                        editName.setError("Cannot be empty");
                    }
                    if(editNumber.getText().toString().isEmpty()) {
                        editNumber.setError("Cannot be empty");
                    }
                }
            }
        });
    }
}
