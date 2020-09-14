package com.example.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
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
    private ContactDatabase database;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewContacts = findViewById(R.id.recyclerView);
        viewContacts.setLayoutManager(new LinearLayoutManager(this));
        button = findViewById(R.id.floatingActionButton);
        searchView = findViewById(R.id.searchbar);
        contacts = new ArrayList<>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                database = ContactDatabase.getInstance(getApplicationContext());
                contactDao = database.contactDao();
                contacts.addAll(contactDao.getAll());
            }
        });
        adapter = new ContactAdapter(this, contacts);
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, Add.class);
                startActivityForResult(intent, 123);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 123 && resultCode == RESULT_OK){
            final Contact newContact = (Contact) data.getSerializableExtra("returnedcontact");
            contacts.add(newContact);
            adapter.notifyDataSetChanged();
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    contactDao.insertAll(newContact);
                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}