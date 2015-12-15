package edu.cvtc.android.securelife.Helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import edu.cvtc.android.securelife.Model.AccountTable;

public class AccountDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "securelife.db";

    public static final int DB_VERSION = 1;


    public AccountDatabaseHelper(Context context, String name, CursorFactory factory, int version) {

        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        AccountTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        AccountTable.onUpgrade(db, oldVersion, newVersion);
    }

}
