package com.repairshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DatabaseManager extends SQLiteOpenHelper {
    // Constants for database name and version

    private static final String DATABASE_NAME = "repair_shop.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_REPAIR_SHOP = "repair_shop";
    private static final String TABLE_POSITION = "position";
    private static final String TABLE_EMPLOYEE = "employee";

    // Common columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_DELETED = "deleted";
    private static final String COLUMN_DISABLING_REASON = "disabling_reason";
    private static final String COLUMN_ENABLED = "enabled";
    private static final String COLUMN_UPDATED_BY = "updated_by";
    private static final String COLUMN_UPDATED_AT = "updated_at";
    private static final String COLUMN_REPAIR_SHOP_ID = "repair_shop_id";
    // Repair Shop table columns
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_GSTIN = "gstin";
    private static final String COLUMN_LOGO = "logo";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_OWNER_FIRST_NAME = "owner_first_name";
    private static final String COLUMN_OWNER_LAST_NAME = "owner_last_name";
    private static final String COLUMN_PHONE = "phone";

    // Position table columns
    private static final String COLUMN_POSITION_NAME = "name";

    // Employee table columns
    private static final String COL_EMP_FIRST_NAME = "firstname";
    private static final String COL_EMP_EMAIL = "email";
    private static final String COL_EMP_LAST_NAME = "lastname";
    private static final String COL_EMP_PHONE = "phone";
    private static final String COLUMN_EMPLOYMENT_END_DATE = "employment_end_date";
    private static final String COLUMN_EMPLOYMENT_START_DATE = "employment_start_date";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String EMCOLUMN_PHONE = "phone";
    private static final String COLUMN_POSITION_ID = "position_id";

    // Create tables SQL statements
    private static final String CREATE_TABLE_REPAIR_SHOP = "CREATE TABLE " + TABLE_REPAIR_SHOP + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_CREATED_BY + " TEXT,"
            + COLUMN_CREATED_AT + " DATETIME,"
            + COLUMN_DELETED + " INTEGER,"
            + COLUMN_DISABLING_REASON + " TEXT,"
            + COLUMN_ENABLED + " INTEGER,"
            + COLUMN_UPDATED_BY + " TEXT,"
            + COLUMN_UPDATED_AT + " DATETIME,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_OWNER_FIRST_NAME + " TEXT,"
            + COLUMN_OWNER_LAST_NAME + " TEXT,"
            + COLUMN_PHONE + " TEXT"
            + ")";
    public long insertRepairShop(String repairShopName, String ownerFirstName, String ownerLastName, String address, String email, String phone,String owName,String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, repairShopName);
        values.put(COLUMN_OWNER_FIRST_NAME, ownerFirstName);
        values.put(COLUMN_OWNER_LAST_NAME, ownerLastName);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_CREATED_AT,getCurrentDateTime());
        values.put(COLUMN_CREATED_BY,owName);
        values.put(COLUMN_DESCRIPTION,desc);
        values.put(COLUMN_ENABLED,1);
        long result = db.insert(TABLE_REPAIR_SHOP, null, values);
        db.close();
        return result;
    }
    private static final String CREATE_TABLE_POSITION = "CREATE TABLE " + TABLE_POSITION + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_CREATED_BY + " TEXT,"
            + COLUMN_CREATED_AT + " DATETIME,"
            + COLUMN_DELETED + " INTEGER,"
            + COLUMN_DISABLING_REASON + " TEXT,"
            + COLUMN_ENABLED + " INTEGER,"
            + COLUMN_UPDATED_BY + " TEXT,"
            + COLUMN_UPDATED_AT + " DATETIME,"
            + COLUMN_POSITION_NAME + " TEXT,"
            + COLUMN_REPAIR_SHOP_ID + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_REPAIR_SHOP_ID + ") REFERENCES " + TABLE_REPAIR_SHOP + "(" + COLUMN_ID + ")"
            + ")";
    public long insertPosition(String positionName, long repairShopId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POSITION_NAME, positionName);
        values.put(COLUMN_REPAIR_SHOP_ID, repairShopId);
        long result = db.insert(TABLE_POSITION, null, values);
        db.close();
        return result;
    }
    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_CREATED_BY + " TEXT,"
            + COLUMN_CREATED_AT + " DATETIME,"
            + COLUMN_DELETED + " INTEGER,"
            + COLUMN_DISABLING_REASON + " TEXT,"
            + COLUMN_ENABLED + " INTEGER,"
            + COLUMN_UPDATED_BY + " TEXT,"
            + COLUMN_UPDATED_AT + " DATETIME,"
            + COL_EMP_EMAIL + " TEXT,"
            + COLUMN_EMPLOYMENT_END_DATE + " DATETIME,"
            + COLUMN_EMPLOYMENT_START_DATE + " DATETIME,"
            + COL_EMP_FIRST_NAME + " TEXT,"
            + COL_EMP_LAST_NAME + " TEXT,"
            + COL_EMP_PHONE + " TEXT,"
            + COLUMN_POSITION_ID + " INTEGER,"
            + COLUMN_REPAIR_SHOP_ID + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_POSITION_ID + ") REFERENCES " + TABLE_POSITION + "(" + COLUMN_ID + "),"
            + "FOREIGN KEY(" + COLUMN_REPAIR_SHOP_ID + ") REFERENCES " + TABLE_REPAIR_SHOP + "(" + COLUMN_ID + ")"
            + ")";
    public long insertEmployee(String firstName, String lastName, String email, String phone, long positionId, long repairShopId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMP_FIRST_NAME, firstName);
        values.put(COL_EMP_LAST_NAME, lastName);
        values.put(COL_EMP_EMAIL, email);
        values.put(COL_EMP_PHONE, phone);
        values.put(COLUMN_POSITION_ID, positionId);
        values.put(COLUMN_REPAIR_SHOP_ID, repairShopId); // add repair shop id to values
        long result = db.insert(TABLE_EMPLOYEE, null, values);
        db.close();
        return result;
    }
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REPAIR_SHOP);
        db.execSQL(CREATE_TABLE_POSITION);
        db.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSITION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPAIR_SHOP);

        // Create tables again
        onCreate(db);
    }
    public String getEmployeeName() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + TABLE_EMPLOYEE + "." + COL_EMP_FIRST_NAME + ", "
                + TABLE_EMPLOYEE + "." + COL_EMP_LAST_NAME + ", "
                + TABLE_POSITION + "." + COLUMN_POSITION_NAME
                + " FROM " + TABLE_EMPLOYEE
                + " JOIN " + TABLE_POSITION
                + " ON " + TABLE_EMPLOYEE + "." + COLUMN_POSITION_ID + "="
                + TABLE_POSITION + "." + COLUMN_ID;
        Cursor cursor = db.rawQuery(query, null);
        String employeeName = "";
        if (cursor.moveToFirst()) {
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMP_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMP_LAST_NAME));
            String position = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSITION_NAME));
            employeeName = firstName + " " + lastName + ", " + position;
        }
        cursor.close();
        db.close();
        return employeeName;
    }


    public String getEmployeePosition() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_POSITION_NAME
                + " FROM " + TABLE_POSITION
                + " WHERE " + COLUMN_ID + "=(SELECT " + COLUMN_POSITION_ID
                + " FROM " + TABLE_EMPLOYEE
                + " WHERE " + COL_EMP_FIRST_NAME + "='John'"
                + " AND " + COL_EMP_LAST_NAME + "='Doe')";
        Cursor cursor = db.rawQuery(query, null);
        String employeePosition = "";
        if (cursor.moveToFirst()) {
            employeePosition = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSITION_NAME));
        }
        cursor.close();
        db.close();
        return employeePosition;
    }



    public String getRepairShopName() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_NAME};
        Cursor cursor = db.query(TABLE_REPAIR_SHOP, columns, null, null, null, null, null);
        String repairShopName = "";
        if (cursor.moveToFirst()) {
            repairShopName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
        }
        cursor.close();
        db.close();
        return repairShopName;
    }


    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        return dateFormat.format(date);
    }

    public List<Map<String, String>> getAllShopAndEmployeeData() {
        List<Map<String, String>> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + TABLE_REPAIR_SHOP + "." + COLUMN_NAME + ", "
                + TABLE_REPAIR_SHOP + "." + COLUMN_ADDRESS + ", "
                + TABLE_REPAIR_SHOP + "." + COLUMN_EMAIL + ", "
                + TABLE_REPAIR_SHOP + "." + COLUMN_CREATED_AT + ", "
                + TABLE_REPAIR_SHOP + "." + COLUMN_CREATED_BY + ", "
                + TABLE_EMPLOYEE + "." + COL_EMP_FIRST_NAME + ", "
                + TABLE_EMPLOYEE + "." + COL_EMP_LAST_NAME + ", "
                + TABLE_POSITION + "." + COLUMN_POSITION_NAME
                + " FROM " + TABLE_REPAIR_SHOP
                + " JOIN " + TABLE_EMPLOYEE
                + " ON " + TABLE_REPAIR_SHOP + "." + COLUMN_CREATED_BY + "="
                + TABLE_EMPLOYEE + "." + COLUMN_ID
                + " JOIN " + TABLE_POSITION
                + " ON " + TABLE_EMPLOYEE + "." + COLUMN_POSITION_ID + "="
                + TABLE_POSITION + "." + COLUMN_ID;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Map<String, String> dataMap = new HashMap<>();
            String shopName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String shopAddress = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
            String shopEmail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String shopCreatedAt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT));
            String shopCreatedBy = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_BY));
            String employeeFirstName = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMP_FIRST_NAME));
            String employeeLastName = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMP_LAST_NAME));
            String employeePosition = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSITION_NAME));
            dataMap.put("shopName", shopName);
            dataMap.put("shopAddress", shopAddress);
            dataMap.put("shopEmail", shopEmail);
            dataMap.put("shopCreatedAt", shopCreatedAt);
            dataMap.put("shopCreatedBy", shopCreatedBy);
            dataMap.put("employeeName", employeeFirstName + " " + employeeLastName);
            dataMap.put("employeePosition", employeePosition);
            dataList.add(dataMap);
        }
        cursor.close();
        db.close();
        return dataList;
    }


}
