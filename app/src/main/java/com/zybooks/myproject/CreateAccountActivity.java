package com.zybooks.myproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        databaseHelper = new DatabaseHelper(this);

        EditText usernameEditText = findViewById(R.id.newUsernameEditText);
        EditText passwordEditText = findViewById(R.id.newPasswordEditText);
        Button createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = usernameEditText.getText().toString();
                String newPassword = passwordEditText.getText().toString();

                // Add code to insert new user into the database
                databaseHelper.addUser(newUsername, newPassword);

                Toast.makeText(CreateAccountActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                finish(); // Finish the activity after creating the account
            }
        });
    }
}