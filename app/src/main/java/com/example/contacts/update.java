package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class update extends AppCompatActivity {
    private ContactDatabase database;
    private ContactDao contactDao;
    private EditText edtName, edtNumer, edtEmail;
    private Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtName = findViewById(R.id.editTextTextPersonName);
        edtNumer = findViewById(R.id.editTextPhone);
        edtEmail = findViewById(R.id.editTextTextEmailAddress);
        contact = (Contact) getIntent().getSerializableExtra("contact_infor");
        edtName.setText(contact.getName());
        edtNumer.setText(contact.getNumber());
        edtEmail.setText(contact.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_done, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mn_done:
                String name = edtName.getText().toString();
                String number = edtNumer.getText().toString();
                String email = edtEmail.getText().toString();
                Contact newcontact = new Contact(name, number, email,0);
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        database = ContactDatabase.getInstance(getApplicationContext());
                        contactDao = database.contactDao();
                        contactDao.update(contact);
                    }
                });
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}