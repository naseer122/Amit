package com.repairshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.repairshop.amit.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvRepairShopName, tvEmployeeName, tvEmployeePosition,CreatBy,Creatat;
    EditText VehicalName,VehicalModel,VehicalMake,VehicalFuel,VehicalVariation;
    Button SBtn;

    private com.repairshop.DatabaseManager dbHelper;

    private com.repairshop.RepairShop shop;
    private SharedPreferences sharedPreferences;
    long id;

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
        VehicalMake = findViewById(R.id.vehicle_make_input);
        VehicalModel = findViewById(R.id.vehicle_model_input);
        VehicalName = findViewById(R.id.vehicle_type_name_input);
        VehicalFuel = findViewById(R.id.vehicle_model_feul);
        VehicalVariation = findViewById(R.id.vehicle_model_varitaion);
        SBtn = findViewById(R.id.submit_button);
        // Get RepairShop name and display in TextView
        String repairShopName = dbHelper.getRepairShopName();
        tvRepairShopName.setText(repairShopName);
         id = dbHelper.getRepairId(repairShopName);
        String name = VehicalName.getText().toString();
        String model = VehicalModel.getText().toString();
        String make = VehicalMake.getText().toString();
        String feul = VehicalFuel.getText().toString();
        String employeeName = dbHelper.getEmployeeName();

        String variation = VehicalVariation.getText().toString();

        // Get Employee name and position and display in TextViews

        String employeePosition = dbHelper.getEmployeePosition();
        tvEmployeeName.setText(employeeName);
        tvEmployeePosition.setText(employeePosition);
        SBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long VehicalId = dbHelper.addVehicalType(name,employeeName);
                if (id != -1){
                    long makeid = dbHelper.addVehicalMake(make,employeeName,id);
                    if (makeid != -1){
                        long modelid = dbHelper.addVehicalModel(model,feul,employeeName,id,makeid,VehicalId);
                        if (modelid != -1){
                            VehicalName.setText("");
                            VehicalModel.setText("");
                            VehicalFuel.setText("");
                            VehicalMake.setText("");
                            RecyclerView recyclerView = findViewById(R.id.recycler_view);
                            ArrayList<vehical> vehicals = new ArrayList<>();// call the method that retrieves data from the database
                            vehicals = dbHelper.getAlldata();
                            VehicleAdapter adapter = new VehicleAdapter(vehicals);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            Toast.makeText(MainActivity.this, "Models Successfully Created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error Creating Model Table", Toast.LENGTH_SHORT).show();
                        }
                    }  else {
                        Toast.makeText(MainActivity.this, "Error Creating Make Table", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error Creating Vehical Type Table", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}