package com.example.pillproject;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // The Android's default system path
    // of your application database.
    private static String TAG = "DatabaseHelper.java";
    private final File DB_FILE;
    private static String DB_NAME = "drug_database.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private SQLiteOpenHelper sqLiteOpenHelper;

    // Table name in the database.
    public static final String DRUGS_TABLE = "drugs";

    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_FILE = myContext.getDatabasePath(DB_NAME);
    }

    public void createDataBase() throws IOException {
        // If the database does not exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
    // Check that the database file exists in databases folder
    private boolean checkDataBase() {
        //return DB_FILE.exists();
        return false;
    }

    // Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = myContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_FILE);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    // Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        // Log.v("DB_PATH", DB_FILE.getAbsolutePath());
        myDataBase = SQLiteDatabase.openDatabase(DB_FILE.toString(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        // mDataBase = SQLiteDatabase.openDatabase(DB_FILE, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return myDataBase != null;
    }

    @Override
    public synchronized void close()
    {
        // close the database.
        if (myDataBase != null) myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // It is an abstract method
        // but we define our own method here.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // It is an abstract method which is
        // used to perform different task
        // based on the version of database.
    }

    // This method is used to get the
    // algorithm topics from the database.
    public ArrayList<Drug> getDrugs(Activity activity)
    {
        sqLiteOpenHelper = new DatabaseHelper(activity);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        ArrayList<Drug> list = new ArrayList<>();

        // query help us to return all data
        // the present in the ALGO_TOPICS table.
        String query = "SELECT * FROM " + DRUGS_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do
            {
                list.add(new Drug(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
