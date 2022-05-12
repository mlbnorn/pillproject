package com.example.pillproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper
{
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PresDrugsDB.db";
    public static final String TABLE_NAME = "PresDrug";
    public static final String COLUMN_ID = "drugID";
    public static final String COLUMN_NAME = "drugName";
    public static final String COLUMN_DESCRIPTION = "drugDesc";

    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,DATABASE_NAME,factory, DATABASE_VERSION);
    }

    //Create Drug Table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+ COLUMN_ID
            + " INTEGER PRIMARY KEY," + COLUMN_NAME +
            " TEXT, " + COLUMN_DESCRIPTION + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {}

    //Function: Load Data
    public String loadHandler()
    {
        String result = "";
        String query = "SELECT*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext())
        {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            result += String.valueOf(result_0)+ " " + result_1
                    + System.getProperty("line.seperator");
        }
        cursor.close();
        db.close();
        return result;
    }

    //Function: Add a New Record
    public void addHandler(PresDrug presDrug)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,presDrug.getDrugID());
        values.put(COLUMN_NAME, presDrug.getDrugName());
        values.put(COLUMN_DESCRIPTION, presDrug.getDrugDesc());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Function: Find Information by Condition
    public PresDrug findHandler(String drugname)
    {
        String query = "Select * FROM " + TABLE_NAME + "WHERE"
                        + COLUMN_NAME + " = " + "'" + drugname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        PresDrug drug = new PresDrug();

        if (cursor.moveToFirst())
        {
            drug.setDrugID(Integer.parseInt(cursor.getString(0)));
            drug.setDrugName(cursor.getString(1));
            drug.setDrugDesc(cursor.getString(2));
            cursor.close();
        }
        else
        {drug = null;}

        db.close();
        return drug;
    }

    //Function: Delete a Record by Condition
    public boolean deleteHandler (int ID)
    {
        boolean result = false;
        String query = "Select*FROM" + TABLE_NAME + "WHERE"
                        + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PresDrug drug = new PresDrug();

        if (cursor.moveToFirst())
        {
            drug.setDrugID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]
            {
                    String.valueOf(drug.getDrugID())
            });

            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    //Function: Update Information of a Record
    public boolean updateHandler (int ID, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID,ID);
        args.put(COLUMN_NAME, name);

        return db.update(TABLE_NAME, args, COLUMN_ID + "=" +ID,null) > 0;
    }

}
