package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.ACTION_VIEW;
import static android.view.View.GONE;
import static androidx.core.content.ContextCompat.startActivity;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private static ArrayList<Contact> contacts, copied_contacts;
    private static Context context;

    public ContactAdapter( Context context, ArrayList<Contact> contacts) {
        this.contacts = contacts;
        copied_contacts = contacts;
        this.context = context;
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
        holder.number.setText(contacts.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView name, call, sms, info, number;
        public LinearLayout panel;


        public MyViewHolder(View v) {
            super(v);
            this.view = v;
            name = this.view.findViewById(R.id.name);
            panel = this.view.findViewById(R.id.contentPanel);
            call = this.view.findViewById(R.id.call_action);
            sms = this.view.findViewById(R.id.sms_action);
            info = this.view.findViewById(R.id.infor_action);
            number = this.view.findViewById(R.id.number_show);
            panel.setVisibility(GONE);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(panel.getVisibility() == GONE){
                        panel.setVisibility(View.VISIBLE);
                    }else{
                        panel.setVisibility(GONE);
                    }
                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String phone = contacts.get(position).getNumber();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    context.startActivity(intent);
                }
            });
            sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String phone = contacts.get(position).getNumber();
                    Intent intent = new Intent(ACTION_VIEW, Uri.fromParts("sms", phone, null));
                    context.startActivity(intent);
                }
            });
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Contact seleted = contacts.get(position);
                    Intent intent = new Intent(context, Detail.class);
                    intent.putExtra("contact", seleted);
                    context.startActivity(intent);
                }
            });

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


