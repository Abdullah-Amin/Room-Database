package com.example.localdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.localdatabase.R;
import com.example.localdatabase.room.Contact;
import com.example.localdatabase.room.ContactDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class AddContactActivity extends AppCompatActivity {

    TextInputEditText nameET, phoneET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        nameET = findViewById(R.id.name_et);
        phoneET = findViewById(R.id.phone_et);
    }

    public void addContact(View view) {
        String name = nameET.getText().toString();
        String phone = phoneET.getText().toString();

        if(name.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Please fill all data", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact contact = new Contact(name, phone);

        ContactDatabase.getInsatance(this).contactDao().addContact(contact);

        startActivity(new Intent(AddContactActivity.this, MainActivity.class));

    }
}