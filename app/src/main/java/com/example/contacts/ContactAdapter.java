package com.example.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private ArrayList<Contact> contacts, copied_contacts;

    public ContactAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        copied_contacts = new ArrayList<>(contacts);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_card, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView name;

        public MyViewHolder(View v) {
            super(v);
            this.view = v;
            name = this.view.findViewById(R.id.name);
        }
    }
    public void filter(String text){
        if(text.isEmpty()){
            contacts.clear();
            contacts.addAll(copied_contacts);
        }else {
            ArrayList<Contact> result = new ArrayList<>();
            text = text.toLowerCase();
            for(Contact contact:contacts ){
                if(contact.getName().toLowerCase().contains(text)){
                    result.add(contact);
                }
            }
            contacts.clear();
            contacts.addAll(result);
        }
        notifyDataSetChanged();
    }
}


