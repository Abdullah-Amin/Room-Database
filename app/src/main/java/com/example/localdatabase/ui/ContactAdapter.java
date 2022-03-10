package com.example.localdatabase.ui;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localdatabase.R;
import com.example.localdatabase.room.Contact;
import com.example.localdatabase.room.ContactDatabase;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    Context context;
    List<Contact> contactList;
    MenuI menuI;

    public ContactAdapter(Context context, List<Contact> contactList, MenuI menuI) {
        this.context = context;
        this.contactList = contactList;
        this.menuI = menuI;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.nameTV.setText(contact.getName());
        holder.phoneTV.setText(contact.getPhone());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                menuI.openMenu(holder, contact);
                return true;
            }
        });
    }

    private void onClickContactBtn(ContactHolder holder) {
        holder.materialButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {

        TextView nameTV, phoneTV;
        MaterialButton materialButtonEdit, materialButtonDelete;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.contact_name);
            phoneTV = itemView.findViewById(R.id.contact_phone);
            materialButtonDelete = itemView.findViewById(R.id.delete_contact_btn);
            materialButtonEdit = itemView.findViewById(R.id.edit_contact_btn);
        }
    }
}
