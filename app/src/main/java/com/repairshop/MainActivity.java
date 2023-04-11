package com.repairshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.repairshop.amit.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvRepairShopName, tvEmployeeName, tvEmployeePosition,CreatBy,Creatat;
    EditText VehicalName,VehicalModel,VehicalMake,VehicalFuel,VehicalVariation;
    Button SBtn;
long vehical_type , make;
    private com.repairshop.DatabaseManager dbHelper;

    private com.repairshop.RepairShop shop;
    private SharedPreferences sharedPreferences;
    long id;
    AlertDialog.Builder alert;
    String employeeName;
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
        alert = new AlertDialog.Builder(this);
        // Get RepairShop name and display in TextView
        String repairShopName = dbHelper.getRepairShopName();
        tvRepairShopName.setText(repairShopName);
         id = dbHelper.getRepairId(repairShopName);

        employeeName   = dbHelper.getEmployeeName();



        // Get Employee name and position and display in TextViews

        String employeePosition = dbHelper.getEmployeePosition();
        tvEmployeeName.setText(employeeName);
        tvEmployeePosition.setText(employeePosition);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addCarType:
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                final EditText edittext = new EditText(MainActivity.this);
                alert.setMessage("Please Enter Your Car Type");
                alert.setTitle("Enter Car Type");
                alert.setView(edittext);
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        String VehicalType = String.valueOf(edittext.getText());
                        vehical_type = dbHelper.addVehicalType(VehicalType,employeeName);
                        //OR
                        Toast.makeText(MainActivity.this, "Vehical Type Added", Toast.LENGTH_SHORT).show();

                    }
                });
                alert.show();
                break;
            case R.id.addCarModel:
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

// Add a TextView here for the "Title" label, as noted in the comments
                final EditText Model = new EditText(MainActivity.this);
                Model.setHint("Enter Your Car Model");
                layout.addView(Model); // Notice this is an add method

// Add another TextView here for the "Description" label
                final EditText Feul = new EditText(MainActivity.this);
                Feul.setHint("Enter Feul Average");
                layout.addView(Feul); // Another add method

                alert.setView(layout);
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        String Vehicalfeul = String.valueOf(Feul.getText());
                        String Vehicalmodel = String.valueOf(Model.getText());

                        if (make!=-1){
                            long modelid = dbHelper.addVehicalModel(Vehicalmodel,Vehicalfeul,employeeName,id,make,vehical_type);
                            if (modelid!= -1){
                                Toast.makeText(MainActivity.this, "All Data Added Succesfully", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Car Make First", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.show();
                break;
            case R.id.addCarMake:
                final EditText Carmake = new EditText(MainActivity.this);
                alert.setMessage("Please Enter Your Car Model");
                alert.setTitle("Enter Car Model");
                alert.setView(Carmake);
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        String VehicalMake = String.valueOf(Carmake.getText());
                        if (vehical_type!=-1){
                            make = dbHelper.addVehicalMake(VehicalMake,employeeName,id);
                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Car Type First", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.show();

                break;

        }

        return true;
    }
}