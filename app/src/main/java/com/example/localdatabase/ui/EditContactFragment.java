package com.example.localdatabase.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.localdatabase.R;
import com.example.localdatabase.room.Contact;
import com.example.localdatabase.room.ContactDatabase;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class EditContactFragment extends DialogFragment {

    TextInputEditText nameET, phoneET;
    MaterialButton updateBtn;

    EditContactI editContactI;

    public EditContactFragment(EditContactI editContactI) {
        this.editContactI = editContactI;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameET = view.findViewById(R.id.edit_name_et);
        phoneET = view.findViewById(R.id.edit_phone_et);
        updateBtn = view.findViewById(R.id.edit_update_btn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString().trim();
                String phone = phoneET.getText().toString();

                if(name.isEmpty() || phone.isEmpty()){
                    Toast.makeText(requireContext(), "Please fill all data", Toast.LENGTH_SHORT).show();
                    return;
                }

                Contact contact = new Contact(name, phone);

                editContactI.getContactData(contact);
                dismiss();
            }
        });
    }

}