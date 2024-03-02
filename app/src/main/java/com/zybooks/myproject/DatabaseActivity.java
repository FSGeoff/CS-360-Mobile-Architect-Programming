
package com.zybooks.myproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class DatabaseActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private static final int REQUEST_SMS_PERMISSION = 1;
    private static final String message = "Low inventory alert: Item X is running low.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        databaseHelper = new DatabaseHelper(this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button sendSMSButton = findViewById(R.id.sendSMSButton);
        sendSMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(DatabaseActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DatabaseActivity.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSION);
                } else {
                    sendSMSAlert(message);
                }
            }
        });

        // Add item to the database
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement adding item to the database
                String itemName = "Sample Item";
                int itemQuantity = 10;
                String itemDescription = "Sample Description";
                databaseHelper.addNewItem(itemName, itemQuantity, itemDescription);
            }
        });

        // Delete item from the database
        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement deleting item from the database
                int itemId = 1; // Provide the specific item ID to delete
                databaseHelper.deleteItem(itemId);
            }
        });

        // Update item in the database
        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement updating item in the database
                int itemId = 1; // Provide the specific item ID to update
                String updatedItemName = "Updated Item";
                int updatedItemQuantity = 15;
                String updatedItemDescription = "Updated Description";
                databaseHelper.updateItem(itemId, updatedItemName, updatedItemQuantity, updatedItemDescription);
            }
        });
    }

    private void sendSMSAlert(String message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("1234567890", null, message, null, null);
        Toast.makeText(this, "SMS alert sent successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSMSAlert(message);
            } else {
                Toast.makeText(this, "SMS permission denied. SMS alerts will not be sent.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

