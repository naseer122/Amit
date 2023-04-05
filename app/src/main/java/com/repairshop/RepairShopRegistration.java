package com.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.repairshop.amit.R;

public class RepairShopRegistration extends AppCompatActivity {

    private EditText etShopName, etOwnerFirstName, etOwnerLastName, etAddress, etPhone, etEmail, etGSTIN, etDescription;

    private Button btnSubmit;

    private SharedPreferences sharedPreferences;
    private DatabaseManager databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_shop_registration);
        initViews();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isShopRegistered = sharedPreferences.getBoolean("isShopRegistered", false);
        if (isShopRegistered) {
            // skip registration form activity and show main activity
            Intent intent = new Intent(RepairShopRegistration.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        eventhandling();

    }

    private void eventhandling() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (etShopName.getText().toString().isEmpty() || etOwnerFirstName.getText().toString().isEmpty() || etOwnerLastName.getText().toString().isEmpty()
                        || etAddress.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty()
                        || etGSTIN.getText().toString().isEmpty() || etDescription.getText().toString().isEmpty()) {
                    Toast.makeText(RepairShopRegistration.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                // insert shop data into database
                String repairShopName = etShopName.getText().toString();
                String ownerFirstName = etOwnerFirstName.getText().toString();
                String ownerLastName = etOwnerLastName.getText().toString();
                String address = etAddress.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String desc = etDescription.getText().toString();
                long repairShopId = databaseHelper.insertRepairShop(repairShopName, ownerFirstName,  ownerLastName, address, email, phone,ownerFirstName,desc);
                // save shop id to shared preferences
                if(repairShopId != -1){
                    long positionId = databaseHelper.insertPosition("Admin", repairShopId);
                    editor.putLong("postion",positionId);
                    if(positionId != -1){
                        long employeeId = databaseHelper.insertEmployee(ownerFirstName, ownerLastName, email, phone, positionId, repairShopId);
                        editor.putLong("employee",employeeId);
                        if(employeeId != -1){
                            Toast.makeText(RepairShopRegistration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RepairShopRegistration.this, MainActivity.class);
                            startActivity(intent);

                            editor.putLong("shopId", repairShopId);
                            editor.putBoolean("isShopRegistered", true);
                            editor.apply();
                            finish();
                        }else{
                            Toast.makeText(RepairShopRegistration.this, "Employee registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RepairShopRegistration.this, "Position creation failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RepairShopRegistration.this, "Repair shop creation failed", Toast.LENGTH_SHORT).show();
                }

                // show main activity

            }
        });
    }

    private void initViews() {

        // initialize views
        etShopName = findViewById(R.id.shop_name);
        etOwnerFirstName = findViewById(R.id.owner_first_name);
        etOwnerLastName = findViewById(R.id.owner_last_name);
        etAddress = findViewById(R.id.address);
        etPhone = findViewById(R.id.phone);
        etEmail = findViewById(R.id.email);
        etGSTIN = findViewById(R.id.gstin);
        etDescription = findViewById(R.id.description);
        btnSubmit = findViewById(R.id.submit_button);

        databaseHelper = new DatabaseManager(this);

    }


}