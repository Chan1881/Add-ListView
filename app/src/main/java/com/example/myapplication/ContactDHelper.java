package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.myapplication.Customer.COLUMN_ID;

public class ContactDHelper extends SQLiteOpenHelper {
    private static final String databasename = "MyDatabase.db";

    public ContactDHelper(Context context) {
        super(context, databasename, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE "+ Customer.TABLE_NAME
                    + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + Customer.COLUMN_NAME + "TEXT, "
                    + Customer.COLUMN_NUMBER + "TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Customer.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact(Customer contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Customer.COLUMN_NAME, contact.getName());
        values.put(Customer.COLUMN_NUMBER, contact.getNumber());
        db.insert(Customer.TABLE_NAME, null, values);
        return true;
    }

    public void updateContact(Customer contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Customer.COLUMN_NAME, contact.getName());
        values.put(Customer.COLUMN_NUMBER, contact.getNumber());
        db.update(Customer.TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{Integer.toString(contact.getId())});
    }

    public void deleteContact(Integer id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Customer.TABLE_NAME, Customer.COLUMN_ID + " = ?", new String[] {Integer.toString(id)});
    }

    public ArrayList<Customer> getAllContacts(){
        ArrayList<Customer> contactArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Customer.TABLE_NAME, null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            Customer contact = new Customer();
            contact.setId(res.getInt(res.getColumnIndex(Customer.COLUMN_ID)));
            contact.setName(res.getString(res.getColumnIndex(Customer.COLUMN_NAME)));
            contact.setNumber(res.getString(res.getColumnIndex(Customer.COLUMN_NUMBER)));
            contactArrayList.add(contact);
            res.moveToNext();
        }
        res.close();
        return contactArrayList;
    }
    }
