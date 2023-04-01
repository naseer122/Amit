package com.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvRepairShopName, tvEmployeeName, tvEmployeePosition,CreatBy,Creatat;

    private com.repairshop.DatabaseManager dbHelper;

    private com.repairshop.RepairShop shop;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseManager(this);
        // Find TextViews in layout
        tvRepairShopName = findViewById(R.id.tv_repair_shop_name);
        tvEmployeeName = findViewById(R.id.tv_employee_name);
        tvEmployeePosition = findViewById(R.id.tv_employee_position);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Find TextViews in layout
        tvRepairShopName = findViewById(R.id.tv_repair_shop_name);
        tvEmployeeName = findViewById(R.id.tv_employee_name);
        tvEmployeePosition = findViewById(R.id.tv_employee_position);
        // Get RepairShop name and display in TextView
        String repairShopName = dbHelper.getRepairShopName();
        tvRepairShopName.setText(repairShopName);

        // Get Employee name and position and display in TextViews
        String employeeName = dbHelper.getEmployeeName();
        String employeePosition = dbHelper.getEmployeePosition();
        tvEmployeeName.setText(employeeName);
        tvEmployeePosition.setText(employeePosition);


    }
}