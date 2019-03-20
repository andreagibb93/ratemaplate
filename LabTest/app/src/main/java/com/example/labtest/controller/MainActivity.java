package com.example.labtest.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labtest.R;
import com.example.labtest.model.User;
import com.example.labtest.model.UserManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserManager manager;
    private ListView myListView;
    private ArrayAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new UserManager();

        //store the data field objects
        myListView = findViewById(R.id.listView);

        //array adapter, list view needs this
        myArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, manager.getCollection()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                text1.setText(manager.getCollection().get(position).getDetails());
                return view;
            }
        };

        //set the adapter for the list view
        myListView.setAdapter(myArrayAdapter);

        //set an event listener: when the item on the list view is clicked
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                manager.getCollection().remove(position);
                myArrayAdapter.notifyDataSetChanged();

                if (manager.getCollection().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "My collection is empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addUserToCollection(View view) {
        EditText nameView = findViewById(R.id.name);
        EditText idView = findViewById(R.id.id);
        EditText ageView = findViewById(R.id.age);

        String name = nameView.getText().toString();
        String id = idView.getText().toString();
        int age = Integer.parseInt(ageView.getText().toString());

        if (nameView.getText().length() == 0 || idView.getText().length() == 0 || ageView.getText().length() == 0) {
            User user = new User();
            manager.addUser(user);
            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();
        } else if (age < 0 || age > 100) {
            User user = new User(name, id, 0);
            manager.addUser(user);
            Toast.makeText(getApplicationContext(), "Invalid age", Toast.LENGTH_LONG).show();
        } else {
            User user = new User(name, id, age);
            manager.addUser(user);

            Toast.makeText(getApplicationContext(), "User was added succesfully", Toast.LENGTH_LONG).show();
        }

        myArrayAdapter.notifyDataSetChanged();
    }


}
