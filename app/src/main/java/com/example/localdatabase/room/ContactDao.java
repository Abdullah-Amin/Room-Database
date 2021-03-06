package com.example.localdatabase.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void addContact (Contact contact);

    @Update
    void updateContact(Contact contact);

    @Delete
    void deleteContcat(Contact contact);

    @Query("SELECT * FROM contacts")
    List<Contact> getContacts();
}
