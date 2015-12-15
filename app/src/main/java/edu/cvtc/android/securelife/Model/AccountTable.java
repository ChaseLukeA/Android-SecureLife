package edu.cvtc.android.securelife.Model;

import android.database.sqlite.SQLiteDatabase;


public class AccountTable {

    public static final String DATABASE_ACCOUNTS_TABLE = "accounts";

    public static final String ACCOUNT_ID_KEY = "_id";
    public static final int ACCOUNT_ID_COL = 0;

    public static final String FRIENDLY_NAME_KEY = "friendlyName";
    public static final int FRIENDLY_NAME_COL = 1;

    public static final String TYPE_KEY = "type";
    public static final int TYPE_COL = 2;

    public static final String URL_KEY = "url";
    public static final int URL_COL = 3;

    public static final String EMAIL_KEY = "email";
    public static final int EMAIL_COL = 4;

    public static final String USERNAME_KEY = "username";
    public static final int USERNAME_COL = 5;

    public static final String PASSWORD_KEY = "password";
    public static final int PASSWORD_COL = 6;

    public static final String SECRET_QUESTION_KEY = "secretQuestion";
    public static final int SECRET_QUESTION_COL = 7;

    public static final String SECRET_ANSWER_KEY = "secretAnswer";
    public static final int SECRET_ANSWER_COL = 8;

    public static final String NOTES_KEY = "notes";
    public static final int NOTES_COL = 9;

    public static final String BUSINESS_NAME_KEY = "businessName";
    public static final int BUSINESS_NAME_COL = 10;

    public static final String BUSINESS_ADDRESS_KEY = "businessAddress";
    public static final int BUSINESS_ADDRESS_COL = 11;

    public static final String BUSINESS_CITY_KEY = "businessCity";
    public static final int BUSINESS_CITY_COL = 12;

    public static final String BUSINESS_STATE_KEY = "businessState";
    public static final int BUSINESS_STATE_COL = 13;

    public static final String BUSINESS_ZIP_KEY = "businessZip";
    public static final int BUSINESS_ZIP_COL = 14;

    public static final String BUSINESS_PHONE_NUMBER_KEY = "businessPhoneNumber";
    public static final int BUSINESS_PHONE_NUMBER_COL = 15;

    public static final String BUSINESS_CONTACT_KEY = "businessContact";
    public static final int BUSINESS_CONTACT_COL = 16;

    public static final String ACCT_NUMBER_KEY = "acctNumber";
    public static final int ACCT_NUMBER_COL = 17;

    public static final String ROUTING_NUMBER_KEY = "routingNumber";
    public static final int ROUTING_NUMBER_COL = 18;

    public static final String EXPIRATION_DATE_KEY = "expirationDate";
    public static final int EXPIRATION_DATE_COL = 19;

    public static final String CVV_NUMBER_KEY = "cvvNumber";
    public static final int CVV_NUMBER_COL = 20;

    public static final String AVATAR_KEY = "avatar";
    public static final int AVATAR_COL = 21;

    public static final String IMAGE_URI_KEY = "imageUri";
    public static final int IMAGE_URI_COL = 22;

    public static final String SERIAL_NUMBER_KEY = "serialNumber";
    public static final int SERIAL_NUMBER_COL = 23;

    public static final String STORAGE_SIZE_KEY = "storageSize";
    public static final int STORAGE_SIZE_COL = 24;

    public static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + DATABASE_ACCOUNTS_TABLE + " (" +
                    ACCOUNT_ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FRIENDLY_NAME_KEY + " TEXT NOT NULL, " +
                    TYPE_KEY + " TEXT NOT NULL, " +
                    URL_KEY + " TEXT, " +
                    EMAIL_KEY + " TEXT, " +
                    USERNAME_KEY + " TEXT, " +
                    PASSWORD_KEY + " TEXT, " +
                    SECRET_QUESTION_KEY + " TEXT, " +
                    SECRET_ANSWER_KEY + " TEXT, " +
                    NOTES_KEY + " TEXT, " +
                    BUSINESS_NAME_KEY + " TEXT, " +
                    BUSINESS_ADDRESS_KEY + " TEXT, " +
                    BUSINESS_CITY_KEY + " TEXT, " +
                    BUSINESS_STATE_KEY + " TEXT, " +
                    BUSINESS_ZIP_KEY + " TEXT, " +
                    BUSINESS_PHONE_NUMBER_KEY + " TEXT, " +
                    BUSINESS_CONTACT_KEY + " TEXT, " +
                    ACCT_NUMBER_KEY + " TEXT, " +
                    ROUTING_NUMBER_KEY + " TEXT, " +
                    EXPIRATION_DATE_KEY + " TEXT, " +
                    CVV_NUMBER_KEY + " TEXT, " +
                    AVATAR_KEY + " TEXT, " +
                    IMAGE_URI_KEY + " TEXT, " +
                    SERIAL_NUMBER_KEY + " TEXT, " +
                    STORAGE_SIZE_KEY + " TEXT" +
                    ")";

    public static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + DATABASE_ACCOUNTS_TABLE;


    public static void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE);
    }


    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        database.execSQL(DATABASE_CREATE);
        onCreate(database);
    }

}
