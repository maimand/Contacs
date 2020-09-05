package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView viewContacts;
    private FloatingActionButton button;
    private SearchView searchView;
    private ArrayList<Contact> contacts;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewContacts = findViewById(R.id.recyclerView);
        button = findViewById(R.id.floatingActionButton);
        searchView = findViewById(R.id.searchbar);
        contacts = new ArrayList<>();
        contacts.add(new Contact("Nguyen A", "0101010101", "a@gmail.com", 0));
        contacts.add(new Contact("Nguyen B", "0101010101", "b@gmail.com", 0));
        contacts.add(new Contact("Nguyen C", "0101010101", "c@gmail.com", 0));
        contacts.add(new Contact("Nguyen D", "0101010101", "d@gmail.com", 0));
        adapter = new ContactAdapter(contacts);
        viewContacts.setLayoutManager(new LinearLayoutManager(this));
        viewContacts.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return true;
            }
        });
    }
}