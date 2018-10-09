package com.example.android.databaseapp;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NameDatabase db;
    Button insertButton;
    Button showButton;
    TextView namesTextView;
    EditText namesEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namesTextView = findViewById(R.id.names);
        namesEditText = findViewById(R.id.name);
        insertButton = findViewById(R.id.btn_insert);
        showButton = findViewById(R.id.show);

        db = Room.databaseBuilder(MainActivity.this, NameDatabase.class, "database-name")
                .fallbackToDestructiveMigration().build();

        insertButton.setOnClickListener(new View.OnClickListener() { //when you click insert, it runs the Dao
            @SuppressLint("StaticFieldLeak")                         //that inserts the text from the edittext
            @Override                                                // done in an async task so its in the background.
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>() {

            @Override
            public Void doInBackground(Void... params) { //not passing anything into the async task.
                                                         //just want it to do something for me in the background
                db.nameDao().insertAll(new User(namesEditText.getText().toString()));
                return null;
                    }
                }
                .execute();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() { //when you click the button, it runs the DAO
            @SuppressLint("StaticFieldLeak")                       //for the database interactions.
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, List<User>>() {

            @Override
            protected List<User> doInBackground(Void... params) {
                return db.nameDao().getAll();
                    }

            @Override
            protected void onPostExecute(List<User> users) { //results are posted back to the main thread
                namesTextView.setText(users.toString());     //then it is set to the textview.
                    }
                }
                .execute();
            }
        });
    }
}
