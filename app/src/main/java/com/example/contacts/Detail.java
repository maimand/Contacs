package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    private TextView tvName, tvNumber, tvEmail;
    private Contact contact ;
    private ContactDatabase database;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.name);
        tvNumber = findViewById(R.id.number);
        tvEmail = findViewById(R.id.email);
        Intent intent = getIntent();

        contact = (Contact) intent.getSerializableExtra("contact");
        if(contact != null){
            tvName.setText(contact.getName());
            tvNumber.setText(contact.getNumber());
            tvEmail.setText(contact.getEmail());
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mn_edit:
                Intent intent = new Intent(this, update.class);
                intent.putExtra("contact_infor", contact);
                startActivity(intent);
                return true;
            case R.id.mn_delete:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        database = ContactDatabase.getInstance(getApplicationContext());
                        contactDao = database.contactDao();
                        contactDao.delete(contact);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}