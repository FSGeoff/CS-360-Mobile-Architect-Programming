package com.zybooks.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        Button loginButton = findViewById(R.id.loginButton);
        Button newUserButton = findViewById(R.id.newUserButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEditText = findViewById(R.id.usernameEditText);
                EditText passwordEditText = findViewById(R.id.passwordEditText);

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (databaseHelper.checkUser(username, password)) {
                    // User authentication successful, navigate to DatabaseActivity
                    Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle create new account button click event
                // You can navigate to a new activity for account creation here
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
