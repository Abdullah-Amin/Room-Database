package com.example.localdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.localdatabase.R;
import com.example.localdatabase.room.Contact;
import com.example.localdatabase.room.ContactDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contact> contactList = new ArrayList<>();
    MenuI menuI;
    EditContactI editContactI;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.contact_rv);

        openMenu();

        getContacts();

    }

    private void openMenu() {
        menuI = new MenuI() {
            @Override
            public void openMenu(ContactAdapter.ContactHolder holder, Contact contact) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, holder.itemView);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if(id == R.id.edit){

                            editContactI = new EditContactI() {
                                @Override
                                public void getContactData(Contact contact) {
                                    ContactDatabase.getInsatance(MainActivity.this).contactDao().updateContact(contact);
                                    getContacts();
                                }
                            };

                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .add(new EditContactFragment(editContactI), "editContactFragment")
                                    .commit();

                        }
                        else if(id == R.id.delete){
                            ContactDatabase.getInsatance(MainActivity.this).contactDao().deleteContcat(contact);
                            getContacts();
                        }
                        return true;
                    }
                });
            }
        };
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getContacts();
    }

    private void getContacts() {
        contactList = ContactDatabase.getInsatance(this).contactDao().getContacts();
        contactAdapter = new ContactAdapter(this, contactList, menuI);
        recyclerView.setAdapter(contactAdapter);
    }

    public void addContactBtn(View view) {
        startActivity(new Intent(MainActivity.this, AddContactActivity.class));
    }
}